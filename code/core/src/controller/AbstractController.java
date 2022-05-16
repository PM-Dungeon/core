package controller;

import basiselements.DungeonElement;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A controller manages elements of a certain type and is based on a layer system.
 *
 * @param <T> Type of elements to manage.
 */
public abstract class AbstractController<T extends DungeonElement> extends LinkedHashSet<T>
        implements Iterable<T> {

    private final Map<ControllerLayer, List<T>> layerTreeMap = new TreeMap<>();
    private final Map<T, List<T>> elementHashMap = new HashMap<>();

    /**
     * Updates all elements that are registered at this controller, removes deletable elements and
     * calls the update and draw method for every registered element.
     */
    public void update() {
        for (T e : this) {
            if (e.removable()) {
                remove(e);
            } else {
                e.update();
                e.draw();
            }
        }
    }

    /**
     * Returns true, if the element is in this controller.
     *
     * @param t Element to look for.
     * @return true, if the element is in this controller.
     */
    @Override
    public boolean contains(Object t) {
        assert (t != null);
        return elementHashMap.containsKey(t);
    }

    /**
     * Adds the element with default layer (20) to this controller, if it is not already added.
     *
     * @param t Element to add.
     * @return true, if this was successful.
     */
    @Override
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
        layerTreeMap.computeIfAbsent(layer, x -> new ArrayList<>()).add(t);
        elementHashMap.put(t, layerTreeMap.get(layer));
        return true;
    }

    /**
     * Removes the element from this controller, if it is in this controller.
     *
     * @param t Element to remove.
     * @return true, if this was successful.
     */
    @Override
    public boolean remove(Object t) {
        assert (t != null);
        if (!contains(t)) {
            return false;
        }
        elementHashMap.get(t).remove(t);
        elementHashMap.remove(t);
        return true;
    }

    /**
     * Tests, if this controller has no elements.
     *
     * @return true, if empty.
     */
    @Override
    public boolean isEmpty() {
        return elementHashMap.isEmpty();
    }

    /** Clears the entire controller (removes all elements). */
    @Override
    public void clear() {
        layerTreeMap.clear();
        elementHashMap.clear();
    }

    /** Removes all elements in the specific layer. */
    public void clearLayer(ControllerLayer layer) {
        for (T t : layerTreeMap.get(layer)) {
            elementHashMap.remove(t);
        }
        layerTreeMap.get(layer).clear();
    }

    /**
     * @return An unmodifiable iterator.
     */
    @Override
    public Iterator<T> iterator() {
        // creates a list copy of merged lists
        List<T> list = new ArrayList<>();
        for (List<T> l : layerTreeMap.values()) {
            list.addAll(l);
        }
        return list.iterator();
        // will not work with PowerMock:
        // return map.values().stream().flatMap(List::stream).toList().iterator();
    }

    @Override
    public int size() {
        return elementHashMap.size();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        iterator().forEachRemaining(action);
    }

    @Override
    public String toString() {
        return elementHashMap.keySet().toString();
    }

    // not needed methods
    // from LinkedHashSet, HashSet, AbstractSet, AbstractCollection and Collection
    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }

    @Override
    public Object clone() {
        throw new UnsupportedOperationException("please don't use this method");
    }

    @Override
    public Object[] toArray() {
        return elementHashMap.keySet().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return elementHashMap.keySet().toArray(a);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        for (Object e : c) {
            modified |= remove(e);
        }
        return modified;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elementHashMap.keySet().containsAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        for (T e : this) {
            if (!c.contains(e)) {
                remove(e);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return stream().toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return removeAll(stream().filter(filter).toList());
    }

    @Override
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
