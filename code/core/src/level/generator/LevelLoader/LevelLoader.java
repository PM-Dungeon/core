package level.generator.LevelLoader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import level.elements.Level;
import level.generator.IGenerator;
import tools.Constants;

public class LevelLoader implements IGenerator {

    @Override
    public Level getLevel() {
        File dir = new File(Constants.getPathToGraph());
        File[] allLevelFiles = dir.listFiles();
        if (allLevelFiles == null || allLevelFiles.length == 0) {
            return getLevel();
        }
        File levelFile = allLevelFiles[new Random().nextInt(allLevelFiles.length)];
        Level level = loadLevel(levelFile.getPath());
        if (level == null) {
            return getLevel();
        }
        return level;
    }

    private Level loadLevel(String path) {
        Type levelType = new TypeToken<Level>() {}.getType();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path, StandardCharsets.UTF_8));
            return new Gson().fromJson(reader, levelType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File may be corrupted ");
            e.printStackTrace();
        }
        return null;
    }
}
