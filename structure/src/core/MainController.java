package core;

import core.controls.ControlsController;
import core.graphic.GraphicController;
import core.interfaces.IEntity;
import core.level.LevelController;

import java.util.ArrayList;

public class MainController {

    protected GraphicController gc;
    protected ControlsController cc;
    protected LevelController lc;

    public MainController(GraphicController gc, ControlsController cc, LevelController lc) throws InterruptedException {
        this.gc=gc;
        this.cc=cc;
        this.lc=lc;
        this.loop();
    }

    private void loop() throws InterruptedException {
        final int TARGET_FPS = 30;
        final long OPTIMAL_TIME = 1000 / TARGET_FPS;
        while (true) {
            //update game

            //firstFrame
            beginFrame();

            //updateLevel
            //updateGrafik
            gc.draw(null,null);
            //update Camera
            //update GameBatch
            //update hud
            endFrame();

            long sleepTime = (OPTIMAL_TIME - System.currentTimeMillis()) % OPTIMAL_TIME + OPTIMAL_TIME;
            System.out.println("Sleep for: " + sleepTime);
            Thread.sleep(sleepTime);
        }
    }

    // --------------------------- OWN IMPLEMENTATION ---------------------------
    protected void setup() {}

    protected void beginFrame() {}

    protected void endFrame() {}

    public void onLevelLoad() {}
    // --------------------------- END OWN IMPLEMENTATION ------------------------
}
