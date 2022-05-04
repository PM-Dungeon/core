package controller;

import java.util.*;

/**
 * A controller manages elements of a certain type and is based on a layer system.
 *
 * @param <T> Type of elements to manage.
 */
public abstract class AbstractController<T> implements Iterable<T> {
    /** Updates all elements that are registered at this controller */
    public abstract void update();

    private final Map<ControllerLayer, List<T>> map = new TreeMap<>();
    private final Map<T, List<T>> map2 = new HashMap<>();

    /**
     * Returns true, if the element is in this controller.
     *
     * @param t Element to look for.
     * @return true, if the element is in this controller.
     */
    public boolean contains(T t) {
        assert (t != null);
        return map2.containsKey(t);
    }

    /**
     * Adds the element with default layer (20) to this controller, if it is not already added.
     *
     * @param t Element to add.
     * @return true, if this was successful.
     */
    public boolean add(T t) {
        assert (t != null);
        return add(t, ControllerLayer.DEFAULT);
    }

    /**
     * Adds the element with the specific layer to this controller, if it is not already added.
     *
     * @param t Element to add.
     * @param layer Layer to add the element in.
     * @return true, if this was successful.
     */
    public boolean add(T t, ControllerLayer layer) {
        assert (t != null);
        if (contains(t)) {
            return false;
        }
        map.computeIfAbsent(layer, x -> new ArrayList<>()).add(t);
        map2.put(t, map.get(layer));
        return true;
    }

    /**
     * Removes the element from this controller, if it is in this controller.
     *
     * @param t Element to remove.
     * @return true, if this was successful.
     */
    public boolean remove(T t) {
        assert (t != null);
        if (!contains(t)) {
            return false;
        }
        map2.get(t).remove(t);
        map2.remove(t);
        return true;
    }

    /**
     * Tests, if this controller has no elements.
     *
     * @return true, if empty.
     */
    public boolean isEmpty() {
        return map2.isEmpty();
    }

    /** Clears the entire controller (removes all elements). */
    public void clear() {
        map.clear();
        map2.clear();
    }

    /** Removes all elements in the specific layer. */
    public void clearLayer(ControllerLayer layer) {
        for (T t : map.get(layer)) {
            map2.remove(t);
        }
        map.get(layer).clear();
    }

    @Override
    public Iterator<T> iterator() {
        // creates a list copy of merged lists
        List<T> list = new ArrayList<>();
        for (List<T> l : map.values()) {
            list.addAll(l);
        }
        return list.iterator();
        // will not work with PowerMock:
        // return map.values().stream().flatMap(List::stream).toList().iterator();
    }
}
