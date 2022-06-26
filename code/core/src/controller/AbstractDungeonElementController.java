package controller;

import basiselements.DungeonElement;
import java.util.*;

/**
 * A controller manages elements of a certain type and is based on a layer system.
 *
 * <p>Layer system means: All elements are listed in ascending order according to the layers in this
 * controller and in the order they are arranged in the layer.
 *
 * @param <T> Type of elements to manage.
 */
public abstract class AbstractDungeonElementController<T extends DungeonElement>
        extends AbstractController<T> {

    /**
     * Updates all elements that are registered at this controller, removes deletable elements and
     * calls the update and draw method for every registered element.
     */
    @Override
    public void process(T e) {
        e.update();
        e.draw();
    }
}
