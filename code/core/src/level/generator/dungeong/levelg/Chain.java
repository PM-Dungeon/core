package level.generator.dungeong.levelg;

import level.elements.graph.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A Chain is a list of nodes where each node is connected to his predecessor and his successor.
 *
 * @author Andre Matutat
 */
public class Chain implements Comparable<Chain> {
    private List<Node> chain;
    private boolean circle = false;

    public Chain() {
        chain = new ArrayList<>();
    }

    public void add(Node n) {
        chain.add(n);
    }

    public List<Node> getNodes() {
        return new ArrayList<>(chain);
    }

    public void setNodes(List<Node> nodes) {
        chain = nodes;
    }

    public boolean isCircle() {
        return circle;
    }

    public void setCircle(boolean b) {
        circle = b;
    }

    @Override
    public int compareTo(Chain o) {
        if (circle && !o.circle) return -1;
        return Integer.compare(getNodes().size(), o.getNodes().size());
    }
}
