package tools;

public final class Constants {
    /* Window width */
    public static final int WIDTH = 640;

    /* Window height */
    public static final int HEIGHT = 480;

    /* Frames per seconds */
    public static final int FRAME_RATE = 30;

    /*
     * Virtual width and height ~~ size of a grid field (in pixel)
     */
    public static final float VIRTUAL_WIDTH = WIDTH / 16f;
    public static final float VIRTUAL_HEIGHT = HEIGHT / 16f;
    public static final float DEFAULT_ZOOM_FACTOR = 0.5f;

    /* Title of the application */
    @SuppressWarnings("unused")
    public static final String WINDOW_NAME = "PM-Dungeon";

    @SuppressWarnings("unused")
    public static final String PATH_OF_LEVEL = "assets/level/";

    @SuppressWarnings("unused")
    public static final String START_LEVEL = Constants.PATH_OF_LEVEL + "small_dungeon.json";
}
