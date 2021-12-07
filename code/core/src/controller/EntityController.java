package controller;

import interfaces.IEntity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Keeps a set of entities and calls their update method every frame. */
public class EntityController {
    /** Contains all the entities this controller handles. */
    private final Set<IEntity> dungeonEntities;

    /** Keeps a set of entities and calls their update method every frame. */
    public EntityController() {
        dungeonEntities = new LinkedHashSet<>();
    }

    /**
     * Calls the update method for every entity in the list. Removes entity if deletable is set to
     * true.
     */
    public void update() {
        dungeonEntities.removeIf(IEntity::removable);
        dungeonEntities.forEach(IEntity::update);
        dungeonEntities.forEach(IEntity::draw);
    }

    /** Adds an entity to the set. */
    public void addEntity(IEntity entity) {
        dungeonEntities.add(entity);
    }

    /** Removes entity from the set. */
    public void removeEntity(IEntity entity) {
        dungeonEntities.remove(entity);
    }

    /** Removes all entities from the set. */
    public void removeAll() {
        dungeonEntities.clear();
    }

    /** Returns a list of all entities. */
    public List<IEntity> getEntities() {
        return new ArrayList<>(dungeonEntities);
    }
}
