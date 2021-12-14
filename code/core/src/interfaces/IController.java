package interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IController<T> {
    /** Updates all Elements that are registered at this controller */
    void update();
    /** Registers an element. */
    void add(T element);
    /** Removes an element from the set. */
    void remove(T element);
    /** Removes all elements from the set. */
    void removeAll();
    /** Returns the number of elements in this controller. */
    int size();
    /** Returns <code>true</code> if this controller has no elements. */
    boolean isEmpty();
    /** Returns <code>true</code> if this controller contains all given elements. */
    boolean containsAll(Collection<T> collection);
    /** Returns <code>true</code> if the element is registered. */
    boolean contains(T element);
    /** Returns a copy set of all elements. */
    Set<T> getSet();
    /** Returns a copy list of all elements. */
    List<T> getList();
}
