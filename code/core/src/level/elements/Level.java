package level.elements;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import level.elements.astar.TileHeuristic;
import level.elements.graph.Node;
import level.elements.room.Room;
import level.elements.room.Tile;
import level.tools.Coordinate;
import level.tools.DesignLabel;
import level.tools.LevelElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A level is a set of connect rooms to play in.
 *
 * @author Andre Matutat
 */
public class Level implements IndexedGraph<Tile> {
    private final TileHeuristic tileHeuristic = new TileHeuristic();
    private final List<Room> rooms;
    private final List<Node> nodes;
    private Node startNode;
    private Node endNode;
    private Tile startTile;
    private Tile endTile;
    private int nodeCount = 0;

    /**
     * Create a new level
     *
     * @param nodes A list of nodes that represent the structure of the level. Each noe is
     *     represented by a room.
     * @param rooms A list of rooms that are in this level. Each represents a node.
     */
    public Level(List<Node> nodes, List<Room> rooms) {
        this.nodes = nodes;
        this.rooms = rooms;
        makeConnections();

        setRandomEnd();
        setRandomStart();
    }

    /** @return A random room in the level. */
    public Room getRandomRoom() {
        return getRooms().get(new Random().nextInt(getRooms().size()));
    }

    /**
     * Finds the node to a given room.
     *
     * @param room The room to find the node for.
     * @return The node.
     */
    public Node getNodeToRoom(Room room) {
        return nodes.get(rooms.indexOf(room));
    }

