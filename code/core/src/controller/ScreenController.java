package controller;

public interface ScreenController {
    
    /**
     * Set the current screen to inventory
     */
    public void showInventory();
    
     /**
     * Set the current screen to main game
     */
    public void showGame();

    /*
    TODO Set the current screen to start screen
    
    public void showStart();
    */

    /*
    TODO Set the current screen to end screen
    
    public void showEnd();
    */

    /*
    TODO Set the current screen to pause screen
    
    public void showPause();
    */

    /**
     * Pause the game
     */
    public void pause();

    /**
     * Resume from pause
     */
    public void resume();
}
