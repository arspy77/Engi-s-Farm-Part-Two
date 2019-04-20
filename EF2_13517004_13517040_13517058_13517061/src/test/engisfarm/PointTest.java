package engisfarm;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void manhattanDist() {
        Point a = new Point(), b = new Point();
        a.x = 2;
        a.y = 0;
        b.x = 6;
        b.y = 3;
        assertEquals(7, Point.manhattanDist(a, b));
    }

    @Test
    public void changeComp() {
        Point a = new Point();
        a.x = 5;
        assertEquals(5, a.x);
        a.x = -3;
        assertEquals(-3, a.x);
        a.y = 5;
        assertEquals(5, a.y);
        a.y = -3;
        assertEquals(-3, a.y);
    }
}