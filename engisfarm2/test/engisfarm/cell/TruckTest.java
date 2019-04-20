package engisfarm.cell;

import org.junit.Test;

import static org.junit.Assert.*;

public class TruckTest {
    @Test
    public void getCategory() {
        Cell land = new Truck();
        assertEquals(Cell.Category.TRUCK, land.getCategory());
    }

    @Test
    public void getIsOcupied_setIsOcupied() {
        Cell land  = new Truck();
        assertTrue(land.getIsOcupied());
    }

    @Test
    public void isFacility() {
        Cell land  = new Truck();
        assertTrue(land.isFacility());
    }
}