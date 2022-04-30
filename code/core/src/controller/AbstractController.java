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

    @Override
    public boolean add(T t) {
        assert (t != null);
        return super.add(t);
    }

    @Override
    public boolean remove(Object o) {
        assert (o != null);
        return super.remove(o);
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
    public boolean addFirst(T t) {
        assert (t != null);
        if (super.contains(t)) {
            return false;
        }
        ArrayList<T> list = new ArrayList<>(this);
        super.clear();
        super.add(t);
        return super.addAll(list);
    }
}
