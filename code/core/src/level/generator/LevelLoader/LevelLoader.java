package level.generator.LevelLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import level.elements.Level;
import level.generator.IGenerator;
import tools.Constants;

import java.io.IOException;
import java.util.Random;

public class LevelLoader implements IGenerator {

    @Override
    public Level getLevel() {
        FileHandle handle = Gdx.files.local(Constants.PATH_TO_LEVEL);
        FileHandle[] allLevelFiles = handle.list();
        FileHandle level = allLevelFiles[new Random().nextInt(allLevelFiles.length)];
        try {
            return Level.loadLevel(level.path());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getLevel();
    }
}
