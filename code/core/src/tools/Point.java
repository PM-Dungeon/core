package tools;

public class Point {

    public float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy Point
     *
     * @param p
     */
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
}
