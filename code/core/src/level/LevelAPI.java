package level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.elements.Level;
import level.elements.Room;
import level.elements.Tile;
import level.generator.IGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LevelAPI {
    private final Method onLevelLoad;
    private final Object klass;
    private final Object[] args;
    private Level currentLevel;
    private SpriteBatch batch;
    private Painter painter;
    private IGenerator gen;

    public LevelAPI(
            SpriteBatch batch,
            Painter painter,
            IGenerator gen,
            Method onLevelLoad,
            Object klass,
            Object[] args) {
        this.gen = gen;
        this.batch = batch;
        this.painter = painter;
        this.onLevelLoad = onLevelLoad;
        this.klass = klass;
        this.args = args;
    }

    public void loadLevel() throws InvocationTargetException, IllegalAccessException {
        currentLevel = gen.getLevel();
        onLevelLoad.invoke(klass, args);
    }

    public void update() {
        drawLevel();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    private void drawLevel() {
        for (Room r : getCurrentLevel().getRooms())
            for (int y = 0; y < r.getLayout().length; y++)
                for (int x = 0; x < r.getLayout()[0].length; x++) {
                    Tile t = r.getLayout()[y][x];
                    painter.draw(t.getTexture(), t.getGlobalPosition(), batch);
                }
    }
}
