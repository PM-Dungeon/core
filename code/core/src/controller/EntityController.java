package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import interfaces.IEntity;
import java.util.ArrayList;

public class EntityController {
    /** Contains all the entities this controller handles. */
    private final ArrayList<IEntity> dungeonEntities;

    public EntityController() {
        this.dungeonEntities = new ArrayList<>();
    }

    /**
     * calls the update method for every entity in the list. removes entity if deletable is set true
     */
    public void update() {
        dungeonEntities.removeIf(obj -> obj.removable());
        dungeonEntities.forEach(obj -> obj.update());
    }

    /**
     * add an entity to the list
     *
     * @param entity
     * @throws IllegalArgumentException
     */
    public void addEntity(IEntity entity) throws IllegalArgumentException {
        if (entity == null) throw new IllegalArgumentException("null can not be added.");
        if (!dungeonEntities.contains(entity)) this.dungeonEntities.add(entity);
    }

    /**
     * removes entity from the list
     *
     * @param entity
     * @throws IllegalArgumentException
     */
    public void removeEntity(IEntity entity) throws IllegalArgumentException {
        if (entity == null) throw new IllegalArgumentException("null can not be deleted.");
        if (dungeonEntities.contains(entity)) this.dungeonEntities.remove(entity);
    }

    /** removes all entities from the list */
    public void removeAll() {
        this.dungeonEntities.clear();
    }

    /** returns entity list */
    public ArrayList<IEntity> getList() {
        return this.dungeonEntities;
    }
}
