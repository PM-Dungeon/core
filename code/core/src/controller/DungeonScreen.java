package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class DungeonScreen implements Screen, InputProcessor{

    private ScreenController controller;
    private SpriteBatch batch;

    public void create(ScreenController controller, SpriteBatch batch) {
        this.controller = controller;
        this.batch = batch;
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Call before render
     */
    public void renderStart() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
    }

    /**
     * Render textures
     */
    public void renderTexture(TextureRegion region, float x, float y) {
        this.batch.draw(region, x, y);;
    }

    /**
     * Rendes actors
     */
    public void renderActor(Actor actor) {
        actor.draw(this.batch, 1.0f);
    }

    /**
     * Call after render
     */
    public void renderEnd() {
        this.batch.end();
        //Performance test
        System.out.println("Render calls: " + String.valueOf(this.batch.renderCalls));
    }

    @Override
    public abstract void render(float delta);

    @Override
    public void resize(int width, int height) {    
    }

    @Override
    public void pause() {
        this.controller.pause();
    }

    @Override
    public void resume() {
        this.controller.resume();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public abstract boolean keyDown(int keycode);

    public void showInventory() {
        this.controller.showInventory();
    }

    public void showGame() {
        this.controller.showGame();
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return true;
    } 
}
