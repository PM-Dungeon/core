package level.generator.dungeong.levelg;

import level.elements.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A Chain is a list of nodes where each node is connected to his predecessor and his successor.
 *
 * @author Andre Matutat
 */
public class Chain {
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

    public List<Node> getChain() {
        ArrayList<Node> copy = new ArrayList<>(chain);
        return copy;
    }
}
