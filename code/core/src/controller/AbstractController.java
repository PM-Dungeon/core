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
     * Adds the element in front.
     *
     * <p>Elements that you add first with this method are drawn last, so they are drawn on top of
     * the other elements. New elements added with this method are overdrawn with older ones.
     *
     * <p>Duplicates are not replaced or inserted, this is how HashSet normally behaves.
     *
     * @param t The element to be added in front.
     * @return true, if success.
     */
    @Override
    public boolean add(T t) {
        assert (t != null);
        if (contains(t)) {
            return false;
        }
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
