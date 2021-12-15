package level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.levelg.Level;
import level.levelg.Room;
import level.levelg.Tile;
import tools.Point;

import java.util.ArrayList;
import java.util.List;

public class LevelAPI {
    private Level currentLevel;
    private SpriteBatch batch;
    private Painter painter;

    public LevelAPI(SpriteBatch batch, Painter painter) {
        this.batch = batch;
        this.painter = painter;
    }

    public void loadLevel() {
        // todo durch levelg tauschen
        int x = 8;
        int y = 8;
        LevelElement[][] layout = new LevelElement[y][x];
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++) {
                if (i == 0 || i == y - 1 || j == 0 || j == x - 1) layout[i][j] = LevelElement.WALL;
                else layout[i][j] = LevelElement.FLOOR;
            }

        layout[1][3] = LevelElement.WALL;
        layout[6][3] = LevelElement.WALL;
        layout[3][1] = LevelElement.WALL;
        layout[3][6] = LevelElement.WALL;

        Room r = new Room(layout, DesignLabel.DEFAULT, new Point(2, 1), new Point(2, 2));
        //  Room r2 = new Room(layout, DesignLabel.DEFAULT, new Point(3, 2), new Point(7, -3));
        List<Room> rl = new ArrayList<>();
        rl.add(r);
        // rl.add(r2);
        currentLevel = new Level(null, rl);
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
