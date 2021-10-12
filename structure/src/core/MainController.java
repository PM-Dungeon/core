package core;

import core.interfaces.IEntity;

import java.util.ArrayList;

public class MainController {

    private final ArrayList<IEntity> dungeonEntities = new ArrayList<>();

    public void update() {
        dungeonEntities.removeIf(obj -> obj.deleteable());
        dungeonEntities.forEach(obj -> obj.update());
    }



}
