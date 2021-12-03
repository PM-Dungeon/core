package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import level.enums.DesignLabel;
import level.levelg.Level;
import level.levelg.LevelG;
import tools.Constants;

import java.util.Random;

public class LevelController {
    private SpriteBatch batch;
    private GraphicController graphicController;
    private LevelG levelg;
    private Level currentLevel;

    public LevelController(SpriteBatch batch, GraphicController graphicController) {
        this.batch = batch;
        this.graphicController = graphicController;
        levelg = new LevelG();
    }

    /**
     * load a level
     *
     * @param nodecounter number of rooms in the level
     * @param edgecounter number of extra-connections in the level
     * @param label design of the level
     */
    public void loadLevel(int nodecounter, int edgecounter, DesignLabel label) {
        currentLevel = levelg.getLevel(nodecounter, edgecounter, label);
    }

    public void loadLevel(int nodecounter, DesignLabel label) {
        loadLevel(nodecounter, new Random().nextInt(Constants.MAXEDGES), label);
    }

    public void loadLevel(DesignLabel label) {
        loadLevel(2 + new Random().nextInt(Constants.MAXNODES), label);
    }

    public void loadLevel(int nodecounter) {
        loadLevel(nodecounter, DesignLabel.ALL);
    }

    public void loadLevel() {
        loadLevel(DesignLabel.ALL);
    }

    public void update() {
        drawLevel();
    }

    // todo
    private void drawLevel() {}

    public Level getLevel() {
        return currentLevel;
    }
}
