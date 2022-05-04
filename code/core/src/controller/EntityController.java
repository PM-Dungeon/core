package controller;

import basiselements.Entity;

/** Keeps a set of entities and calls their update method every frame. */
public class EntityController extends AbstractController<Entity> {
    /**
     * Removes deletable entities and calls the update and draw method for every registered entity.
     */
    public void update() {
        for (Entity e : this) {
            if (e.removable()) {
                remove(e);
            }
        }
        forEach(Entity::update);
        forEach(Entity::draw);
    }
}
