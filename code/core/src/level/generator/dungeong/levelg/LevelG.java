package level.generator.dungeong.levelg;

import level.elements.Graph;
import level.elements.Level;
import level.elements.Node;
import level.elements.Room;
import level.generator.IGenerator;
import level.generator.dungeong.graphg.GraphG;
import level.generator.dungeong.graphg.NoSolutionException;
import level.generator.dungeong.roomg.Replacement;
import level.generator.dungeong.roomg.ReplacementLoader;
import level.generator.dungeong.roomg.RoomTemplate;
import level.generator.dungeong.roomg.RoomTemplateLoader;
import level.tools.DesignLabel;
import tools.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Uses RoomG and GraphG to generate level.
 *
 * @author Andre Matutat
 */
public class LevelG implements IGenerator {
    private GraphG graphg = new GraphG();
    private RoomTemplateLoader roomLoader;
    private ReplacementLoader replacementLoader;
    private String pathToGraph;

    /**
     * Uses RoomG and GraphG to generate level.
     *
     * @param pathToRoomTemplates Path to roomTemplates.json
     * @param pathToReplacements Path to replacements.json
     * @param pathToGraph path to graphs/
     */
    public LevelG(String pathToRoomTemplates, String pathToReplacements, String pathToGraph) {
        roomLoader = new RoomTemplateLoader(pathToRoomTemplates);
        replacementLoader = new ReplacementLoader(pathToReplacements);
        this.pathToGraph = pathToGraph;
    }

    @Override
    public Level getLevel() {
        return getLevel(DesignLabel.values()[new Random().nextInt(DesignLabel.values().length)]);
    }

    @Override
    public Level getLevel(int nodeCounter, int edgeCounter, DesignLabel design)
            throws NoSolutionException {
        Graph graph = graphg.getGraph(nodeCounter, edgeCounter, pathToGraph);
        if (graph == null) throw new NoSolutionException("No Graph found for this configuration");
        return getLevel(graph, design);
    }

    @Override
    public Level getLevel(DesignLabel designLabel) {
        // todo find good configuration space
        Random random = new Random();
        int nodeCounter = random.nextInt(20);
        int edgeCounter = random.nextInt(nodeCounter / 2);
        try {
            return getLevel(nodeCounter, edgeCounter, designLabel);
        } catch (NoSolutionException e) {
            // e.printStackTrace();
            // retry
            return getLevel();
        }
    }

    @Override
    public Level getLevel(int nodeCounter, int edgeCounter) throws NoSolutionException {
        return getLevel(
                nodeCounter,
                edgeCounter,
                DesignLabel.values()[new Random().nextInt(DesignLabel.values().length)]);
    }

    @Override
    public Level getLevel(Graph graph) throws NoSolutionException {
        return getLevel(
                graph, DesignLabel.values()[new Random().nextInt(DesignLabel.values().length)]);
    }

    @Override
    public Level getLevel(Graph graph, DesignLabel design) throws NoSolutionException {
        List<Chain> chain = splitInChains(graph);
        return getLevel(graph, chain, design);
    }

    /**
     * Generate a Level from a graph that has already been split in chains.
     *
     * @param graph The Level-Graph.
     * @param chains The graph split into chains.
     * @param design The Design-Label the level should have.
     * @return The level.
     * @throws NoSolutionException If no solution can be found for the given configuration.
     */
    private Level getLevel(Graph graph, List<Chain> chains, DesignLabel design)
            throws NoSolutionException {
        return getLevel(getSolveSequence(chains), graph, design);
    }

    /**
     * Generate a Level from a graph that has already been split in chains.
     *
     * @param graph The Level-Graph.
     * @param solveSeq Sequence to solve.
     * @param design The Design-Label the level should have.
     * @return The level.
     * @throws NoSolutionException If no solution can be found for the given configuration.
     */
    private Level getLevel(List<Node> solveSeq, Graph graph, DesignLabel design)
            throws NoSolutionException {
        List<ConfigurationSpace> configurationSpaces = makeLevel(graph, solveSeq, design);
        List<Room> rooms = new ArrayList<>();
        List<Replacement> replacements = replacementLoader.getReplacements(design);
        // replace templates
        for (ConfigurationSpace cs : configurationSpaces) {
            RoomTemplate template = cs.getTemplate();
            rooms.add(template.replace(replacements, cs.getGlobalPosition()));
        }
        Level level = new Level(graph.getNodes(), rooms);
        if (checkIfCompletable(level)) return level;
        // in rare cases, the path to the target may be blocked.
        else return getLevel(solveSeq, graph, design);
    }

    /**
     * Split a graph in chains.
     *
     * @param graph Graph to split
     * @return List with Chains.
     */
    private List<Chain> splitInChains(Graph graph) {
        List<Chain> chains = new ArrayList<>();
        // todo split graph in chains
        return chains;
    }

    /**
     * Convert chains into a list of nodes, ordered by the sequence to be solved.
     *
     * @param chains
     * @return Solve sequence
     */
    private List<Node> getSolveSequence(List<Chain> chains) {
        List<Node> solveSequence = new ArrayList<>();
        for (Chain chain : chains)
            for (Node node : chain.getNodes())
                if (!solveSequence.contains(node)) solveSequence.add(node);
        return solveSequence;
    }

