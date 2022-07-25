package basiselements.hud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This abstract class is a ClickListener that must be implemented for all screen items. You only
 * need to implement the clicked event method.
 */
public abstract class TextButtonListener extends ClickListener {

    @Override
    public abstract void clicked(InputEvent event, float x, float y);
}
