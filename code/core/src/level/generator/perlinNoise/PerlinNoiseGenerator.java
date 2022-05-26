package level.generator.perlinNoise;

import java.util.Random;
import java.util.logging.Logger;
import level.elements.Level;
import level.elements.Tile;
import level.generator.IGenerator;
import level.tools.Coordinate;
import level.tools.DesignLabel;
import level.tools.LevelElement;
import level.tools.LevelSize;
import level.tools.TileTextureFactory;

public class PerlinNoiseGenerator implements IGenerator {
    public static final Logger LOG = Logger.getLogger(PerlinNoiseGenerator.class.getName());
    private static final int SMALL_MIN_X_SIZE = 10;
    private static final int SMALL_MIN_Y_SIZE = 10;
    private static final int SMALL_MAX_X_SIZE = 30;
    private static final int SMALL_MAX_Y_SIZE = 30;
    private static final int MEDIUM_MIN_X_SIZE = 30;
    private static final int MEDIUM_MIN_Y_SIZE = 30;
    private static final int MEDIUM_MAX_X_SIZE = 100;
    private static final int MEDIUM_MAX_Y_SIZE = 100;
    private static final int BIG_MIN_X_SIZE = 100;
    private static final int BIG_MIN_Y_SIZE = 100;
    private static final int BIG_MAX_X_SIZE = 300;
    private static final int BIG_MAX_Y_SIZE = 300;

    @Override
    public Level getLevel(DesignLabel designLabel, LevelSize size) {
        final int seed = (int) (Math.random() * Integer.MAX_VALUE);
        LOG.info("Seed: " + seed);
        return getLevel(designLabel, size, new Random(seed));
    }

    /**
     * generates a new level based on the seed
     *
     * <p>the same seed should give the same level
     *
     * @param seed seed of level
     * @return The level.
     */
    public Level getLevel(long seed) {
        final Random random = new Random(seed);
        DesignLabel designLabel = DesignLabel.values()[random.nextInt(DesignLabel.values().length)];
        LevelSize size = LevelSize.values()[random.nextInt(LevelSize.values().length)];
        return getLevel(designLabel, size, random);
    }

    /**
     * generates new Level
     *
     * @param width width of level
     * @param height height of level
     * @param random Random Object used to generate the level
     * @return the generated Level
     */
    public Level getLevel(DesignLabel designLabel, LevelSize size, final Random random) {
        // playing field
        final NoiseArea playingArea = generateNoiseArea(size, random);
        LevelElement[][] elements = toLevelElementArray(playingArea);
        Level generatedLevel = new Level(toTilesArray(elements, designLabel));

        // end tile
        final Tile end = generatedLevel.getEndTile();
        elements[end.getCoordinate().y][end.getCoordinate().x] = LevelElement.EXIT;
        String endTexturePath =
                TileTextureFactory.findTexturePath(
                        elements[end.getCoordinate().y][end.getCoordinate().x],
                        designLabel,
                        elements,
                        end.getCoordinate());
        end.setLevelElement(LevelElement.EXIT, endTexturePath);
        return generatedLevel;
    }

    private static NoiseArea generateNoiseArea(final LevelSize size, final Random randomGenerator) {
        final int width = getWidthFromLevelSize(size, randomGenerator);
        final int height = getHeightFromLevelSize(size, randomGenerator);
        LOG.info("Level dimensions: " + width + " x " + height);
        int octavesAdd = 0;
        switch (size) {
            case LARGE:
                octavesAdd += 1;
            case MEDIUM:
                octavesAdd += 1;
            case SMALL:
            default:
                octavesAdd += 1;
        }
        final PerlinNoise pNoise =
                new PerlinNoise(
                        width,
                        height,
                        new int[] {1 + octavesAdd, 2 + octavesAdd},
                        false,
                        randomGenerator);
        final double[][] noise = pNoise.noiseAll(1);

        final NoiseArea[] areas = NoiseArea.getAreas(0.4, 0.6, noise, false);
        NoiseArea area = areas[0];
        for (final NoiseArea f : areas) {
            if (area.getSize() < f.getSize()) {
                area = f;
            }
        }
        return area;
    }

    private static LevelElement[][] toLevelElementArray(NoiseArea playingArea) {
        LevelElement[][] res = new LevelElement[playingArea.getWidth()][playingArea.getHeight()];
        for (int i = 0; i < playingArea.getWidth(); i++) {
            for (int j = 0; j < playingArea.getHeight(); j++) {
                if (playingArea.contains(i, j)) {
                    res[i][j] = LevelElement.FLOOR;
                } else {
                    res[i][j] = LevelElement.SKIP;
                }
            }
        }
        return res;
    }

    private static Tile[][] toTilesArray(
            final LevelElement[][] levelElements, final DesignLabel design) {
        final Tile[][] res = new Tile[levelElements.length][levelElements[0].length];
        for (int y = 0; y < res.length; y++) {
            for (int x = 0; x < res[0].length; x++) {
                String texturePath =
                        TileTextureFactory.findTexturePath(
                                levelElements[y][x], design, levelElements, new Coordinate(x, y));
                res[y][x] =
                        new Tile(texturePath, new Coordinate(x, y), levelElements[y][x], design);
            }
        }
        return res;
    }

    private static int getWidthFromLevelSize(LevelSize size, Random random) {
        switch (size) {
            case LARGE:
                return random.nextInt(BIG_MAX_X_SIZE - BIG_MIN_X_SIZE) + BIG_MIN_X_SIZE;
            case MEDIUM:
                return random.nextInt(MEDIUM_MAX_X_SIZE - MEDIUM_MIN_X_SIZE) + MEDIUM_MIN_X_SIZE;
            case SMALL:
            default:
                return random.nextInt(SMALL_MAX_X_SIZE - SMALL_MIN_X_SIZE) + SMALL_MIN_X_SIZE;
        }
    }

    private static int getHeightFromLevelSize(LevelSize size, Random random) {
        switch (size) {
            case LARGE:
                return random.nextInt(BIG_MAX_Y_SIZE - BIG_MIN_Y_SIZE) + BIG_MIN_Y_SIZE;
            case MEDIUM:
                return random.nextInt(MEDIUM_MAX_Y_SIZE - MEDIUM_MIN_Y_SIZE) + MEDIUM_MIN_Y_SIZE;
            case SMALL:
            default:
                return random.nextInt(SMALL_MAX_Y_SIZE - SMALL_MIN_Y_SIZE) + SMALL_MIN_Y_SIZE;
        }
    }
}
