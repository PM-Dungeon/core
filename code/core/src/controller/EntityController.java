package controller;

import interfaces.IEntity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Keeps a set of entities and calls their update method every frame. */
public class EntityController implements IController<IEntity> {
    /** Contains all the entities this controller handles. */
    private final Set<IEntity> dungeonEntities = new LinkedHashSet<>();

    /**
     * Removes deletable entities and calls the update and draw method for every registered entity.
     */
    public void update() {
        dungeonEntities.removeIf(IEntity::removable);
        dungeonEntities.forEach(IEntity::update);
        dungeonEntities.forEach(IEntity::draw);
    }

    @Override
    /** Register an entity. */
    public void add(IEntity element) {
        dungeonEntities.add(element);
    }

    @Override
    /** Removes an entity from the set. */
    public void remove(IEntity element) {
        dungeonEntities.remove(element);
    }

    @Override
    /** Returns <code>true</code> if the entity is registered. */
    public boolean contains(IEntity element) {
        return dungeonEntities.contains(element);
    }

    @Override
    /** Removes all entities from the set. */
    public void removeAll() {
        dungeonEntities.clear();
    }

    @Override
    /** Returns a copy set of all entities. */
    public Set<IEntity> getSet() {
        return new LinkedHashSet<>(dungeonEntities);
    }

    @Override
    /** Returns a copy list of all entities. */
    public List<IEntity> getList() {
        return new ArrayList<>(dungeonEntities);
    }
}
