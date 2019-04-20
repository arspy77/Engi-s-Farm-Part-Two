package engisfarm.cell;

import org.junit.Test;

import static org.junit.Assert.*;

public class BarnTest {
    @Test
    public void getCategory() {
        Cell land  = new Barn();
        assertEquals(Cell.Category.BARN, land.getCategory());
    }

    @Test
    public void getIsOcupied_setIsOcupied() {
        Cell land  = new Barn();
        assertFalse(land.getIsOcupied());
        land.setIsOcupied(true);
        assertTrue(land.getIsOcupied());
    }

    @Test
    public void isFacility() {
        Cell land  = new Barn();
        assertFalse(land.isFacility());
    }

    @Test
    public void isGrassExist_growGrass_removeGrass() {
        Cell land  = new Barn();
        assertFalse(land.isGrassExist());
        land.growGrass();
        assertTrue(land.isGrassExist());
        land.removeGrass();
        assertFalse(land.isGrassExist());
    }
}