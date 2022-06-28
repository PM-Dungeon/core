package controller;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public final class DungeonGame implements ApplicationListener, ScreenController{

    private Screen currentScreen = null;
    private int screenWidth;
    private int screenHeight;
    public static final int FRAME_RATE = 30;
    public static final String WINDOW_TITLE = "PM Dungeon";
    public static final String LOGO_PATH = "logo/logo32x32.png";
    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;
    private static Lwjgl3ApplicationConfiguration config = null;
    private static Skin skin = null;
    private boolean isRunning;
    private SpriteBatch mainBatch = null;
    private DungeonScreen inventoryScreen = null;
    private DungeonScreen gameScreen = null;
    
    @Override
    public void create() {
        this.mainBatch = new SpriteBatch(20);//default is 1000
        this.inventoryScreen.create(this, this.mainBatch);
        this.gameScreen.create(this, this.mainBatch);
        this.currentScreen = this.gameScreen;
        this.currentScreen.show();
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.isRunning = true;
    }

    /**
     * Set the screen which shows the inventory of the main character
     */
    public void setInventoryScreen(DungeonScreen inventoryScreen) {
        this.inventoryScreen = inventoryScreen;
    }

    /**
     * Set the screen which shows the main game
     */
    public void setGameScreen(DungeonScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    private void setCurrenScreen(Screen screen) {
        this.currentScreen.hide();
        this.currentScreen = screen;
        this.currentScreen.show();
        this.currentScreen.resize(this.screenWidth, this.screenHeight);
    }

    @Override
    public void showInventory() {
        this.setCurrenScreen(this.inventoryScreen);
    }

    @Override
    public void showGame() {
        this.setCurrenScreen(this.gameScreen);
    }
    
    @Override
    public void resize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight= height;
        if(this.currentScreen != null) {
            this.currentScreen.resize(width, height);
        }
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        if(this.isRunning) {
            this.currentScreen.render(delta);
        }
        else {
            //TODO Show Pause Screen
        }
    }

    @Override
    public void pause() {
        this.isRunning = false;
    }

    @Override
    public void resume() {
        this.isRunning = true;
    }

    @Override
    public void dispose() {
        this.inventoryScreen.dispose();
        this.gameScreen.dispose();
        this.mainBatch.dispose();
    }

    public static Lwjgl3ApplicationConfiguration getConfiguration(){
        if(DungeonGame.config == null) {
            DungeonGame.config = new Lwjgl3ApplicationConfiguration();
            DungeonGame.config.setWindowSizeLimits(DungeonGame.WINDOW_WIDTH, DungeonGame.WINDOW_HEIGHT, 9999, 9999);
            DungeonGame.config.setForegroundFPS(DungeonGame.FRAME_RATE);
            DungeonGame.config.setTitle(DungeonGame.WINDOW_TITLE);
            DungeonGame.config.setWindowIcon(DungeonGame.LOGO_PATH);
        }
        return DungeonGame.config;
    }

    public static Skin getSkin() {
        if(DungeonGame.skin == null) {
            DungeonGame.skin = new Skin();
            DungeonGame.skin.add("label", new LabelStyle(new BitmapFont(), Color.WHITE));
        }
        return DungeonGame.skin;
    }
}
