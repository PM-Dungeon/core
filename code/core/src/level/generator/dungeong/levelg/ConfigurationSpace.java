package level.generator.dungeong.levelg;

import level.elements.graph.Node;
import level.generator.dungeong.roomg.RoomTemplate;
import level.tools.LevelElement;
import tools.Point;

import java.util.ArrayList;
import java.util.List;

/** @author Andre Matutat */
public class ConfigurationSpace {
    private final RoomTemplate template;
    private final Node node;
    private final Point globalPosition;

    /**
     * @param template The used RoomTemplate
     * @param node Node in the graph this configuration-space belongs to.
     * @param globalPosition Position of th localReferencePoint of the template in the global system
     */
    public ConfigurationSpace(RoomTemplate template, Node node, Point globalPosition) {
        this.template = template;
        this.node = node;
        this.globalPosition = globalPosition;
    }

    /**
     * @param otherTemplate Template to check for.
     * @param otherGlobal Global-position of the template.
     * @return If the given template overlapped with this configuration-space.
     */
    public boolean overlap(RoomTemplate otherTemplate, Point otherGlobal) {
        List<Point> thisPoints = convertInPoints(template, globalPosition);
        List<Point> otherPoints = convertInPoints(otherTemplate, otherGlobal);
        for (Point p1 : thisPoints)
            for (Point p2 : otherPoints)
                if (p1.equals(p2))
                    if (!isOuterWall(p1, globalPosition, template)
                            || !isOuterWall(p2, otherGlobal, otherTemplate)) return false;
        return true;
    }

    /**
     * Calculate the global position for each field of the template.
     *
     * @param template
     * @param globalPosition
     * @return
     */
    private List<Point> convertInPoints(RoomTemplate template, Point globalPosition) {
        List<Point> points = new ArrayList<>();
        LevelElement[][] layout = template.getLayout();
        float difx = globalPosition.x - template.getLocalRef().x;
        float dify = globalPosition.y - template.getLocalRef().y;
        for (int y = 0; y < layout.length; y++)
            for (int x = 0; x < layout[0].length; x++) points.add(new Point(x + difx, y + dify));
        return points;
    }

    /**
     * Check if a given point in the layout is an outer wall.
     *
     * @param globalPoint
     * @param globalReferencePoint
     * @param template
     * @return Is outer wall?
     */
    private boolean isOuterWall(
            Point globalPoint, Point globalReferencePoint, RoomTemplate template) {
        LevelElement[][] layout = template.getLayout();
        float difx = globalReferencePoint.x - template.getLocalRef().x;
        float dify = globalReferencePoint.y - template.getLocalRef().y;
        Point localP = new Point(globalPoint.x - difx, globalPoint.y - dify);

        // outer points
        if (localP.y == 0
                || localP.y == layout.length - 1
                || localP.x == 0
                || localP.x == layout[0].length - 1) return true;

        // check all the way up
        boolean ok = true;
        for (int y = (int) localP.y + 1; y < layout.length; y++)
            if (layout[y][(int) localP.x] != LevelElement.SKIP) ok = false;

        if (ok) return true;

        // check all the way down
        ok = true;
        for (int y = (int) localP.y - 1; y <= 0; y--)
            if (layout[y][(int) localP.x] != LevelElement.SKIP) ok = true;

        if (ok) return true;

        // check all the way right
        ok = true;
        for (int x = (int) localP.x + 1; x < layout[0].length; x++)
            if (layout[(int) localP.y][x] != LevelElement.SKIP) ok = false;

        if (ok) return true;

        // check all the way left
        ok = true;
        for (int x = (int) localP.x - 1; x <= 0; x--)
            if (layout[(int) localP.y][x] != LevelElement.SKIP) ok = true;

        if (ok) return true;

        return false;
    }

    public RoomTemplate getTemplate() {
        return template;
    }

    public Node getNode() {
        return node;
    }

    public Point getGlobalPosition() {
        return globalPosition;
    }
}
