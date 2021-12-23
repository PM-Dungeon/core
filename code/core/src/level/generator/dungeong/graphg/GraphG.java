package level.generator.dungeong.graphg;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import level.elements.Graph;
import level.elements.Node;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** @author Andre Matutat */
public class GraphG {
    public Graph getGraph(int nodes, int edges, String path) {
        path += "/" + nodes + "_" + edges + ".json";
        List<Graph> sol = null;
        sol = readFromJson(path);
        return sol.get(new Random().nextInt(sol.size()));
    }

    /**
     * Calculate a list of planar graphs. Generate all possible trees and then draw extra edges.
     *
     * @param nodes number of nodes
     * @param edges number of extra edges that get drawn into the generated tree
     * @return a list of all solutions
     * @throws CantBePlanarException
     * @throws IllegalArgumentException
     * @throws NoSolutionException
     */
    public List<Graph> generateGraphs(int nodes, int edges)
            throws CantBePlanarException, IllegalArgumentException, NoSolutionException {
        if (nodes <= 1)
            throw new IllegalArgumentException("A graph must consist of at least two nodes");
        if (edges < 0)
            throw new IllegalArgumentException("Number of additional edges cannot be negative");

        // e≤3v-6 must hold
        int minimumEdges = nodes - 1 + edges;
        int leftTerm = 3 * nodes - 6;
        if (minimumEdges > leftTerm) throw new CantBePlanarException("e<=3V-6 does not hold");

        Graph tree = new Graph();
        List<Graph> trees = new ArrayList<>();
        trees.add(tree);
        trees = calculateTrees(trees, nodes - 2);
        List<Graph> solutions = calculateGraphs(trees, edges);
        if (solutions.isEmpty()) throw new NoSolutionException("No solution found"); // ??
        return solutions;
    }

    private List<Graph> calculateTrees(List<Graph> trees, int nodesLeft) {
        if (nodesLeft <= 0) return trees;
        else {
            List<Graph> newTrees = new ArrayList<>();
            for (Graph t : trees)
                for (Node n : t.getNodes()) {
                    Graph newTree = new Graph(t);
                    // have to get the copy of 'n', the index of the copy is always the index of the
                    // original
                    if (newTree.connectNewNode(n.getIndex())) newTrees.add(newTree);
                }
            return calculateTrees(newTrees, nodesLeft - 1);
        }
    }

    private List<Graph> calculateGraphs(List<Graph> graphs, int edgesLeft) {
        if (edgesLeft <= 0) return graphs;
        else {
            List<Graph> newGraphs = new ArrayList<>();
            for (Graph g : graphs)
                for (Node n1 : g.getNodes())
                    for (Node n2 : g.getNodes()) {
                        Graph newGraph = new Graph(g);
                        // same as in tree, have to get the copys of n and n2
                        if (n1.getIndex() != n2.getIndex()
                                && newGraph.connectNodes(n1.getIndex(), n2.getIndex())) {
                            newGraphs.add(newGraph);
                        }
                    }
            return calculateGraphs(newGraphs, edgesLeft - 1);
        }
    }

    private List<Graph> readFromJson(String path) {
        Type graphType = new TypeToken<ArrayList<Graph>>() {}.getType();
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            return new Gson().fromJson(reader, graphType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return new ArrayList<>();
        }
    }

    /**
     * Writes down the list to a json
     *
     * @param graphs the list of rooms to save
     * @param path where to save
     */
    public void writeToJSON(List<Graph> graphs, String path) {
        Gson gson = new Gson();
        String json = gson.toJson(graphs);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("File" + path + " not found");
        }
    }
}
