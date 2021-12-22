package level.elements;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;
import level.elements.astar.TileConnection;
import level.tools.LevelElement;
import tools.Point;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private String texture;
    private Point globalPosition;
    private LevelElement e;
    private final Array<Connection<Tile>> connections = new Array<>();
    private int index;

    public Tile(String texture, Point globalPosition, LevelElement e) {
        this.texture = texture;
        this.e = e;
        this.globalPosition = globalPosition;
    }

    /**
     * Returns if the tile is accessible by a character
     *
     * @return true if the tile ist floor or exit; false if its a wall or empty
     */
    public boolean isAccessible() {
        switch (e) {
            case FLOOR:
            case EXIT:
                return true;
            case WALL:
            default:
                return false;
        }
    }

    public String getTexture() {
        return texture;
    }

    public Point getGlobalPosition() {
        return globalPosition;
    }

    public LevelElement getLevelElement() {
        return e;
    }

    public void setLevelElement(LevelElement e, String texture) {
        this.e = e;
        this.texture = texture;
    }

    public void addConnection(Tile to) {
        connections.add(new TileConnection(this, to));
    }

    public Array<Connection<Tile>> getConnections() {
        return connections;
    }
    /**
     * Returns the direction to a given tile.
     *
     * @param goal To which tile is the direction
     * @return Can either be north, east, south, west or a combination of two.
     * @author Marti Stuwe
     */
    public Direction[] directionTo(Tile goal) {
        List<Direction> directions = new ArrayList<>();
        if (globalPosition.x < goal.getGlobalPosition().x) {
            directions.add(Direction.E);
        } else if (globalPosition.x > goal.getGlobalPosition().x) {
            directions.add(Direction.W);
        } else if (globalPosition.y < goal.globalPosition.y) {
            directions.add(Direction.N);
        } else if (globalPosition.y > goal.globalPosition.y) {
            directions.add(Direction.S);
        }
        return directions.toArray(new Direction[0]);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    /** @author Marti Stuwe */
    public enum Direction {
        N,
        E,
        S,
        W,
    }
}
