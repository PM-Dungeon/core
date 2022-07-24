package basiselements.hud;

import basiselements.ScreenElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import tools.Point;

public class ScreenText extends Label implements ScreenElement {
    /** Allows the dynamic configuration of the default style for the generated ScreenTexts */
    public static final LabelStyle DEFAULT_LABEL_STYLE;

    static {
        DEFAULT_LABEL_STYLE = new LabelStyle();
        DEFAULT_LABEL_STYLE.font = new BitmapFont();
        DEFAULT_LABEL_STYLE.fontColor = Color.BLUE;
    }

    /**
     * Creates a Text with the default label style at the given position
     *
     * @param text the text which should be written
     * @param position the Point where the ScreenText should be written 0,0 bottom left
     */
    private ScreenText(String text, LabelStyle style, Point position, float scaleXY) {
        super(text, style);
        this.setPosition(position.x, position.y);
        this.setScale(scaleXY);
    }

    /**
     * Creates the ScreenText fully configurable with a custom font
     *
     * @param text the text which should be written
     * @param position the position for the ScreenText 0,0 bottom left
     * @param fontColor the color for the font
     * @param borderColor the color for the fontborder
     * @param borderWith the width of the border
     * @param fontPath the internal path inkls. file to the font
     * @param scaleXY the scale for the ScreenText
     * @return always a new ScreenText object after full configuration
     */
    public static ScreenText build(
            String text,
            Point position,
            Color fontColor,
            Color borderColor,
            float borderWith,
            String fontPath,
            float scaleXY) {
        LabelStyle labelStyle = new LabelStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter parameters =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 16;
        parameters.borderWidth = 1;
        parameters.color = Color.SKY;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        labelStyle.font = generator.generateFont(parameters);
        generator.dispose();

        return new ScreenText(text, labelStyle, position, scaleXY);
    }

    /**
     * Creates the ScreenText with the default style
     *
     * @param text the text which should be written
     * @param position the position for the ScreenText 0,0 bottom left
     * @param scaleXY the scale for the ScreenText
     * @return always a new ScreenText object after full configuration
     */
    public static ScreenText build(String text, Point position, float scaleXY) {
        return new ScreenText(text, DEFAULT_LABEL_STYLE, position, scaleXY);
    }

    @Override
    public Actor getActor() {
        return this;
    }
}
