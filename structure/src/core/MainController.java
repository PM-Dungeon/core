package core;

import core.interfaces.IEntity;

import java.util.ArrayList;

public class MainController {

    private final ArrayList<IEntity> dungeonEntities = new ArrayList<>();

    private void updateEntities() {
        dungeonEntities.removeIf(obj -> obj.deleteable());
        dungeonEntities.forEach(obj -> obj.update());
    }

    public void loop(){
        //firstFrame
        beginFrame();
        updateEntities();
        //updateLevel
        //updateGrafik
        //update Camera
        //update GameBatch
        //update hud
        endFrame();
    }

    // --------------------------- OWN IMPLEMENTATION ---------------------------
    protected void setup() {}

    protected void beginFrame() {}

    protected void endFrame() {}

    public void onLevelLoad() {}
    // --------------------------- END OWN IMPLEMENTATION ------------------------
}
