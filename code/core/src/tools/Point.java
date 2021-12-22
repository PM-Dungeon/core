package tools;

/**
 * For easy handling of positions in the dungeon. <br>
 * No getter needed. All attributes are public. <br>
 * Point.x to get x <br>
 * Point.y to get y <br>
 */
public class Point {

    public float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /** Copies the point. */
    public Point(Point p) {
        x = p.x;
        y = p.y;
    }

    /**
     * Compares to Points
     *
     * @param other
     * @return if booth points have the same x and y value
     */
    public boolean equals(Point other) {
        return x == other.x && y == other.y;
    }
}
