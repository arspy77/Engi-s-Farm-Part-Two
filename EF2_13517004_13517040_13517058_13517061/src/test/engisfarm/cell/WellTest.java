package engisfarm.cell;

import org.junit.Test;

import static org.junit.Assert.*;

public class WellTest {
    @Test
    public void getCategory() {
        Cell land = new Well();
        assertEquals(Cell.Category.WELL, land.getCategory());
    }

    @Test
    public void getIsOcupied_setIsOcupied() {
        Cell land  = new Well();
        assertTrue(land.getIsOcupied());
    }

    @Test
    public void isFacility() {
        Cell land  = new Well();
        assertTrue(land.isFacility());
    }
}