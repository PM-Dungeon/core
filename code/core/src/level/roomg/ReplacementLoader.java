package level.roomg;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import level.DesignLabel;
import level.LevelElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * loads and stores replacements from a jsons
 *
 * @author Andre Matutat
 */
public class ReplacementLoader {
    private List<Replacement> replacements;

    /**
     * Creates a ReplacementLoader and loads the replacements from the json. if the json is empty, the list is empty
     *
     * @param path path to json
     */
    public ReplacementLoader(String path) {
        readFromJson(path);
    }


    /**
     * Returns a list of Replacements that have the corresponding DesignLabel
     *
     * @param l the DesignLabel, use ALL if you don't care
     * @return the list
     */
    public List<Replacement> getReplacements(DesignLabel l) {
        List<Replacement> results = new ArrayList<>(replacements);
        if (l != DesignLabel.ALL) results.removeIf(r -> r.getDesign() != l);
        return results;
    }

    /**
     * rotate the layout of the given replacement in 90 degree and create a new replacement with the rotated layout
     *
     * @param r the Replacement that holds the layout to rotate
     * @return new Replacement with rotated layout
     */
    private Replacement rotate90(final Replacement r) {
        LevelElement[][] originalLayout = r.getLayout();
        int mSize = originalLayout.length;
        int nSize = originalLayout[0].length;
        LevelElement[][] rotatedLayout = new LevelElement[nSize][mSize];
        for (int row = 0; row < mSize; row++)
            for (int col = 0; col < nSize; col++)
                rotatedLayout[col][mSize - 1 - row] = originalLayout[row][col];
        return new Replacement(rotatedLayout, r.canRotate(), r.getDesign());
    }

    /**
     * adds a replacement to the list
     *
     * @param r the replacement to add
     */
    public void addReplacement(Replacement r) {
        if (!replacements.contains(r)) replacements.add(r);
    }

    private void readFromJson(String path) {
        Type replacementType = new TypeToken<ArrayList<Replacement>>() {
        }.getType();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
            replacements = new Gson().fromJson(reader, replacementType);
            if (replacements == null) throw new NullPointerException("File is empty");
            // add all rotations to list
            List<Replacement> toRotate = new ArrayList<>(replacements);
            toRotate.removeIf(r -> !r.canRotate());

            for (Replacement r : toRotate) {
                Replacement tmp = r;
                // 90,180,270
                for (int i = 0; i < 3; i++) {
                    tmp = rotate90(tmp);
                    replacements.add(tmp);
                }
            }

        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("No Replacements to load in " + path);
            replacements = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("File Corrupted or other error");
            e.printStackTrace();
            replacements = new ArrayList<>();
        }
    }

    /**
     * Writes down the list to a json
     *
     * @param rep  the list of replacements to save
     * @param path where to save
     */
    /*public void writeToJSON(List<Replacement> rep, String path) {
        Gson gson = new Gson();
        String json = gson.toJson(rep);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("File" + path + " not found");
        }
    }*/

}