    /**
     * Finds the room to a given node.
     *
     * @param node The node to find the room for.
     * @return The room.
     */
    public Room getRoomToNode(Node node) {
        return rooms.get(nodes.indexOf(node));
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    /** @return A random node in level. */
    public Node getRandomNode() {
        return getNodes().get(new Random().nextInt(getNodes().size()));
    }

    /** @return Node where the transposition is located in. */
    public Node getStartNode() {
        return startNode;
    }

    /**
     * Set the start node.
     *
     * @param startNode The start node.
     */
    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    /** @return Node where the exposition is located in. */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * Set the end node.
     *
     * @param endNode The end node.
     */
    public void setEndNode(Node endNode) {
        this.endNode = endNode;
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
        endTile = end;
    }

    public void setRandomStart() {
        Node startN = getRandomNode();
        Tile startT = getRoomToNode(startN).getRandomFloorTile();
        setStartTile(startT);
        setStartNode(startN);
    }

    public void setRandomEnd() {
        Node endN = getRandomNode();
        Tile endT = getRoomToNode(endN).getRandomFloorTile();
        DesignLabel l = getRoomToNode(endN).getDesign();
        endT.setLevelElement(
                LevelElement.EXIT,
                "textures/dungeon/" + l.name().toLowerCase() + "/floor/floor_ladder.png");
        setEndTile(endT);
        setEndNode(endN);
    }

    /**
     * Finds all paths form on node to another one.
     *
     * @param start start node.
     * @param goal goal node.
     * @return A list with a list of paths form start to goal.
     */
    public List<List<Node>> getAllPath(Node start, Node goal) {
        List<List<Node>> paths = new ArrayList<>();
        graph_search(start, new ArrayList<>(), goal, paths);
        return paths;
    }

    /**
     * Find the path with the lowest number of nodes from one node to another one.
     *
     * @param start start node
     * @param goal goal node
     * @return The fastest path.
     */
    public List<Node> getShortestPath(Node start, Node goal) {
        List<List<Node>> allPaths = getAllPath(start, goal);
        List<Node> shortestPath = allPaths.get(0);
        for (List<Node> l : allPaths) if (l.size() < shortestPath.size()) shortestPath = l;
        return shortestPath;
    }

    /**
     * Find all nodes, that have to be entered to get from the start to the end.
     *
     * @return All critical nodes.
     */
    public List<Node> getCriticalNodes() {
        List<List<Node>> allPaths = getAllPath(getStartNode(), getEndNode());
        List<Node> criticalNodes = allPaths.get(0);
        for (List<Node> list : allPaths) criticalNodes.retainAll(list);
        return criticalNodes;
    }

    /**
     * Find all nodes, that don't have to be entered to get from the start to the end.
     *
     * @return All optional nodes
     */
    public List<Node> getOptionalNodes() {
        List<Node> criticalNodes = getCriticalNodes();
        List<Node> optionalNodes = new ArrayList<>(nodes);
        optionalNodes.removeAll(criticalNodes);
        return optionalNodes;
    }

    /**
     * Check if you can get from the start node to the goal node without entering to avoid node.
     *
     * @param start Node to start from.
     * @param goal Goal node.
     * @param avoid Node to avoid.
     * @return Can you reach goal from start without avoid?
     */
    public boolean isRoomReachableWithout(Node start, Node goal, Node avoid) {
        List<List<Node>> allPaths = getAllPath(start, goal);
        List<List<Node>> pathWithoutAvoid = new ArrayList<>(allPaths);
        for (List<Node> list : allPaths) if (list.contains(avoid)) pathWithoutAvoid.remove(list);
        return pathWithoutAvoid.size() > 0;
    }

    /**
     * Get a tile on the global position.
     *
     * @param globalPoint Position form where to get the tile.
     * @return The tile on that point.
     */
    public Tile getTileAt(Coordinate globalPoint) {
        for (Room r : rooms) {
            for (int y = 0; y < r.getLayout().length; y++)
                for (int x = 0; x < r.getLayout()[0].length; x++)
                    if (r.getLayout()[y][x].getGlobalPosition().equals(globalPoint))
                        return r.getLayout()[y][x];
        }
        return null;
    }

    /**
     * Get the room in which the pin is located in.
     *
     * @param globalPoint Point to check for.
     * @return The room.
     */
    public Room getRoomToPoint(Coordinate globalPoint) {
        for (Room r : rooms) {
            for (int y = 0; y < r.getLayout().length; y++)
                for (int x = 0; x < r.getLayout()[0].length; x++)
                    if (r.getLayout()[y][x].getGlobalPosition().equals(globalPoint)) return r;
        }
        return null;
    }

    /**
     * Check if you can reach the goal tile from the start tile. Uses A*.
     *
     * @param start Start tile
     * @param goal Goal tile
     * @return Can you reach the tile?
     */
    public boolean isTileReachable(Tile start, Tile goal) {
        return findPath(start, goal).getCount() > 0;
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
     * Uses deep-first-search and recursion to find all pats in a graph.
     *
     * @param currentNode Node currently to check.
     * @param marked Already checked nodes.
     * @param goal Goal node.
     * @param paths Already found paths.
     */
    private void graph_search(
            Node currentNode, List<Node> marked, Node goal, List<List<Node>> paths) {
        List<Node> myMarked = new ArrayList<>(marked);
        myMarked.add(currentNode);
        if (currentNode == goal) paths.add(myMarked);
        else
            for (int child : currentNode.getNeighbours()) {
                Node childNode = nodes.get(child);
                if (!myMarked.contains(childNode))
                    graph_search(childNode, new ArrayList<>(myMarked), goal, paths);
            }
    }

    /**
     * Connect each tile with it neighbour tiles.
     *
     * @author Marti Stuwe
     */
    public void makeConnections() {
        for (Room r : rooms) {
            for (int y = 0; y < r.getLayout().length; y++) {
                for (int x = 0; x < r.getLayout()[0].length; x++) {
                    if (r.getLayout()[y][x].isAccessible()) {
                        r.getLayout()[y][x].setIndex(nodeCount++);
                        addConnectionsToNeighbours(r.getLayout()[y][x]);
                    }
                }
            }
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
                new Coordinate(
                        checkTile.getGlobalPosition().x, checkTile.getGlobalPosition().y + 1);
        Tile upperTile = getTileAt(upper);
        if (upperTile != null && upperTile.isAccessible()) checkTile.addConnection(upperTile);

        // lowerTile
        Coordinate lower =
                new Coordinate(
                        checkTile.getGlobalPosition().x, checkTile.getGlobalPosition().y - 1);
        Tile lowerTile = getTileAt(lower);
        if (lowerTile != null && lowerTile.isAccessible()) checkTile.addConnection(lowerTile);

        // leftTile
        Coordinate left =
                new Coordinate(
                        checkTile.getGlobalPosition().x - 1, checkTile.getGlobalPosition().y);
        Tile leftTile = getTileAt(left);
        if (leftTile != null && leftTile.isAccessible()) checkTile.addConnection(leftTile);
        // rightTile
        Coordinate right =
                new Coordinate(
                        checkTile.getGlobalPosition().x + 1, checkTile.getGlobalPosition().y);
        Tile rightTile = getTileAt(right);
        if (rightTile != null && rightTile.isAccessible()) checkTile.addConnection(rightTile);
    }

    @Override
    public int getIndex(Tile tile) {
        return tile.getIndex();
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public Array<Connection<Tile>> getConnections(Tile fromNode) {
        return fromNode.getConnections();
    }
}
