package engisfarm;

import engisfarm.cell.GrassLand;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void get() {
        Matrix<Integer> map = new Matrix<Integer>(10, 10, new Integer(5));
        assertEquals(new Integer(5), map.get(2, 3));
    }

    @Test
    public void set() {
        Matrix<Integer> map = new Matrix<Integer>(10, 10, new Integer(5));
        assertEquals(new Integer(5), map.get(2, 3));
        map.set(1, 1, new Integer(21));
        assertEquals(new Integer(21), map.get(1, 1));
        assertEquals(new Integer(5), map.get(2, 3));
    }
}