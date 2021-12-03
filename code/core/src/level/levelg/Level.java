package level.levelg;

import level.enums.DesignLabel;
import level.graphg.Graph;
import level.graphg.Node;
import tools.Point;

import java.util.List;

public class Level {
    private Graph graph;
    private int[][] layout;
    private DesignLabel label;
    private List<Room> rooms;
    private Node start;
    private Node goal;

    public Level(int[][] layout, Graph graph, List<Room> rooms, DesignLabel label) {
        this.graph = graph;
        this.layout = layout;
        this.label = label;
        this.rooms = rooms;
    }

    /**
     * return a way from the start node to the end node todo
     *
     * @param start
     * @param goal
     * @return
     */
    public List<Room> getOnePath(Node start, Node goal) {
        return null;
    }

    // todo
    public List<List<Room>> getAllPaths(Node start, Node goal) {
        return null;
    }
    // todo
    public List<Room> getCriticalRooms(Node start, Node goal) {
        return null;
    }
    // todo
    public List<Room> getOptionalRooms(Node start, Node goal) {
        return null;
    }
    // todo
    public boolean isRoomReachableWithout(Node start, Node goal, List<Node> avoid) {
        return false;
    }

    // todo

    public Room whereAmI(Point p) {
        return null;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node n) {
        start = n;
    }

    public Node getGoal() {
        return goal;
    }

    public void setGoal(Node n) {
        goal = n;
    }

    public Graph getGraph() {
        return graph;
    }

    public int[][] getLayout() {
        return layout;
    }

    public DesignLabel getLabel() {
        return label;
    }
}
