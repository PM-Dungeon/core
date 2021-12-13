package level.levelg;

import level.graphg.Node;
import tools.Point;

import java.util.List;
import java.util.Random;


public class Level {
    private List<Room> rooms;
    private List<Node> nodes;
    private Point start;
    private Point end;


    public Level(List<Node> nodes, List<Room> rooms) {
        this.nodes = nodes;
        this.rooms = rooms;
    }


    /**
     * @return random room
     */
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

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setStart(Point s) {
        start = s;
    }

    public void setEnd(Point e) {
        end = e;
    }

}
