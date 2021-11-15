package controller;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainController extends ScreenAdapter {
    private SpriteBatch batch;

    public void setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
    }
}
