package menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import java.util.ArrayList;
import java.util.List;

public class MenuScreenEntry {
    public static final float defaultScaleXY = 1f;
    private final TextButton menuButton;
    private final List<MenuScreenItem> entryButtons = new ArrayList<>();
    private final VerticalGroup vg = new VerticalGroup();

    /**
     * Constructs a new MenuScreenEntry resp. a new TextButton.
     *
     * @param menuText the TextButton text.
     */
    public MenuScreenEntry(String menuText) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLUE;
        style.checkedFontColor = Color.YELLOW;
        menuButton = new TextButton(menuText, style);
        setFontSize(defaultScaleXY);
    }

    /**
     * Adds a MenuScreenItem as sub menu to this menu button.
     *
     * @param entry to add.
     */
    public void add(MenuScreenItem entry) {
        entryButtons.add(entry);
    }

    /**
     * Removes the given MenuScreenItem from this menu button.
     *
     * @param entry to remove.
     */
    public void remove(MenuScreenItem entry) {
        entryButtons.remove(entry);
    }

    /** Removes all MenuScreenItems from this menu button. */
    public void clear() {
        entryButtons.clear();
    }

    /** When mouse is over this menu button. */
    public void showVg() {
        vg.clear();
        for (MenuScreenItem e : entryButtons) {
            vg.addActor(e.getButton());
        }
    }

    /** When mouse is no longer over this menu button. */
    public void hideVg() {
        for (MenuScreenItem e : entryButtons) {
            e.getButton().setChecked(false);
        }
        vg.clear();
    }

    /**
     * Sets the font size of this menu button.
     *
     * @param scaleXY the scale factor (1 = no rescaling).
     */
    public void setFontSize(float scaleXY) {
        TextButton.TextButtonStyle style = menuButton.getStyle();
        style.font.getData().setScale(scaleXY);
        menuButton.setStyle(style);
    }

    /**
     * @return the menu button.
     */
    public TextButton getMenuButton() {
        return menuButton;
    }

    /**
     * @return a list with all menu screen item of this menu button.
     */
    public List<MenuScreenItem> getEntryButtons() {
        return entryButtons;
    }

    /**
     * @return the VerticalGroup of this menu button.
     */
    public VerticalGroup getVg() {
        return vg;
    }
}
