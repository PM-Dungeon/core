package controller;

import java.util.*;
import java.util.function.Predicate;

/**
 * A controller manages elements of a specific type, null and duplicates are not allowed.
 *
 * @param <T> Type of elements to manage.
 */
public abstract class AbstractController<T> extends ArrayDeque<T> {
    /** Updates all elements that are registered at this controller */
    public abstract void update();

    private final Set<T> set = new HashSet<>();

    @Override
    public boolean contains(Object o) {
        assert (o != null);
        return set.contains(o);
    }

    @Override
    public void addFirst(T t) {
        if (contains(t)) {
            return;
        }
        set.add(t);
        super.addFirst(t);
    }

    @Override
    public void addLast(T t) {
        if (contains(t)) {
            return;
        }
        set.add(t);
        super.addLast(t);
    }

    @Override
    public T pollFirst() {
        T e = super.pollFirst();
        set.remove(e);
        return e;
    }

    @Override
    public T pollLast() {
        T e = super.pollLast();
        set.remove(e);
        return e;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        set.removeIf(filter);
        return super.removeIf(filter);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (super.removeFirstOccurrence(o)) {
            @SuppressWarnings("unchecked")
            T e = (T) o;
            set.remove(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (super.removeLastOccurrence(o)) {
            @SuppressWarnings("unchecked")
            T e = (T) o;
            set.remove(e);
            return true;
        }
        return false;
    }
}
