package tools;

import interfaces.IController;

import java.util.*;

public abstract class AbstractDefaultController<T> implements IController<T> {

    protected LinkedHashSet<T> set = new LinkedHashSet<>();

    @Override
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        set.add(element);
    }

    @Override
    public void remove(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        set.remove(element);
    }

    @Override
    public void removeAll() {
        set.clear();
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean containsAll(Collection<T> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        return set.containsAll(collection);
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        return set.contains(element);
    }

    @Override
    public Set<T> getSet() {
        return new LinkedHashSet<>(set);
    }

    @Override
    public List<T> getList() {
        return new ArrayList<>(set);
    }
}
