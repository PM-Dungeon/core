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
        int edgeCounter = random.nextInt(nodeCounter);
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
     * @param chain The graph split into chains.
     * @param design The Design-Label the level should have.
     * @return The level.
     * @throws NoSolutionException If no solution can be found for the given configuration.
     */
    private Level getLevel(Graph graph, List<Chain> chain, DesignLabel design)
            throws NoSolutionException {
        List<ConfigurationSpace> configurationSpaces = layDownLevel(chain, design);
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
        else return getLevel(graph, chain, design);
    }

    /**
     * Split a graph in chains.
     *
     * @param graph Graph to split
     * @return List with Chains.
     */
    private List<Chain> splitInChains(Graph graph) {
        List<Chain> chains = new ArrayList<>();
        // todo split graoh in chains
        return chains;
    }

    /**
     * @param chains The Graph split into chains.
     * @param design The Design-Label the level should have.
     * @return The level.
     * @throws NoSolutionException If no solution can be found for the given configuration.
     */
    private List<ConfigurationSpace> layDownLevel(List<Chain> chains, DesignLabel design)
            throws NoSolutionException {
        List<ConfigurationSpace> solution = new ArrayList<>();
        List<RoomTemplate> templates = roomLoader.getRoomTemplates(design);

        // todo SOLVE PROBLEM (new methode?)
        // for each chain
        // for each node in chain
        // find neighbours //maybe us a helper class chainNodes?
        // check if left neighbour is already "placed"
        // yes: static
        // no: ignore
        // check if right neighbour is already "placed"
        // yes :static
        // no: ignore
        // no neighbour is placed? (this should mean this one is the first node ever)
        // this position is  0/0 with each layout
        // else calculate configuration spaces for each static neighbour
        // use Intersection in needed
        // if configuration space is empty -> backtrack
        // go to last checkpoint and try another configuration
        // no more backtracking possible? THROW NO SOLUTION
        // if configuration space not empty -> savepoint for backtracking
        // continue

        if (solution.isEmpty())
            throw new NoSolutionException(
                    "No way to convert the given graph into a level using the given templates.");

        placeDoors(solution);
        return solution;
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
    private List<ConfigurationSpace> calculateConfigurationSpace(
            ConfigurationSpace staticSpace,
            Node dynamicNode,
            List<RoomTemplate> template,
            List<ConfigurationSpace> level) {
        List<ConfigurationSpace> spaces = new ArrayList<>();
        for (RoomTemplate layout : template) {
            for (int r = 0; r <= 4; r++) {
                // rotate template 90,180,270,360
                RoomTemplate tmp = layout.rotateTemplate();
                Point p = new Point(0, 0); // todo calculate cs
                boolean isValid = true; // todo check if no other rooms are in the way
                if (isValid) spaces.add(new ConfigurationSpace(tmp, dynamicNode, p));
            }
        }
        return spaces;
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
