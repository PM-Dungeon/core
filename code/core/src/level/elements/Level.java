package level.elements;

import basiselements.Entity;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import java.util.Random;
import level.elements.astar.TileHeuristic;
import level.tools.Coordinate;
import level.tools.DesignLabel;
import level.tools.LevelElement;
import level.tools.TileTextureFactory;
import tools.Point;

/**
 * A level is a 2D-Array of Tiles.
 *
 * @author Andre Matutat
 */
public class Level implements IndexedGraph<Tile> {
    private static final Random RANDOM = new Random();
    private final TileHeuristic tileHeuristic = new TileHeuristic();
    private Tile startTile;
    private Tile endTile;
    private int nodeCount = 0;
    private Tile[][] layout;

    /**
     * Create a new level
     *
     * @param layout The layout of the level.
     */
    public Level(Tile[][] layout) {
        this.layout = layout;
        makeConnections();
        setRandomEnd();
        setRandomStart();
    }

    /**
     * Create a new Level
     *
     * @param layout The layout of the Level
     * @param designLabel The design the level should have
     */
    public Level(LevelElement[][] layout, DesignLabel designLabel) {
        this(convertLevelElementToTile(layout, designLabel));
    }

    // --------------------------- API ---------------------------

    /**
     * Starts the indexed A* pathfinding algorithm a returns a path
     *
     * @param start Start tile
     * @param end End tile
     * @return Generated path
     * @author Marti Stuwe
     */
    public GraphPath<Tile> findPath(Tile start, Tile end) {
        GraphPath<Tile> path = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(start, end, tileHeuristic, path);
        return path;
    }

    /**
     * Checks if the passed entity is on the tile to the next level.
     *
     * @param entity entity to check for.
     * @return if the passed entity is on the tile to the next level
     */
    public boolean isOnEndTile(Entity entity) {
        return entity.getPosition().toCoordinate().equals(getEndTile().getCoordinate());
    }

    /**
     * Get a tile on the global position.
     *
     * @param globalPoint Position form where to get the tile.
     * @return The tile on that point. null if there is no Tile or the Coordinate is out of bound
     */
    public Tile getTileAt(Coordinate globalPoint) {
        try {
            return layout[globalPoint.y][globalPoint.x];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * @return a random Tile in the Level
     */
    public Tile getRandomTile() {
        return layout[RANDOM.nextInt(layout.length)][RANDOM.nextInt(layout[0].length)];
    }

    /**
     * Get a random Tile
     *
     * @param elementType Type of the Tile
     * @return A random Tile of the given Type
     */
    public Tile getRandomTile(LevelElement elementType) {
        Tile randomTile = getRandomTile();
        if (randomTile.getLevelElement() == elementType) return randomTile;
        else return getRandomTile(elementType);
    }

    /**
     * Get the position of a random Tile as Point
     *
     * @return Position of the Tile as Point
     */
    public Point getRandomTilePoint() {
        return getRandomTile().getCoordinate().toPoint();
    }

    /**
     * Get the position of a random Tile as Point
     *
     * @param elementTyp Type of the Tile
     * @return Position of the Tile as Point
     */
    public Point getRandomTilePoint(LevelElement elementTyp) {
        return getRandomTile(elementTyp).getCoordinate().toPoint();
    }

    public Tile[][] getLayout() {
        return layout;
    }

    /**
     * Set the start tile.
     *
     * @param start The start tile.
     */
    public void setStartTile(Tile start) {
        startTile = start;
        changeTileElementType(startTile, LevelElement.FLOOR);
    }

    /** Mark a random tile as start */
    public void setRandomStart() {
        setStartTile(getRandomTile(LevelElement.FLOOR));
    }

    /**
     * Get the start tile.
     *
     * @return The start tile.
     */
    public Tile getStartTile() {
        return startTile;
    }

    /**
     * Set the end tile.
     *
     * @param end The end tile.
     */
    public void setEndTile(Tile end) {
        if (endTile != null) changeTileElementType(endTile, LevelElement.FLOOR);
        System.out.println(end.getLevelElement());
        endTile = end;
        changeTileElementType(end, LevelElement.EXIT);
    }

    /** Mark a random tile as end */
    public void setRandomEnd() {
        setEndTile(getRandomTile(LevelElement.FLOOR));
    }

    /**
     * Get the end tile.
     *
     * @return The end tile.
     */
    public Tile getEndTile() {
        return endTile;
    }

    /**
     * Change the type of tile (including changing texture)
     *
     * @param tile The Tile you want to change
     * @param changeInto The LevelElement to change the Tile into.
     */
    public void changeTileElementType(Tile tile, LevelElement changeInto) {
        tile.setLevelElement(
                changeInto, TileTextureFactory.findTexturePath(tile, layout, changeInto));
    }

    /**
     * F=Floor, W=Wall, E=Exit, S=Skip/Blank
     *
     * @return The level layout in String format
     */
    public String printLevel() {
        String output = "";
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                if (layout[y][x].getLevelElement() == LevelElement.FLOOR) output += "F";
                else if (layout[y][x].getLevelElement() == LevelElement.WALL) output += "W";
                else if (layout[y][x].getLevelElement() == LevelElement.EXIT) output += "E";
                else output += "S";
            }
            output += "\n";
        }
        return output;
    }
    // --------------------------- END API ---------------------------

