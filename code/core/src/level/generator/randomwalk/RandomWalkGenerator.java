package level.generator.randomwalk;

import java.util.Random;
import level.elements.Level;
import level.generator.IGenerator;
import level.tools.Coordinate;
import level.tools.DesignLabel;
import level.tools.LevelElement;
import level.tools.LevelSize;

public class RandomWalkGenerator implements IGenerator {

    private static Random random = new Random();
    private final int SMALL_MIN_X_SIZE = 10;
    private final int SMALL_MIN_Y_SIZE = 10;
    private final int SMALL_MAX_X_SIZE = 30;
    private final int SMALL_MAX_Y_SIZE = 30;
    private final int MEDIUM_MIN_X_SIZE = 30;
    private final int MEDIUM_MIN_Y_SIZE = 30;
    private final int MEDIUM_MAX_X_SIZE = 100;
    private final int MEDIUM_MAX_Y_SIZE = 100;
    private final int BIG_MIN_X_SIZE = 100;
    private final int BIG_MIN_Y_SIZE = 100;
    private final int BIG_MAX_X_SIZE = 300;
    private final int BIG_MAX_Y_SIZE = 300;
    private final int MIN_STEPS_FACTOR = 4;
    private final int MAX_STEPS_FACTOR = 2;

    @Override
    public Level getLevel(DesignLabel designLabel, LevelSize size) {
        switch (size) {
            case SMALL:
                return new Level(
                        drunkWalk(
                                SMALL_MIN_X_SIZE,
                                SMALL_MAX_X_SIZE,
                                SMALL_MIN_Y_SIZE,
                                SMALL_MAX_Y_SIZE),
                        designLabel);
            case MEDIUM:
            default:
                return new Level(
                        drunkWalk(
                                MEDIUM_MIN_X_SIZE,
                                MEDIUM_MAX_X_SIZE,
                                MEDIUM_MIN_Y_SIZE,
                                MEDIUM_MAX_Y_SIZE),
                        designLabel);
            case LARGE:
                return new Level(
                        drunkWalk(BIG_MIN_X_SIZE, BIG_MAX_X_SIZE, BIG_MIN_Y_SIZE, BIG_MAX_Y_SIZE),
                        designLabel);
        }
    }

    private LevelElement[][] drunkWalk(int minX, int maxX, int minY, int maxY) {
        int xSize = random.nextInt(minX, maxX);
        int ySize = random.nextInt(minY, maxY);
        LevelElement[][] layout = new LevelElement[ySize][xSize];
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                layout[y][x] = LevelElement.SKIP;
            }
        }

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
