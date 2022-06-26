package controller;

import basiselements.Entity;

/** Keeps a set of entities and calls their update method every frame. */
public class EntityController extends AbstractController<Entity> {
    /**
     * Updates all elements that are registered at this controller, removes deletable elements and
     * calls the update and draw method for every registered element.
     */
    @Override
    public void process(Entity e) {
        e.update();
        e.draw();
    }
}
