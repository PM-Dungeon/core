package controller;

import java.util.List;
import java.util.Set;

public interface IController<T> {

    /** Updates all Elements that are registered at this controller */
    public void update();
    /** Register an element. */
    public void add(T element);
    /** Removes an element from the set. */
    public void remove(T element);
    /** Removes all elements from the set. */
    public void removeAll();
    /** Returns <code>true</code> if the element is registered. */
    public boolean contains(T element);
    /** Returns a copy set of all elements. */
    public Set<T> getSet();
    /** Returns a copy list of all elements. */
    public List<T> getList();
}
