package controller;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;

public class ScreenControllerTest {
    
    public static void main(String[] args) {
    InventoryController inventoryController = new InventoryController();
    GameController gameController = new GameController();
    DungeonGame game = new DungeonGame();
    game.setInventoryScreen(inventoryController);
    game.setGameScreen(gameController);
    new Lwjgl3Application(game, DungeonGame.getConfiguration());
    }
}
