package menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreenItem {
    private final TextButton button;

    /**
     * Constructs a new MenuScreenItem as sub menu of a menu button.
     *
     * @param text the text of this item.
     * @param listener the mouse click listener for this item.
     */
    public MenuScreenItem(String text, TextButtonListener listener) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLUE;
        style.checkedFontColor = Color.YELLOW;
        button = new TextButton(text, style);
        listener.setButton(button);
        setFontSize(MenuScreenEntry.defaultScaleXY);
    }

    /**
     * @return the TextButton.
     */
    public TextButton getButton() {
        return button;
    }

    /**
     * Sets the font size of this button.
     *
     * @param scaleXY the scale factor (1 = no rescaling).
     */
    public void setFontSize(float scaleXY) {
        TextButton.TextButtonStyle style = button.getStyle();
        style.font.getData().setScale(scaleXY);
        button.setStyle(style);
    }
}
