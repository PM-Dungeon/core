package basiselements.hud;

import basiselements.ScreenElement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import tools.Constants;
import tools.Point;

public class ScreenButton extends TextButton implements ScreenElement {
    /** The default style for every button */
    public static final TextButtonStyle DEFAULT_BUTTON_STYLE;

    static {
        DEFAULT_BUTTON_STYLE = new TextButtonStyle();
        DEFAULT_BUTTON_STYLE.font = new BitmapFont();
        DEFAULT_BUTTON_STYLE.fontColor = Color.BLUE;
        DEFAULT_BUTTON_STYLE.checkedFontColor = Color.YELLOW;
    }

    /**
     * Creates a Button which can be used with the Screencontroller.
     *
     * @param text the text for the ScreenButton
     * @param position the Position where the Screenbutton should be placed 0,0 is bottom left
     * @param listener the TextButtonListener which handles the button press
     */
    public ScreenButton(String text, Point position, TextButtonListener listener) {
        super(text, DEFAULT_BUTTON_STYLE);
        this.setPosition(position.x, position.y);
        this.addListener(listener);
        this.setScale(1 / Constants.DEFAULT_ZOOM_FACTOR);
    }

    @Override
    public Actor getActor() {
        return this;
    }
}
