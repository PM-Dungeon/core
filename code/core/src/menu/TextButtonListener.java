package menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This abstract class is a ClickListener that must be implemented for all screen items. You only
 * need to implement the clicked event method.
 */
public abstract class TextButtonListener extends ClickListener {
    public void setButton(TextButton button) {
        button.addListener(this);
    }

    @Override
    public abstract void clicked(InputEvent event, float x, float y);
}
