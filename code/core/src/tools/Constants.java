package tools;

public final class Constants {
    public static final int WINDOW_WIDTH = 640;

    public static final int WINDOW_HEIGHT = 480;

    /** Frames per seconds. */
    public static final int FRAME_RATE = 30;

    /** Virtual width and height. */
    public static final float FIELD_WIDTH_AND_HEIGHT_IN_PIXEL = 16f;

    public static final float VIRTUAL_WIDTH = WINDOW_WIDTH / FIELD_WIDTH_AND_HEIGHT_IN_PIXEL;
    public static final float VIRTUAL_HEIGHT = WINDOW_HEIGHT / FIELD_WIDTH_AND_HEIGHT_IN_PIXEL;

    /** 200% zoom. */
    public static final float DEFAULT_ZOOM_FACTOR = 0.5f;

    public static boolean USE_DUMMY_GENERATOR = false;
    public static boolean USE_LOADER_GENERATOR = true;
    public static boolean USE_DUNGEONG_GENERATOR = false;

    public static final String PATH_TO_LEVEL = "./levelFiles/";
    public static final String PATH_TO_GRAPH = "./graphs/";
    public static final String PATH_TO_ROOM = "./roomTemplates.json";
}
