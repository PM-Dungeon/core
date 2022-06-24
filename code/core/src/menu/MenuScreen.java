package menu;

import basiselements.DungeonElement;
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
import tools.Point;

public class MenuScreen extends ScreenAdapter implements DungeonElement {
    private final SpriteBatch batch;
    private final Stage stage;
    private final Table table;
    private final List<MenuScreenEntry> menuScreenEntries = new ArrayList<>();

    public MenuScreen() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport(), batch);

        // Create a Table to group the TextButton. Place the table in the bottom left corner. Each
        // TextButton should have a 20 pixel distance
        // to the next one.
        table = new Table().bottom().left();
        table.defaults().padRight(20);
        table.padLeft(20);

        // Add the table to the stage.
        stage.addActor(table);

        // Register the InputProcessor to the stage and a ClickListener to button1.
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    /*
    This method is not called because it is not registered yet, in LibgdxSetup.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public String getTexturePath() {
        return null;
    }

    @Override
    public void update() {
        // crop super call
    }

    @Override
    public void draw() {
        render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public SpriteBatch getBatch() {
        return batch;
    }

    // further methods

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

    public void updateMenuScreenEntries() {
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

    public boolean checkIfMouseIsOverButton(MenuScreenEntry menuScreenEntry) {
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

    public void setFontSizeRecursive(float scaleXY) {
        for (MenuScreenEntry entry : menuScreenEntries) {
            entry.setFontSize(scaleXY);
            for (MenuScreenItem de : entry.getEntryButtons()) {
                de.setFontSize(scaleXY);
            }
        }
    }
}
