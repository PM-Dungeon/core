package level.generator.randomwalk;

import java.util.Random;
import level.elements.Level;
import level.generator.IGenerator;
import level.tools.Coordinate;
import level.tools.DesignLabel;
import level.tools.LevelElement;

public class RandomWalkGenerator implements IGenerator {

    private final int MIN_X_SIZE = 30;
    private final int MIN_Y_SIZE = 30;
    private final int MAX_X_SIZE = 300;
    private final int MAX_Y_SIZE = 300;
    private final int MIN_STEPS_FACTOR = 4;
    private final int MAX_STEPS_FACTOR = 2;
    private static Random random = new Random();

    @Override
    public Level getLevel() {
        return getLevel(DesignLabel.randomDesign());
    }

    @Override
    public Level getLevel(DesignLabel designLabel) {
        return new Level(drunkWalk(), designLabel);
    }

    private LevelElement[][] drunkWalk() {
        int xSize = random.nextInt(MIN_X_SIZE, MAX_X_SIZE);
        int ySize = random.nextInt(MIN_Y_SIZE, MAX_Y_SIZE);
        LevelElement[][] layout = new LevelElement[ySize][xSize];
        for (int y = 0; y < ySize; y++)
            for (int x = 0; x < xSize; x++) layout[y][x] = LevelElement.SKIP;

        Coordinate position = new Coordinate(random.nextInt(0, xSize), random.nextInt(0, ySize));
        int steps =
                random.nextInt(
                        (xSize * ySize) / MIN_STEPS_FACTOR, (xSize * ySize) / MAX_STEPS_FACTOR);
        for (; steps > 0; steps--) {
            layout[position.y][position.x] = LevelElement.FLOOR;

            if (random.nextBoolean()) {
                if (random.nextBoolean()) {
                    position.x = Math.min(position.x + 1, xSize - 1);
                } else {
                    position.x = Math.max(position.x - 1, 0);
                }
            } else {
                if (random.nextBoolean()) {
                    position.y = Math.min(position.y + 1, ySize - 1);
                } else {
                    position.y = Math.max(position.y - 1, 0);
                }
            }
        }

        return layout;
    }
}
