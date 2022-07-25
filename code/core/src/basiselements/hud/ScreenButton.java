package basiselements.hud;

import basiselements.ScreenElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
    private ScreenButton(String text, Point position, TextButtonListener listener, TextButtonStyle style) {
        super(text, style);
        this.setPosition(position.x, position.y);
        this.addListener(listener);
        this.setScale(1 / Constants.DEFAULT_ZOOM_FACTOR);
    }

    /**
     * Creates a ScreenButton which can be used with the Screencontroller. Uses the DEFAULT_BUTTON_STYLE
     *
     * @param text the text for the ScreenButton
     * @param position the Position where the Screenbutton should be placed 0,0 is bottom left
     * @param listener the TextButtonListener which handles the button press
     * @return always a new ScreenButton object after full configuration
     */
    public static ScreenButton build(String text, Point position,TextButtonListener listener){
        return new ScreenButton(text, position,listener,DEFAULT_BUTTON_STYLE);
    }
    /**
     * Creates a ScreenButton which can be used with the Screencontroller fully configurable with a custom font
     *
     * @param text the text which should be written
     * @param position the position for the ScreenButton 0,0 bottom left
     * @param fontColor the color for the font
     * @param borderColor the color for the fontborder
     * @param borderWith the width of the border
     * @param fontPath the internal path inkls. file to the font
     * @param listener the TextButtonListener which
     * @return always a new ScreenButton object after full configuration
     */
    public static ScreenButton build(
        String text,
        Point position,
        Color fontColor,
        Color borderColor,
        float borderWith,
        String fontPath,
        TextButtonListener listener) {
        TextButtonStyle style = new TextButtonStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter parameters =
            new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 16;
        parameters.borderWidth = borderWith;
        parameters.color = fontColor;
        parameters.borderColor = borderColor;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        style.font = generator.generateFont(parameters);
        generator.dispose();

        return new ScreenButton(text, position, listener, style);
    }

    @Override
    public Actor getActor() {
        return this;
    }
}