    /**
     * @param graph The Graph of the level.
     * @param solveSeq Sequence to solve.
     * @param design The Design-Label the level should have.
     * @return The level.
     * @throws NoSolutionException If no solution can be found for the given configuration.
     */
    private List<ConfigurationSpace> makeLevel(Graph graph, List<Node> solveSeq, DesignLabel design)
            throws NoSolutionException {

        List<RoomTemplate> templates = roomLoader.getRoomTemplates(design);
        List<RoomTemplate> allTemplates = new ArrayList<>();
        for (RoomTemplate template : templates) allTemplates.addAll(template.getAllRotations());
        List<ConfigurationSpace> solution =
                getLevelCS(graph, solveSeq, new ArrayList<ConfigurationSpace>(), allTemplates);

        if (solution.isEmpty())
            throw new NoSolutionException(
                    "No way to convert the given graph into a level using the given templates.");
        placeDoors(solution);
        return solution;
    }

    /**
     * Incremental-backtracking-process to find the configuration-space for all rooms.
     *
     * @param graph The Graph of the level.
     * @param notPlaced Nodes left to place, ordered by the sequence to be solved.
     * @param partSolution The (partial) solution found so far.
     * @param templates RoomTemplates to place.
     * @return A solution in form of a list of ConfigurationSpaces that can be converted into a
     *     level. null if no solution could be found.
     */
    private List<ConfigurationSpace> getLevelCS(
            Graph graph,
            List<Node> notPlaced,
            List<ConfigurationSpace> partSolution,
            List<RoomTemplate> templates) {
        if (notPlaced.isEmpty()) return partSolution; // end solution found

        // take next node
        Node thisNode = notPlaced.get(0);
        List<Node> notPlacedAfterThis = new ArrayList<>(notPlaced);
        notPlacedAfterThis.remove(thisNode);

        // calculate configuration spaces for thisNode
        List<ConfigurationSpace> spaces = new ArrayList<>();
        List<ConfigurationSpace> neighbourSpaces = findNeighbourCS(graph, thisNode, partSolution);
        if (neighbourSpaces.isEmpty())
            // this is the first node ever
            spaces = calCS(null, thisNode, templates, partSolution);
        else spaces = getCS(neighbourSpaces, thisNode, templates, partSolution);
        if (spaces.isEmpty()) return null; // No solution. Backtrack if possible

        // add some random factor
        Collections.shuffle(spaces);

        // go one step deeper
        for (ConfigurationSpace cs : spaces) {
            List<ConfigurationSpace> thisPartSolution = new ArrayList<>(partSolution);
            thisPartSolution.add(cs);
            List<ConfigurationSpace> solution =
                    getLevelCS(graph, notPlacedAfterThis, thisPartSolution, templates);
            if (solution != null) return solution; // end solution found
        }
        return null; // No solution. Backtrack if possible
    }

    private List<ConfigurationSpace> findNeighbourCS(
            Graph graph, Node node, List<ConfigurationSpace> spaces) {
        // todo
        List<ConfigurationSpace> neighbourSpaces = new ArrayList<>();
        return neighbourSpaces;
    }

    /**
     * Finds all possible configuration-spaces for the given setup.
     *
     * @param neighbourSpaces Configuration-spaces of the neighbours.
     * @param node Node to check for.
     * @param templates List of templates to use.
     * @param partSolution All already placed rooms.
     * @return All possible configuration-spaces for node.
     */
    private List<ConfigurationSpace> getCS(
            List<ConfigurationSpace> neighbourSpaces,
            Node node,
            List<RoomTemplate> templates,
            List<ConfigurationSpace> partSolution) {
        List<ConfigurationSpace> possibleSpaces = new ArrayList<>();
        for (ConfigurationSpace cs : neighbourSpaces)
            if (possibleSpaces.isEmpty()) possibleSpaces = calCS(cs, node, templates, partSolution);
            else possibleSpaces.retainAll(calCS(cs, node, templates, partSolution));
        return possibleSpaces;
    }

    /**
     * Calculate the configuration-spaces to find possible positions for the room in the level.
     *
     * @param staticSpace The non-movable component.
     * @param dynamicNode The movable component.
     * @param template The templates to use.
     * @param level All other rooms that are already placed in the level and are not allowed to
     *     overlap.
     * @return all possible configuration-spaces.
     */
    private List<ConfigurationSpace> calCS(
            ConfigurationSpace staticSpace,
            Node dynamicNode,
            List<RoomTemplate> template,
            List<ConfigurationSpace> level) {
        List<ConfigurationSpace> spaces = new ArrayList<>();
        for (RoomTemplate layout : template) {
            List<Point> possiblePoints = new ArrayList<>();
            // this the first node placed in the level.
            if (level.isEmpty()) {
                possiblePoints.add(new Point(0, 0));
            } else {
                possiblePoints = calAttachingPoints(staticSpace, layout);
            }
            for (Point position : possiblePoints) {
                boolean isValid = true;
                for (ConfigurationSpace sp : level)
                    if (sp.overlap(layout, position)) {
                        isValid = false;
                        break;
                    }
                if (isValid) spaces.add(new ConfigurationSpace(layout, dynamicNode, position));
            }
        }
        return spaces;
    }

    /**
     * Calculate where a dynamic room-template can be attached to a static room-template.
     *
     * @param staticSpace Where to attach.
     * @param template What to attach.
     * @return All possible points.
     */
    private List<Point> calAttachingPoints(ConfigurationSpace staticSpace, RoomTemplate template) {
        // todo
        return null;
    }

    /**
     * Remove walls in room-templates to create doors.
     *
     * @param levelLayout All rooms and there positions in the level.
     */
    private void placeDoors(List<ConfigurationSpace> levelLayout) {
        // todo
    }

    /**
     * Checks if a level can be completed. If not, this level should not be used.
     *
     * @param level To check for.
     * @return Can you reach the End-Tile from the Start-Tile?
     */
    private boolean checkIfCompletable(Level level) {
        return level.isTileReachable(level.getStartTile(), level.getEndTile());
    }
}
