package level.elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    private List<Room> rooms;
    private List<Node> nodes;
    private Node startNode;
    private Node endNode;
    private Tile startTile;
    private Tile endTile;

    public Level(List<Node> nodes, List<Room> rooms) {
        this.nodes = nodes;
        this.rooms = rooms;
    }

    /**
     * Import a level from an json
     *
     * @param file path to the json
     * @return the imported level
     * @throws IOException if there is any problem with the file
     */
    public static final Level loadLevel(String file) throws IOException {
        Level level;
        Type levelType = new TypeToken<Level>() {}.getType();
        JsonReader reader = null;
        reader = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
        return new Gson().fromJson(reader, levelType);
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
        marked.add(currentNode);
        if (currentNode == goal) paths.add(marked);
        else
            for (int child : currentNode.getNeighbours()) {
                Node childNode = nodes.get(child);
                if (!marked.contains(childNode))
                    graph_search(currentNode, new ArrayList<Node>(marked), goal, paths);
            }
    }

    /**
     * Export this level as json
     *
     * @param path where to save?
     */
    public void writeToJson(String path) {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
