package menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class MenuScreenItemListener extends ClickListener {
    public void setButton(TextButton button) {
        button.addListener(this);
    }

    @Override
    public abstract void clicked(InputEvent event, float x, float y);
}
