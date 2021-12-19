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

public class LevelG implements IGenerator {
    private GraphG graphg = new GraphG();
    private RoomTemplateLoader roomLoader;
    private ReplacementLoader replacementLoader;
    private String pathToGraph;

    public LevelG(String pathToRoomTemplates, String pathToReplacements, String pathToGraph) {
        roomLoader = new RoomTemplateLoader(pathToRoomTemplates);
        replacementLoader = new ReplacementLoader(pathToReplacements);
        this.pathToGraph = pathToGraph;
    }

    @Override
    public Level getLevel() {
        Random random = new Random();
        int nodeCounter = random.nextInt(20);
        int edgeCounter = random.nextInt(nodeCounter);
        DesignLabel label = DesignLabel.values()[random.nextInt(DesignLabel.values().length)];
        try {
            return getLevel(nodeCounter, edgeCounter, label);
        } catch (NoSolutionException e) {
            e.printStackTrace();
        }
        return getLevel();
    }

    public Level getLevel(int nodeCounter, int edgeCounter, DesignLabel design)
            throws NoSolutionException {
        Graph graph = graphg.getGraph(nodeCounter, edgeCounter, pathToGraph);
        if (graph == null) throw new NoSolutionException("No Graph found for this configuration");
        List<Node> chain = splitInChains(graph);
        List<Room> rooms = layDownLevel(chain, design);
        Level level = new Level(graph.getNodes(), rooms);
        if (checkIfCompletable(level)) return level;
        else return getLevel(graph, chain, design);
    }

    public Level getLevel(Graph graph, DesignLabel design) throws NoSolutionException {
        List<Node> chain = splitInChains(graph);
        List<Room> rooms = layDownLevel(chain, design);
        Level level = new Level(graph.getNodes(), rooms);
        if (checkIfCompletable(level)) return level;
        else return getLevel(graph, chain, design);
    }

    public Level getLevel(Graph graph, List<Node> chain, DesignLabel design)
            throws NoSolutionException {
        List<Room> rooms = layDownLevel(chain, design);
        Level level = new Level(graph.getNodes(), rooms);
        if (checkIfCompletable(level)) return level;
        else return getLevel(graph, chain, design);
    }

    private List<Node> splitInChains(Graph graph) {
        // todo
        return null;
    }

    private List<Room> layDownLevel(List<Node> chain, DesignLabel design)
            throws NoSolutionException {
        List<RoomTemplate> templates = roomLoader.getRoomTemplates(design);
        List<Replacement> replacements = replacementLoader.getReplacements(design);

        // todo
        List<RoomTemplate> layout = new ArrayList<>();
        List<Point> globalRefs = new ArrayList<>();
        // throw no solution excption
        List<Room> rooms = new ArrayList<>();
        for (RoomTemplate template : layout) {
            int index = layout.indexOf(template);
            rooms.add(template.replace(replacements, globalRefs.get(index)));
        }
        return rooms;
    }

    private boolean checkIfCompletable(Level level) {
        return level.isTileReachable(level.getStartTile(), level.getEndTile());
    }
}
