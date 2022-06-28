package controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Holds the elements required for the inventory screen:
 *  <p>- Inventory Label
 *  <p>- ...
 */
public final class InventoryController extends DungeonScreen{
    
    private Label label = null;
    
    @Override
    public void create(ScreenController controller, SpriteBatch batch) {
        super.create(controller, batch);
        this.label = new Label("Inventory", DungeonGame.getSkin(), "label");
        this.label.setPosition(100, 400);
    }
    
    @Override
    public void show() {
        super.show();
        //TODO
    }

    @Override
    public void render(float delta) {
        super.renderStart();
        super.renderActor(this.label);
        super.renderEnd();       
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.I) {
            super.showGame();
        }
        else {
            //TODO
        }
        return true;
    }
}