    // --------------------------- For LibGDX Pathfinding ---------------------------

    @Override
    public int getIndex(Tile tile) {
        return tile.getIndex();
    }

    @Override
    public Array<Connection<Tile>> getConnections(Tile fromNode) {
        return fromNode.getConnections();
    }

    /**
     * For libgDX pathfinding algorithms
     *
     * @return nodeCount
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /** Connect each tile with it neighbour tiles. */
    private void makeConnections() {
        for (int x = 0; x < layout[0].length; x++)
            for (int y = 0; y < layout.length; y++)
                if (layout[y][x].isAccessible()) {
                    layout[y][x].setIndex(nodeCount++);
                    addConnectionsToNeighbours(layout[y][x]);
                }
    }

    /**
     * Check each tile around the tile, if it is accessible add it to the connectionList.
     *
     * @param checkTile Tile to check for.
     */
    private void addConnectionsToNeighbours(Tile checkTile) {

        // upperTile
        Coordinate upper =
                new Coordinate(checkTile.getCoordinate().x, checkTile.getCoordinate().y + 1);
        Tile upperTile = getTileAt(upper);
        if (upperTile != null && upperTile.isAccessible()) checkTile.addConnection(upperTile);

        // lowerTile
        Coordinate lower =
                new Coordinate(checkTile.getCoordinate().x, checkTile.getCoordinate().y - 1);
        Tile lowerTile = getTileAt(lower);
        if (lowerTile != null && lowerTile.isAccessible()) checkTile.addConnection(lowerTile);

        // leftTile
        Coordinate left =
                new Coordinate(checkTile.getCoordinate().x - 1, checkTile.getCoordinate().y);
        Tile leftTile = getTileAt(left);
        if (leftTile != null && leftTile.isAccessible()) checkTile.addConnection(leftTile);
        // rightTile
        Coordinate right =
                new Coordinate(checkTile.getCoordinate().x + 1, checkTile.getCoordinate().y);
        Tile rightTile = getTileAt(right);
        if (rightTile != null && rightTile.isAccessible()) checkTile.addConnection(rightTile);
    }

    // --------------------------- End LibGDX Pathfinding ---------------------------

    /**
     * Converts the given LevelElement[][] in a corresponding Tile[][]
     *
     * @param layout The LevelElement[][]
     * @param designLabel The selected Design for the Tiles
     * @return The converted Tile[][]
     */
    private static Tile[][] convertLevelElementToTile(
            LevelElement[][] layout, DesignLabel designLabel) {
        Tile[][] tileLayout = new Tile[layout.length][layout[0].length];
        for (int y = 0; y < layout.length; y++)
            for (int x = 0; x < layout[0].length; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                String texturePath =
                        TileTextureFactory.findTexturePath(
                                layout[y][x], designLabel, layout, coordinate);
                tileLayout[y][x] = new Tile(texturePath, coordinate, layout[y][x], designLabel);
            }
        return tileLayout;
    }
}
