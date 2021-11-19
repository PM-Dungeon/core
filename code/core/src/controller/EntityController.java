package controller;

import interfaces.IEntity;

import java.util.ArrayList;

/** Keeps a list of entities and calls their update method every frame */
public class EntityController {
    /** Contains all the entities this controller handles. */
    private final ArrayList<IEntity> dungeonEntities;

    /** Keeps a list of entities and calls their update method every frame */
    public EntityController() {
        dungeonEntities = new ArrayList<>();
    }

    /**
     * calls the update method for every entity in the list. removes entity if deletable is set true
     */
    public void update() {
        System.out.println("EntityController update");

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
        if (!dungeonEntities.contains(entity)) dungeonEntities.add(entity);
    }

    /**
     * removes entity from the list
     *
     * @param entity
     * @throws IllegalArgumentException
     */
    public void removeEntity(IEntity entity) throws IllegalArgumentException {
        if (entity == null) throw new IllegalArgumentException("null can not be deleted.");
        if (dungeonEntities.contains(entity)) dungeonEntities.remove(entity);
    }

    /** removes all entities from the list */
    public void removeAll() {
        dungeonEntities.clear();
    }

    /** returns entity list */
    public ArrayList<IEntity> getEntities() {
        return dungeonEntities;
    }
}
