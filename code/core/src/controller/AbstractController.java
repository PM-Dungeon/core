package controller;

import java.util.*;
import java.util.function.Predicate;

/**
 * A controller is a LinkedHashSet and manages elements of a specific type.
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
}
