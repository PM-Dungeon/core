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

    public Chain() {
        chain = new ArrayList<>();
    }

    public Chain(List<Node> chain) {
        this.chain = chain;
    }

    public void add(Node n) {
        chain.add(n);
    }

    public List<Node> getNodes() {
        ArrayList<Node> copy = new ArrayList<>(chain);
        return copy;
    }

    @Override
    public int compareTo(Chain o) {
        if (getNodes().size() > o.getNodes().size()) return 1;
        if (getNodes().size() < o.getNodes().size()) return -1;
        else return 0;
    }
}
