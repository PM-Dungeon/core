package demo;

import level.elements.Graph;
import level.generator.dungeong.graphg.GraphG;
import level.generator.dungeong.roomg.Replacement;
import level.generator.dungeong.roomg.ReplacementLoader;
import level.tools.DesignLabel;
import level.tools.LevelElement;
import tools.Constants;

import java.util.ArrayList;

public class LayoutCreator {
    public static void main(String[] args) {

        Graph g = new GraphG().getGraph(13, 5, Constants.PATH_TO_GRAPH);
        System.out.println(g.toDot());

        ArrayList<Replacement> templates = new ArrayList<>();
        ReplacementLoader loader = new ReplacementLoader(Constants.PATH_TO_REPLACEMENTS);

        LevelElement[][] layout1 = {
            {LevelElement.FLOOR, LevelElement.WILD, LevelElement.FLOOR},
            {LevelElement.WILD, LevelElement.WILD, LevelElement.WILD},
            {LevelElement.FLOOR, LevelElement.WILD, LevelElement.FLOOR}
        };

        LevelElement[][] layout2 = {
            {LevelElement.FLOOR, LevelElement.FLOOR}, {LevelElement.FLOOR, LevelElement.FLOOR}
        };

        LevelElement[][] layout3 = {
            {
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP
            },
            {
                LevelElement.SKIP,
                LevelElement.WALL,
                LevelElement.FLOOR,
                LevelElement.WALL,
                LevelElement.FLOOR,
                LevelElement.WALL,
                LevelElement.SKIP
            },
            {
                LevelElement.SKIP,
                LevelElement.WALL,
                LevelElement.FLOOR,
                LevelElement.WALL,
                LevelElement.FLOOR,
                LevelElement.WALL,
                LevelElement.SKIP
            },
            {
                LevelElement.SKIP,
                LevelElement.WALL,
                LevelElement.FLOOR,
                LevelElement.WALL,
                LevelElement.FLOOR,
                LevelElement.WALL,
                LevelElement.SKIP
            },
            {
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP
            },
            {
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP
            },
            {
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP,
                LevelElement.SKIP
            }
        };

        Replacement r1 = new Replacement(layout1, true, DesignLabel.ALL);
        Replacement r2 = new Replacement(layout2, true, DesignLabel.ALL);
        Replacement r3 = new Replacement(layout3, true, DesignLabel.ALL);

        templates.add(r1);
        templates.add(r2);
        templates.add(r3);
        loader.writeToJSON(templates, Constants.PATH_TO_REPLACEMENTS);
    }
}
