package level.generator.perlinNoise;

/** representation of a field */
public class Field {
    private final int x;
    private final int y;

    /**
     * creates a field
     *
     * @param x x coordinate
     * @param y y-coordinate
     */
    public Field(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns the x coordinate
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * returns the y coordinate
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }
}
