package controller;

import interfaces.IEntity;
import tools.AbstractDefaultController;

/** Keeps a set of entities and calls their update method every frame. */
public class EntityController extends AbstractDefaultController<IEntity> {
    /**
     * Removes deletable entities and calls the update and draw method for every registered entity.
     */
    public void update() {
        set.removeIf(IEntity::removable);
        set.forEach(IEntity::update);
        set.forEach(IEntity::draw);
    }
}
