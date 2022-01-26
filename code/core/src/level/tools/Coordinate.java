package level.tools;

public class Coordinate {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate c) {
        x = c.x;
        y = c.y;
    }

    public boolean equals(Coordinate o) {
        return x == o.x && y == o.y;
    }
}
