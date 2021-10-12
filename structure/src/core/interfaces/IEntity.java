package core.interfaces;

/** Must be implemented for all objects that should be controlled by the EntityControl */
public interface IEntity {

    /**
     *     Will be executed every frame. Remember to draw/animate your drawable objects
     */

    void update();

    /**
     * @return if this instance can be deleted (than will be removed from EntityControl
     *     list);
     */
    boolean deleteable();
}
