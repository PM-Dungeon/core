package level.generator.dungeong.levelg;

import level.elements.Node;

/**
 * A ChainNode is a node that has at most two neighbors. The neighbors do not have to be part of the
 * same chain. Each ChainNode represents a "real" node from the graph.
 *
 * @author Andre Matutat
 */
public class ChainNode {
    private Node node;
    private ChainNode prev;
    private ChainNode next;

    /**
     * @param node The "real" node from the graph
     * @param prev Previous neighbour.
     * @param next Next neighbour.
     */
    public ChainNode(Node node, ChainNode prev, ChainNode next) {
        this.node = node;
        this.prev = prev;
        this.next = next;
    }

    /** @return The "real" node form the graph. */
    public Node getNode() {
        return node;
    }

    public ChainNode getPrev() {
        return prev;
    }

    public void setPrev(ChainNode node) {
        prev = node;
    }

    public ChainNode getNext() {
        return next;
    }

    public void setNext(ChainNode node) {
        next = node;
    }
}
