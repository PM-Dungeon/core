package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Holds the elements required for the game screen:
 *  <p>- Textures for animation
 *  <p>- ...
 */
public final class GameController extends DungeonScreen{
    
    private int animationSize;
    private TextureRegion[] runAnimation = null;
    private int animationIndex;

    @Override
    public void create(ScreenController controller, SpriteBatch batch) {
        super.create(controller, batch);
        int frameCols = 6;
        int frameRows = 5;
        this.animationSize = frameCols * frameRows;
        Texture animation = new Texture(Gdx.files.internal("animation.png"));
        TextureRegion[][] tmp = TextureRegion.split(animation, animation.getWidth() / frameCols, animation.getHeight() / frameRows);
        this.runAnimation = new TextureRegion[this.animationSize];
        this.animationIndex = 0;
        for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				this.runAnimation[this.animationIndex++] = tmp[i][j];
			}
		}
        this.animationIndex = 0;
    }
    
    @Override
    public void show() {
        super.show();
        //TODO
    }

    @Override
    public void render(float delta) {  
        //Performance tuning with DungeonGame Line 27
        super.renderStart();
        super.renderTexture(this.runAnimation[this.animationIndex], 50.0f,50.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 150.0f,50.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 250.0f,50.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 350.0f,50.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 450.0f,50.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 50.0f,150.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 150.0f,150.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 250.0f,150.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 350.0f,150.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 450.0f,150.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 50.0f,250.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 150.0f,250.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 250.0f,250.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 350.0f,250.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 450.0f,250.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 50.0f,350.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 150.0f,350.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 250.0f,350.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 350.0f,350.0f);
        super.renderTexture(this.runAnimation[this.animationIndex], 450.0f,350.0f);
        super.renderEnd();
        this.animationIndex = (this.animationIndex + 1) % this.animationSize;      
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.I) {
            super.showInventory();
        }
        else {
            //TODO
        }
        return true;
    }
}
