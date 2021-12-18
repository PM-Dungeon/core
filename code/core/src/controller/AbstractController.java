package controller;

import java.util.LinkedHashSet;

public abstract class AbstractController<T> extends LinkedHashSet<T> {
    /** Updates all Elements that are registered at this controller */
    public abstract void update();

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("object should not be null");
        }
        return super.contains(o);
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("element should not be null");
        }
        return super.add(t);
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("object should not be null");
        }
        return super.remove(o);
    }
}
