package level.elements;

import basiselements.Entity;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import level.elements.astar.TileHeuristic;
import level.tools.Coordinate;
import level.tools.LevelElement;
import tools.Point;

/**
 * A level is a set of connect rooms to play in.
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

    public Tile[][] getLayout() {
        return layout;
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
     * Set the start tile.
     *
     * @param start The start tile.
     */
    public void setStartTile(Tile start) {
        startTile = start;
        startTile.setLevelElement(LevelElement.FLOOR, "TEXTURE TODO");
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
     * Set the end tile.
     *
     * @param end The end tile.
     */
    public void setEndTile(Tile end) {
        if (endTile != null) endTile.setLevelElement(LevelElement.FLOOR, "TEXTURE TODO");
        endTile = end;
        endTile.setLevelElement(LevelElement.EXIT, "TEXTURE TODO");
    }

    /** Mark a random tile as start */
    public void setRandomStart() {
        setStartTile(getRandomTile(LevelElement.WALL));
    }

    /** Mark a random tile as end */
    public void setRandomEnd() {
        setEndTile(getRandomTile(LevelElement.FLOOR));
    }

    /**
     * Get a tile on the global position.
     *
     * @param globalPoint Position form where to get the tile.
     * @return The tile on that point.
     */
    public Tile getTileAt(Coordinate globalPoint) {
        return layout[globalPoint.x][globalPoint.y];
    }

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
     * Connect each tile with it neighbour tiles.
     *
     * @author Marti Stuwe
     */
    public void makeConnections() {
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

    @Override
    public int getIndex(Tile tile) {
        return tile.getIndex();
    }

    /**
     * @return a random Tile in the Level
     */
    public Tile getRandomTile() {
        return layout[RANDOM.nextInt(layout.length)][RANDOM.nextInt(layout[0].length)];
    }

    public Tile getRandomTile(LevelElement elementType) {
        Tile randomTile = getRandomTile();
        if (randomTile.getLevelElement() == elementType) return randomTile;
        else return getRandomTile(elementType);
    }

    public Point getRandomTilePoint() {
        return getRandomTile().getCoordinate().toPoint();
    }

    public Point getRandomTilePoint(LevelElement elementTyp) {
        return getRandomTile(elementTyp).getCoordinate().toPoint();
    }

    @Override
    public Array<Connection<Tile>> getConnections(Tile fromNode) {
        return fromNode.getConnections();
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

    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * Converts Level in JSON.
     *
     * @return Level as JSON
     */
    public String toJSON() {
        return new Gson().toJson(this);
    }

    /**
     * Writes down this level in a json.
     *
     * @param path Where to save.
     */
    public void writeToJSON(String path) {
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            writer.write(toJSON());
        } catch (IOException e) {
            System.out.println("File" + path + " not found");
        }
    }
}
