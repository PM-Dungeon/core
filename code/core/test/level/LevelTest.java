package level;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import basiselements.Entity;
import com.badlogic.gdx.ai.pfa.GraphPath;
import level.elements.Level;
import level.elements.Tile;
import level.tools.Coordinate;
import level.tools.DesignLabel;
import level.tools.LevelElement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tools.Point;

public class LevelTest {

    private Level level;
    private Tile[][] layout;
    private Tile endTile;
    private Tile startTile;

    @Before
    public void setup() {
        layout = new Tile[3][3];
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++) {
                if (x < 2)
                    layout[y][x] =
                            new Tile(
                                    "",
                                    new Coordinate(x, y),
                                    LevelElement.FLOOR,
                                    DesignLabel.DEFAULT);
                else
                    layout[y][x] =
                            new Tile(
                                    "",
                                    new Coordinate(x, y),
                                    LevelElement.WALL,
                                    DesignLabel.DEFAULT);
            }

        level = new Level(layout);
        endTile = level.getEndTile();
        startTile = level.getStartTile();
    }

    @Test
    public void test_levelCTOR_LevelElements() {
        LevelElement[][] elementsLayout = new LevelElement[2][2];
        elementsLayout[0][0] = LevelElement.WALL;
        elementsLayout[0][1] = LevelElement.FLOOR;
        elementsLayout[1][0] = LevelElement.WALL;
        elementsLayout[1][1] = LevelElement.FLOOR;
        level = new Level(elementsLayout, DesignLabel.DEFAULT);
        Tile[][] layout = level.getLayout();
        assertTrue(elementsLayout[0][0] == layout[0][0].getLevelElement());
        assertTrue(elementsLayout[1][0] == layout[1][0].getLevelElement());
        assertTrue(
                elementsLayout[0][1] == layout[0][1].getLevelElement()
                        || LevelElement.EXIT == layout[0][1].getLevelElement());
        assertTrue(
                elementsLayout[1][1] == layout[1][1].getLevelElement()
                        || LevelElement.EXIT == layout[1][1].getLevelElement());
    }

    @Test
    public void test_findPath_onlyOnePathPossible() {
        layout = new Tile[3][3];
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                layout[y][x] =
                        new Tile("", new Coordinate(x, y), LevelElement.FLOOR, DesignLabel.DEFAULT);
        layout[1][1] = new Tile("", new Coordinate(1, 1), LevelElement.WALL, DesignLabel.DEFAULT);
        layout[0][1] = new Tile("", new Coordinate(0, 1), LevelElement.WALL, DesignLabel.DEFAULT);
        level = new Level(layout);
        level.setStartTile(layout[0][0]);
        level.setEndTile(layout[0][2]);
        /** How the level layout looks: (S=start, W=Wall,F=Floor,E=exit) SWE FWF FFF */
        GraphPath<Tile> path = level.findPath(level.getStartTile(), level.getEndTile());
        assertEquals(7, path.getCount());
        assertEquals(layout[0][0], path.get(0));
        assertEquals(layout[1][0], path.get(1));
        assertEquals(layout[2][0], path.get(2));
        assertEquals(layout[2][1], path.get(3));
        assertEquals(layout[2][2], path.get(4));
        assertEquals(layout[1][2], path.get(5));
        assertEquals(layout[0][2], path.get(6));
    }

    @Test
    public void test_findPath_moreThanOnePathPossible() {
        layout = new Tile[3][3];
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                layout[y][x] =
                        new Tile("", new Coordinate(x, y), LevelElement.FLOOR, DesignLabel.DEFAULT);
        layout[0][1] = new Tile("", new Coordinate(0, 1), LevelElement.WALL, DesignLabel.DEFAULT);
        level = new Level(layout);
        level.setStartTile(layout[0][0]);
        level.setEndTile(layout[0][2]);
        /** How the level layout looks: (S=start, W=Wall,F=Floor,E=exit) SWE FFF FFF */
        // should take the shortest path
        GraphPath<Tile> path = level.findPath(level.getStartTile(), level.getEndTile());
        assertEquals(5, path.getCount());
        assertEquals(layout[0][0], path.get(0));
        assertEquals(layout[1][0], path.get(1));
        assertEquals(layout[1][1], path.get(2));
        assertEquals(layout[1][2], path.get(3));
        assertEquals(layout[0][2], path.get(4));
    }

    @Test
    public void test_isOnEndTile() {
        Entity entity = Mockito.mock(Entity.class);
        when(entity.getPosition()).thenReturn(endTile.getCoordinate().toPoint());
        assertTrue(level.isOnEndTile(entity));
        when(entity.getPosition()).thenReturn(startTile.getCoordinate().toPoint());
        assertFalse(level.isOnEndTile(entity));
    }

    @Test
    public void test_getTileAt() {
        assertEquals(layout[1][2], level.getTileAt(new Coordinate(2, 1)));
    }

    @Test
    public void test_getRandomTile() {
        assertNotNull(level.getRandomTile());
    }

    @Test
    public void test_getRandomTile_WithElementType() {
        Point randomWallPoint = level.getRandomTilePoint(LevelElement.WALL);
        assertNotNull(randomWallPoint);
        Tile randomWall = level.getTileAt(randomWallPoint.toCoordinate());
        assertNotNull(randomWall);
        assertEquals(LevelElement.WALL, randomWall.getLevelElement());
        /* Point randomFloorPoint = level.getRandomTilePoint(LevelElement.FLOOR);
        assertNotNull(randomFloorPoint);
        Tile randomFloor = level.getTileAt(randomFloorPoint.toCoordinate());
        assertNotNull(randomWall);
        assertEquals(LevelElement.FLOOR, randomFloor.getLevelElement());*/
    }

    @Test
    public void test_getRandomTilePoint() {
        Point randomPoint = level.getRandomTilePoint();
        assertNotNull(randomPoint);
        assertNotNull(level.getTileAt(randomPoint.toCoordinate()));
    }

    @Test
    public void test_getRandomTilePoint_WithElementType() {
        Point randomWallPoint = level.getRandomTilePoint(LevelElement.WALL);
        Point randomFloorPoint = level.getRandomTilePoint(LevelElement.FLOOR);
        Tile randomWall = level.getTileAt(randomWallPoint.toCoordinate());
        Tile randomFloor = level.getTileAt(randomFloorPoint.toCoordinate());
        assertEquals(LevelElement.WALL, randomWall.getLevelElement());
        assertEquals(LevelElement.FLOOR, randomFloor.getLevelElement());
    }

    @Test
    public void test_getLayout() {
        assertArrayEquals(layout, level.getLayout());
    }

    @Test
    public void test_setStartTile() {
        Tile newStart = layout[2][2];
        level.setStartTile(newStart);
        assertEquals(LevelElement.FLOOR, newStart.getLevelElement());
        assertEquals(newStart, level.getStartTile());
    }

    @Test
    public void test_setEndTile() {
        Tile newEnd = layout[2][2];
        level.setEndTile(newEnd);
        assertEquals(LevelElement.FLOOR, endTile.getLevelElement());
        assertEquals(LevelElement.EXIT, newEnd.getLevelElement());
        assertEquals(newEnd, level.getEndTile());
    }

    @Test
    public void test_toString() {
        String compareString = "";
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                if (layout[y][x].getLevelElement() == LevelElement.FLOOR) compareString += "F";
                else if (layout[y][x].getLevelElement() == LevelElement.WALL) compareString += "W";
                else compareString += "E";
            }
            compareString += "\n";
        }
        assertEquals(compareString, level.printLevel());
    }
}
