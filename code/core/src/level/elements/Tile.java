package level.elements;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import java.util.List;
import level.elements.astar.TileConnection;
import level.tools.Coordinate;
import level.tools.DesignLabel;
import level.tools.LevelElement;

/**
 * A Tile is a field of the level.
 *
 * @author Andre Matutat
 */
public class Tile {
    private final Coordinate globalPosition;
    private LevelElement elementType;
    private DesignLabel designLabel;
    private String texturePath;
    private transient Array<Connection<Tile>> connections = new Array<>();
    private int index;

    /**
     * Creates a new Tile.
     *
     * @param texturePath Path to the texture of the tile.
     * @param globalPosition Position of the tile in the global system.
     * @param elementType The type of the tile.
     */
    public Tile(
            String texturePath,
            Coordinate globalPosition,
            LevelElement elementType,
            DesignLabel designLabel) {
        this.texturePath = texturePath;
        this.elementType = elementType;
        this.globalPosition = globalPosition;
        this.designLabel = designLabel;
    }

    /**
     * Returns if the tile is accessible by a character.
     *
     * @return true if the tile is floor or exit; false if it is a wall or empty.
     */
    public boolean isAccessible() {
        switch (elementType) {
            case FLOOR:
            case EXIT:
                return true;
            case WALL:
            default:
                return false;
        }
    }

    /**
     * Change the type and texture of the tile.
     *
     * @param elementType New type of the tile.
     * @param texture New texture of the tile.
     */
    public void setLevelElement(LevelElement elementType, String texture) {
        this.elementType = elementType;
        this.texturePath = texture;
    }

    public String getTexturePath() {
        return texturePath;
    }

    /**
     * @return The global coordinate of the tile.
     */
    public Coordinate getCoordinate() {
        return globalPosition;
    }

    public LevelElement getLevelElement() {
        return elementType;
    }

    public DesignLabel getDesignLabel() {
        return designLabel;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * connects to tile together. this mean you can go from one tile to another. Connections are
     * needed to calculate a path through the dungeon.
     *
     * @param to Tile to connect with.
     */
    public void addConnection(Tile to) {
        if (connections == null) {
            connections = new Array<>();
        }
        connections.add(new TileConnection(this, to));
    }

    public Array<Connection<Tile>> getConnections() {
        return connections;
    }

    /**
     * Returns the direction to a given tile.
     *
     * @param goal To which tile is the direction.
     * @return Can either be north, east, south, west or a combination of two.
     */
    public Direction[] directionTo(Tile goal) {
        List<Direction> directions = new ArrayList<>();
        if (globalPosition.x < goal.getCoordinate().x) {
            directions.add(Direction.E);
        } else if (globalPosition.x > goal.getCoordinate().x) {
            directions.add(Direction.W);
        }
        if (globalPosition.y < goal.getCoordinate().y) {
            directions.add(Direction.N);
        } else if (globalPosition.y > goal.getCoordinate().y) {
            directions.add(Direction.S);
        }
        return directions.toArray(new Direction[0]);
    }

    // --------------------------- For LibGDX Pathfinding ---------------------------
    public enum Direction {
        N,
        E,
        S,
        W,
    }

    // --------------------------- End LibGDX Pathfinding ---------------------------

}
