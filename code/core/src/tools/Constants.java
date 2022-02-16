package tools;

import java.net.URL;

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

    public static final boolean DISABLE_REPLACEMENTS = true;

    public static String getPathToLevel() {
        URL url = ClassLoader.getSystemClassLoader().getResource("level/files");
        assert (url != null);
        return url.getPath();
    }

    public static String getPathToGraph() {
        URL url = ClassLoader.getSystemClassLoader().getResource("level/graphs");
        assert (url != null);
        return url.getPath();
    }

    public static String getPathToRoomTemplates() {
        URL url = ClassLoader.getSystemClassLoader().getResource("level/roomTemplates.json");
        assert (url != null);
        return url.getPath();
    }

    public static String getPathToReplacements() {
        URL url = ClassLoader.getSystemClassLoader().getResource("level/replacements.json");
        assert (url != null);
        return url.getPath();
    }
}
