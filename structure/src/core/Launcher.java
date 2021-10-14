package core;

import core.controls.ControlsController;
import core.graphic.GraphicController;
import core.graphic.api.myLibGDX;
import core.level.LevelController;

public class Launcher {
    public static void main (String[]args) throws InterruptedException {
        GraphicController gc= new GraphicController(new myLibGDX());
        ControlsController cc =new ControlsController();
        LevelController lc =new LevelController();
        new MainController(gc,cc,lc);
    }
}
