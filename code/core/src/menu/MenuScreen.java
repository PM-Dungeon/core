package menu;

import basiselements.ScreenElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends ScreenAdapter implements ScreenElement {
    private final Stage stage;
    private final Table table;
    private final List<MenuScreenEntry> menuScreenEntries = new ArrayList<>();

    public MenuScreen() {
        SpriteBatch batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport(), batch);

        table = new Table().bottom().left();
        table.defaults().padRight(20);
        table.padLeft(20);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void update() {
        render(Gdx.graphics.getDeltaTime());
    }

    public void addMenuScreenEntry(MenuScreenEntry entry) {
        menuScreenEntries.add(entry);
        updateMenuScreenEntries();
    }

    public void removeMenuScreenEntry(MenuScreenEntry entry) {
        menuScreenEntries.remove(entry);
        updateMenuScreenEntries();
    }

    public void clearMenuScreen() {
        menuScreenEntries.clear();
        updateMenuScreenEntries();
    }

    public void setFontSizeRecursive(float scaleXY) {
        for (MenuScreenEntry entry : menuScreenEntries) {
            entry.setFontSize(scaleXY);
            for (MenuScreenItem de : entry.getEntryButtons()) {
                de.setFontSize(scaleXY);
            }
        }
    }

    private void updateMenuScreenEntries() {
        table.clear();
        for (MenuScreenEntry menu : menuScreenEntries) {
            table.add(menu.getVg());
        }
        table.row();
        for (MenuScreenEntry menu : menuScreenEntries) {
            TextButton b = menu.getMenuButton();
            table.add(b);
            stage.addListener(
                    new ClickListener() {
                        @Override
                        public boolean mouseMoved(InputEvent event, float x, float y) {
                            boolean checked = b.isChecked();
                            boolean isOver = checkIfMouseIsOverButton(menu);
                            if (checked && !isOver) {
                                b.setChecked(false);
                                menu.hideVg();
                            }
                            if (!checked && isOver) {
                                b.setChecked(true);
                                menu.showVg();
                            }
                            return true;
                        }
                    });
        }
    }

    private boolean checkIfMouseIsOverButton(MenuScreenEntry menuScreenEntry) {
        if (menuScreenEntry.getMenuButton().isOver()) {
            return true;
        }
        for (MenuScreenItem e : menuScreenEntry.getEntryButtons()) {
            if (e.getButton().isOver()) {
                return true;
            }
        }
        return false;
    }
}
