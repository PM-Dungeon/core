package tools;

public final class Constants {
    /* Window width */
    public static final int WIDTH = 640;

    /* Window height */
    public static final int HEIGHT = 480;

    /* Frames per seconds */
    public static final int FRAME_RATE = 30;

    /*
     * Virtual width and height ~~ one grid field size 32 pixel
     */
    public static final float VIRTUAL_WIDTH = WIDTH / 32f;
    public static final float VIRTUAL_HEIGHT = HEIGHT / 32f;

    /* Title of the application */
    @SuppressWarnings("unused")
    public static final String WINDOW_NAME = "PM-Dungeon";

    @SuppressWarnings("unused")
    public static final String PATH_OF_LEVEL = "assets/level/";

    @SuppressWarnings("unused")
    public static final String START_LEVEL = Constants.PATH_OF_LEVEL + "small_dungeon.json";
}
