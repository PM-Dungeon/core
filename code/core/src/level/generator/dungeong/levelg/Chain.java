package level.generator.dungeong.levelg;

import java.util.ArrayList;
import java.util.List;

/**
 * A Chain is a list of nodes where each node is connected to his predecessor and his successor.
 *
 * @author Andre Matutat
 */
public class Chain {
    private List<ChainNode> chain;

    public Chain() {
        chain = new ArrayList<>();
    }

    public Chain(List<ChainNode> chain) {
        this.chain = chain;
    }

    public void add(ChainNode n) {
        chain.add(n);
    }

    public List<ChainNode> getNodes() {
        ArrayList<ChainNode> copy = new ArrayList<>(chain);
        return copy;
    }
}
