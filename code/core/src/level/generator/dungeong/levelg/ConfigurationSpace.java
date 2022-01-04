package level.generator.dungeong.levelg;

import level.elements.Node;
import level.generator.dungeong.roomg.RoomTemplate;
import tools.Point;
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
        // todo
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
