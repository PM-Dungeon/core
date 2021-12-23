package level.elements;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import level.elements.astar.TileHeuristic;
import tools.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level implements IndexedGraph<Tile> {
    private final TileHeuristic tileHeuristic = new TileHeuristic();
    private List<Room> rooms;
    private List<Node> nodes;
    private Node startNode;
    private Node endNode;
    private Tile startTile;
    private Tile endTile;
    private int nodeCount = 0;

    public Level(List<Node> nodes, List<Room> rooms) {
        this.nodes = nodes;
        this.rooms = rooms;
        makeConnections();
    }

    /** @return random room */
    public Room getRandomRoom() {
        return getRooms().get(new Random().nextInt(getRooms().size()));
    }

    public Node getNodeToRoom(Room r) {
        return nodes.get(rooms.indexOf(r));
    }

    public Room getRoomToNode(Node n) {
        return rooms.get(nodes.indexOf(n));
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getRandomNode() {
        return getNodes().get(new Random().nextInt(getNodes().size()));
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node s) {
        startNode = s;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node e) {
        endNode = e;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile start) {
        startTile = start;
    }

    public Tile getEndTile() {
        return endTile;
    }

    public void setEndTile(Tile end) {
        endTile = end;
    }

    public List<List<Node>> getAllPath(Node start, Node goal) {
        List<List<Node>> paths = new ArrayList<List<Node>>();
        graph_search(start, new ArrayList<>(), goal, paths);
        return paths;
    }

    public List<Node> getShortestPath(Node start, Node goal) {
        List<List<Node>> allPaths = getAllPath(start, goal);
        List<Node> shortestPath = allPaths.get(0);
        for (List<Node> l : allPaths) if (l.size() < shortestPath.size()) shortestPath = l;
        return shortestPath;
    }

    public List<Node> getCriticalNodes() {
        List<List<Node>> allPaths = getAllPath(getStartNode(), getEndNode());
        List<Node> criticalNodes = allPaths.get(0);
        for (List<Node> list : allPaths) criticalNodes.retainAll(list);
        return criticalNodes;
    }

    public List<Node> getOptionalNodes() {
        List<Node> criticalNodes = getCriticalNodes();
        List<Node> optionalNodes = new ArrayList<>(nodes);
        optionalNodes.removeAll(criticalNodes);
        return optionalNodes;
    }

    public boolean isRoomReachableWithout(Node start, Node goal, Node avoid) {
        List<List<Node>> allPaths = getAllPath(start, goal);
        List<List<Node>> pathWithoutAvoid = new ArrayList<>(allPaths);
        for (List<Node> list : allPaths) if (list.contains(avoid)) pathWithoutAvoid.remove(list);
        return pathWithoutAvoid.size() > 0;
    }

    public Tile getTileAt(Point globalPoint) {
        for (Room r : rooms) {
            for (int y = 0; y < r.getLayout().length; y++)
                for (int x = 0; x < r.getLayout()[0].length; x++)
                    if (r.getLayout()[y][x].getGlobalPosition().equals(globalPoint))
                        return r.getLayout()[y][x];
        }
        return null;
    }

    public Room getRoomToPoint(Point globalPoint) {
        for (Room r : rooms) {
            for (int y = 0; y < r.getLayout().length; y++)
                for (int x = 0; x < r.getLayout()[0].length; x++)
                    if (r.getLayout()[y][x].getGlobalPosition().equals(globalPoint)) return r;
        }
        return null;
    }

    public boolean isTileReachable(Tile start, Tile goal) {
        return findPath(start, goal).getCount() > 0;
    }

    /**
     * Starts the indexed A* pathfinding algorithm an returns a path
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
     * bfs
     *
     * @param currentNode
     * @param marked
     * @param goal
     * @param paths
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
                    graph_search(childNode, new ArrayList<Node>(myMarked), goal, paths);
            }
    }

    /**
     * Checks tiles for connections and
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
     * Check each Filed around the Tile, if it is accessible add it to the connectionList
     *
     * @param checkTile Tile to check for
     */
    private void addConnectionsToNeighbours(Tile checkTile) {

        // upperTile
        Point upper =
                new Point(checkTile.getGlobalPosition().x, checkTile.getGlobalPosition().y + 1);
        Tile upperTile = getTileAt(upper);
        if (upperTile != null && upperTile.isAccessible()) checkTile.addConnection(upperTile);

        // lowerTile
        Point lower =
                new Point(checkTile.getGlobalPosition().x, checkTile.getGlobalPosition().y - 1);
        Tile lowerTile = getTileAt(lower);
        if (lowerTile != null && lowerTile.isAccessible()) checkTile.addConnection(lowerTile);

        // leftTile
        Point left =
                new Point(checkTile.getGlobalPosition().x - 1, checkTile.getGlobalPosition().y);
        Tile leftTile = getTileAt(left);
        if (leftTile != null && leftTile.isAccessible()) checkTile.addConnection(leftTile);
        // rightTile
        Point right =
                new Point(checkTile.getGlobalPosition().x + 1, checkTile.getGlobalPosition().y);
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
