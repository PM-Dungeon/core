package controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * A controller is a LinkedHashSet and manages elements of a specific type.
 *
 * @param <T> Type of elements to manage.
 */
public abstract class AbstractController<T> extends LinkedHashSet<T> {
    /** Updates all elements that are registered at this controller */
    public abstract void update();

    @Override
    public boolean contains(Object o) {
        assert (o != null);
        return super.contains(o);
    }

    /**
     * Adds the element in front. This is actually not more than a "workaround".
     *
     * <p>The front elements are drawn first, and they are overdrawn with the back elements. This
     * means: elements inserted last will be overdrawn.
     *
     * @param t The element to be added in front.
     * @return true, if success.
     */
    @Override
    public boolean add(T t) {
        assert (t != null);
        ArrayList<T> list = new ArrayList<>(this);
        clear();
        boolean success = super.add(t);
        for (T e : list) {
            super.add(e);
        }
        return success;
    }

    @Override
    public boolean remove(Object o) {
        assert (o != null);
        return super.remove(o);
    }
}
