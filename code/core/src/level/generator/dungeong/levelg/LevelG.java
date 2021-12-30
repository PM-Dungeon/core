package level.generator.dungeong.levelg;

import level.elements.Graph;
import level.elements.Level;
import level.elements.Room;
import level.generator.IGenerator;
import level.generator.dungeong.graphg.GraphG;
import level.generator.dungeong.graphg.NoSolutionException;
import level.generator.dungeong.roomg.Replacement;
import level.generator.dungeong.roomg.ReplacementLoader;
import level.generator.dungeong.roomg.RoomTemplate;
import level.generator.dungeong.roomg.RoomTemplateLoader;
import level.tools.DesignLabel;

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

    /**
     * Generate a level with random configuration.
     *
     * @return A new level.
     */
    @Override
    public Level getLevel() {
        // todo find good configuration space
        Random random = new Random();
        int nodeCounter = random.nextInt(20);
        int edgeCounter = random.nextInt(nodeCounter);
        DesignLabel label = DesignLabel.values()[random.nextInt(DesignLabel.values().length)];
        try {
            return getLevel(nodeCounter, edgeCounter, label);
        } catch (NoSolutionException e) {
            // e.printStackTrace();
            // retry
            return getLevel();
        }
    }

    /**
     * Generate a Level with a given configuration.
     *
     * @param nodeCounter Number of room the level should have.
     * @param edgeCounter Number of extra edges the level should have.
     * @param design The Design-Label the level should have.
     * @return The level.
     * @throws NoSolutionException If no solution can be found for the given configuration.
     */
    public Level getLevel(int nodeCounter, int edgeCounter, DesignLabel design)
            throws NoSolutionException {
        Graph graph = graphg.getGraph(nodeCounter, edgeCounter, pathToGraph);
        if (graph == null) throw new NoSolutionException("No Graph found for this configuration");
        return getLevel(graph, design);
    }

    /**
     * Generate a Level from a given graph.
     *
     * @param graph The Level-Graph.
     * @param design The Design-Label the level should have.
     * @return The level.
     * @throws NoSolutionException If no solution can be found for the given configuration.
     */
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
    public Level getLevel(Graph graph, List<Chain> chain, DesignLabel design)
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
     * @param chain The Graph split into chains.
     * @param design The Design-Label the level should have.
     * @return The level.
     * @throws NoSolutionException If no solution can be found for the given configuration.
     */
    private List<ConfigurationSpace> layDownLevel(List<Chain> chain, DesignLabel design)
            throws NoSolutionException {
        List<ConfigurationSpace> solution = new ArrayList<>();
        List<RoomTemplate> templates = roomLoader.getRoomTemplates(design);

        // todo SOLVE PROBLEM

        if (solution.isEmpty())
            throw new NoSolutionException(
                    "No way to convert the given graph into a level using the given templates.");
        return solution;
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
