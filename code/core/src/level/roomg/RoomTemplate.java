package level.roomg;

import level.enums.DesignLabel;
import level.enums.LevelElement;
import level.levelg.Room;

import java.util.ArrayList;
import java.util.List;

/** @author Andre Matutat */
public class RoomTemplate {
    private int[][] layout;
    private DesignLabel design;

    /**
     * A RoomTemplate can be used to create a room.
     *
     * @param layout the layout of the room
     * @param label the DesignLabel of the room
     */
    public RoomTemplate(int[][] layout, DesignLabel label) {
        this.layout = layout;
        this.design = label;
    }

    /**
     * copy a room
     *
     * @param r original room
     */
    public RoomTemplate(RoomTemplate r) {
        layout = new int[r.getLayout()[0].length][r.getLayout().length];
        for (int y = 0; y < layout.length; y++)
            for (int x = 0; x < layout[0].length; x++) layout[x][y] = r.getLayout()[x][y];
        design = r.getDesign();
    }

    /**
     * Replace all placeholder with the replacements in the list
     *
     * @param replacements list of replacements
     * @return the created room
     */
    public Room replace(final List<Replacement> replacements) {
        int layoutHeight = getLayout().length;
        int layoutWidth = getLayout()[0].length;
        int[][] roomLayout = new int[layoutHeight][layoutWidth];

        // copy layout
        for (int y = 0; y < layoutHeight; y++)
            for (int x = 0; x < layoutWidth; x++) roomLayout[y][x] = getLayout()[y][x];

        // remove all replacements that are too big
        List<Replacement> replacementList = new ArrayList<>(replacements);
        for (Replacement r : replacements) {
            if (r.getLayout()[0].length <= layoutWidth && r.getLayout().length <= layoutHeight)
                replacementList.add(r);
        }

        // replace with replacements
        boolean changes;
        do {
            changes = false;
            for (Replacement r : replacementList) {
                int rHeight = r.getLayout().length;
                int rWidth = r.getLayout()[0].length;
                for (int y = 0; y < layoutHeight - rHeight; y++)
                    for (int x = 0; x < layoutWidth - rWidth; x++)
                        if (roomLayout[y][x] == LevelElement.PLACEHOLDER.getValue()
                                && placeIn(roomLayout, r, x, y)) changes = true;
            }
        } while (changes);

        // replace all placeholder that are left with floor
        for (int y = 0; y < layoutHeight; y++)
            for (int x = 0; x < layoutWidth; x++)
                if (roomLayout[y][x] == LevelElement.PLACEHOLDER.getValue())
                    roomLayout[y][x] = LevelElement.FLOOR.getValue();
        return new Room(roomLayout, getDesign());
    }

    /**
     * Replace a specific spot in the layout
     *
     * @param layout layout to replace in
     * @param r the replacement
     * @param xCor place the left upper corner of the replacement on this x
     * @param yCor place the left upper corner of the replacement on this y
     * @return if replacement was done
     */
    private boolean placeIn(final int[][] layout, final Replacement r, int xCor, int yCor) {
        if (!canReplaceIn(layout, r, xCor, yCor)) return false;
        else {
            int[][] rlayout = r.getLayout();
            for (int y = yCor; y < yCor + rlayout.length; y++)
                for (int x = xCor; x < xCor + rlayout[0].length; x++) {
                    if (rlayout[y - yCor][x - xCor] != LevelElement.PLACEHOLDER.getValue())
                        layout[y][x] = rlayout[y - yCor][x - xCor];
                }
            return true;
        }
    }

    /**
     * Check if a replacement fit in a specific spot on the layout
     *
     * @param layout layout to replace in
     * @param r the replacement
     * @param xCor place the left upper corner of the replacement on this x
     * @param yCor place the left upper corner of the replacement on this y
     * @return if replacement can be done
     */
    private boolean canReplaceIn(int[][] layout, final Replacement r, int xCor, int yCor) {
        int[][] rlayout = r.getLayout();
        for (int y = yCor; y < yCor + rlayout.length; y++)
            for (int x = xCor; x < xCor + rlayout[0].length; x++) {
                if (rlayout[y - yCor][x - xCor] != LevelElement.PLACEHOLDER.getValue()
                        && layout[y][x] != LevelElement.PLACEHOLDER.getValue()) return false;
            }
        return true;
    }

    public int[][] getLayout() {
        return layout;
    }

    public DesignLabel getDesign() {
        return design;
    }
}
