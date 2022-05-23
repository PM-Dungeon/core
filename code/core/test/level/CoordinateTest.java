package level;

import static org.junit.Assert.*;

import level.tools.Coordinate;
import org.junit.Before;
import org.junit.Test;
import tools.Point;

public class CoordinateTest {

    private Coordinate coordinate;
    private int x = 3, y = -3;

    @Before
    public void setup() {
        coordinate = new Coordinate(x, y);
    }

    @Test
    public void test_equals() {
        assertTrue(coordinate.equals(new Coordinate(x, y)));
        assertFalse(coordinate.equals(new Coordinate(y, x)));
    }

    @Test
    public void test_toPoint() {
        Point point = coordinate.toPoint();
        assertEquals(point.x, (float) coordinate.x, 0.0f);
        assertEquals(point.y, (float) coordinate.y, 0.0f);
    }
}
