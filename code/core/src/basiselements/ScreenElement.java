package basiselements;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface ScreenElement extends RemovableElement {

    /**
     * When the Element is removed from the Screencontroller. Libgdx forbids the removal of the
     * Actor from the Stage site and every Actor can remove itself from the Stage.
     *
     * @return
     */
    boolean remove();

    /**
     * Create a representation of the ScreenElement as libgdx Actor.
     *
     * @return the Actor representation of the ScreenElement
     */
    Actor getActor();
}
