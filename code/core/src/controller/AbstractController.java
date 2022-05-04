package controller;

import java.util.*;

/**
 * A controller is a LinkedHashSet and manages elements of a specific type.
 *
 * @param <T> Type of elements to manage.
 */
public abstract class AbstractController<T> implements Iterable<T> {
    /** Updates all elements that are registered at this controller */
    public abstract void update();

    private final TreeMap<ControllerLayer, List<T>> map =
            new TreeMap<>(Comparator.comparingInt(o -> o.priority));

    public boolean contains(T t) {
        assert (t != null);
        for (List<T> l : map.values()) {
            if (l.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(T t) {
        assert (t != null);
        return add(t, ControllerLayer.DEFAULT);
    }

    public boolean add(T t, ControllerLayer layer) {
        assert (t != null);
        return map.computeIfAbsent(layer, x -> new ArrayList<>()).add(t);
    }

    public boolean remove(T t) {
        assert (t != null);
        boolean success = false;
        for (List<T> l : map.values()) {
            success |= l.remove(t);
        }
        return success;
    }

    public boolean remove(T t, ControllerLayer layer) {
        assert (t != null);
        return map.computeIfAbsent(layer, x -> new ArrayList<>()).remove(t);
    }

    public boolean isEmpty() {
        for (List<T> l : map.values()) {
            if (!l.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        List<T> list = new ArrayList<>();
        for (List<T> l : map.values()) {
            list.addAll(l);
        }
        return list.iterator();
        // will not work with PowerMock:
        // return map.values().stream().flatMap(List::stream).toList().iterator();
    }
}
