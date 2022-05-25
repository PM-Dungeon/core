package level.generator.perlinNoise;

import java.util.ArrayList;

/** area providing some methods to get areas from perlin noise */
public class NoiseArea extends Area {
    /**
     * generates a new area
     *
     * @param contains array true for all fields it contains
     */
    public NoiseArea(final boolean[][] contains) {
        super(contains);
    }

    /**
     * generates areas from perlin noise
     *
     * @param min lowerBound
     * @param max upperBound
     * @param noiseValues noise
     * @param outerBound flag -> determines if the areas will be inside or outside the bound
     * @return all found areas
     */
    public static Area[] getAreas(
            final double min,
            final double max,
            final double[][] noiseValues,
            final boolean outerBound) {
        final ArrayList<Area> alRes = new ArrayList<>();
        for (int x = 0; x < noiseValues.length; x++) {
            allPixel:
            for (int y = 0; y < noiseValues[x].length; y++) {
                for (final Area f : alRes) {
                    if (f.contains(x, y)) continue allPixel;
                }
                if (checkBound(noiseValues[x][y], min, max, outerBound)) {
                    final boolean[][] isContained =
                            floodFill(min, max, noiseValues, new Field(x, y), outerBound);
                    alRes.add(new Area(isContained));
                }
            }
        }
        return alRes.toArray(new Area[alRes.size()]);
    }

    private static boolean[][] floodFill(
            final double min,
            final double max,
            final double[][] input,
            final Field startfeld,
            final boolean outerBound) {
        final boolean[][] res = new boolean[input.length][input[0].length];
        final ArrayList<Field> queue = new ArrayList<>();
        queue.add(startfeld);

        while (!queue.isEmpty()) {
            final Field aktFeld = queue.remove(0);
            final int x = aktFeld.getX();
            final int y = aktFeld.getY();

            if (checkBound(input[x][y], min, max, outerBound) && !res[x][y]) {
                res[x][y] = true;

                if (x > 0) queue.add(new Field(x - 1, y));
                if (x < input.length - 1) queue.add(new Field(x + 1, y));
                if (y > 0) queue.add(new Field(x, y - 1));
                if (y < input[x].length - 1) queue.add(new Field(x, y + 1));
            }
        }
        return res;
    }

    private static boolean checkBound(
            final double value, final double min, final double max, final boolean outerBound) {
        if (outerBound) {
            return (value <= min || value >= max);
        }
        return (value >= min && value <= max);
    }
}
