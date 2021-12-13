package level.levelg;

import level.DesignLabel;
import level.LevelElement;
import tools.Point;

public class TileTextureFactory {


    public static String findTexture(LevelElement e, DesignLabel l, LevelElement[][] layout, Point p) {
        String path;
        if (l == DesignLabel.FIRE)
            path = "fire/";
        else if (l == DesignLabel.FOREST)
            path = "forest/";
        else if (l == DesignLabel.WATER)
            path = "water/";
        else if (l == DesignLabel.HELL)
            path = "hell/";
        else if (l == DesignLabel.DUNGEON)
            path = "dungeon/";
        else if (l == DesignLabel.ICE)
            path = "ice/";
        else
            path = "default/";


        if (e == LevelElement.FLOOR)
            path += "floor/floor_1";
        else if (e == LevelElement.EXIT)
            path += "floor/floor_ladder";


            //walls
        else if (isRightWall(p, layout))
            path += "wall/right";
        else if (isLeftWall(p, layout))
            path += "wall/left";
        else if (isSideWall(p, layout))
            path += "wall/side";
        else if (isTopWall(p, layout))
            path += "wall/top";
        else if (isBottomWall(p, layout))
            path += "wall/bottom";
        else if (isBottomAndTopWall(p, layout))
            path += "wall/top_bottom";


            //corners
        else if (isBottomLeftCorner(p, layout))
            path += "wall/corner_bottom_left";
        else if (isBottomRightCorner(p, layout))
            path += "wall/corner_bottom_right";
        else if (isUpperRightCorner(p, layout))
            path += "wall/corner_upper_right";
        else if (isUpperLeftCorner(p, layout))
            path += "wall/corner_upper_left";

            //fehler zustand
        else
            path += "floor/floor_1";


        return "textures/dungeon/" + path + ".png";
    }


    private static boolean isBottomLeftCorner(Point p, LevelElement[][] layout) {
        return (aboveIsWall(p, layout) && rightIsWall(p, layout));
    }

    private static boolean isBottomRightCorner(Point p, LevelElement[][] layout) {
        return (aboveIsWall(p, layout) && leftIsWall(p, layout));
    }

    private static boolean isUpperRightCorner(Point p, LevelElement[][] layout) {
        return (belowIsWall(p, layout) && leftIsWall(p, layout));
    }

    private static boolean isUpperLeftCorner(Point p, LevelElement[][] layout) {
        return (belowIsWall(p, layout) && rightIsWall(p, layout));
    }


    private static boolean isRightWall(Point p, LevelElement[][] layout) {
        return aboveIsWall(p, layout) && belowIsWall(p, layout) && leftIsFloor(p, layout) && !rightIsFloor(p, layout);
    }

    private static boolean isLeftWall(Point p, LevelElement[][] layout) {
        return aboveIsWall(p, layout) && belowIsWall(p, layout) && !leftIsFloor(p, layout) && rightIsFloor(p, layout);
    }

    private static boolean isSideWall(Point p, LevelElement[][] layout) {
        return aboveIsWall(p, layout) && belowIsWall(p, layout) && leftIsFloor(p, layout) && rightIsFloor(p, layout);
    }


    private static boolean isTopWall(Point p, LevelElement[][] layout) {
        return belowIsFloor(p, layout) && !aboveIsFloor(p, layout) && leftIsWall(p, layout) && rightIsWall(p, layout);
    }

    private static boolean isBottomWall(Point p, LevelElement[][] layout) {
        return !belowIsFloor(p, layout) && aboveIsFloor(p, layout) && leftIsWall(p, layout) && rightIsWall(p, layout);
    }

    private static boolean isBottomAndTopWall(Point p, LevelElement[][] layout) {
        return belowIsFloor(p, layout) && aboveIsFloor(p, layout) && leftIsWall(p, layout) && rightIsWall(p, layout);
    }


    private static boolean aboveIsWall(Point p, LevelElement[][] layout) {
        try {
            return layout[(int) p.y + 1][(int) p.x] == LevelElement.WALL;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean belowIsWall(Point p, LevelElement[][] layout) {
        try {
            return layout[(int) p.y - 1][(int) p.x] == LevelElement.WALL;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean leftIsWall(Point p, LevelElement[][] layout) {
        try {
            return layout[(int) p.y][(int) p.x - 1] == LevelElement.WALL;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean rightIsWall(Point p, LevelElement[][] layout) {
        try {
            return layout[(int) p.y][(int) p.x + 1] == LevelElement.WALL;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean aboveIsFloor(Point p, LevelElement[][] layout) {
        try {
            return layout[(int) p.y + 1][(int) p.x] == LevelElement.FLOOR ||
                layout[(int) p.y + 1][(int) p.x] == LevelElement.EXIT;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean belowIsFloor(Point p, LevelElement[][] layout) {
        try {
            return layout[(int) p.y - 1][(int) p.x] == LevelElement.FLOOR ||
                layout[(int) p.y + 1][(int) p.x] == LevelElement.EXIT;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean leftIsFloor(Point p, LevelElement[][] layout) {
        try {
            return layout[(int) p.y][(int) p.x - 1] == LevelElement.FLOOR ||
                layout[(int) p.y + 1][(int) p.x] == LevelElement.EXIT;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean rightIsFloor(Point p, LevelElement[][] layout) {
        try {
            return layout[(int) p.y][(int) p.x + 1] == LevelElement.FLOOR ||
                layout[(int) p.y + 1][(int) p.x] == LevelElement.EXIT;

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

}
