package engisfarm.cell;

import org.junit.Test;

import static org.junit.Assert.*;

public class GrassLandTest {
    @Test
    public void getCategory() {
        Cell land  = new GrassLand();
        assertEquals(Cell.Category.GRASSLAND, land.getCategory());
    }

    @Test
    public void getIsOcupied_setIsOcupied() {
        Cell land  = new GrassLand();
        assertEquals(false, land.getIsOcupied());
        land.setIsOcupied(true);
        assertEquals(true, land.getIsOcupied());
    }

    @Test
    public void isFacility() {
        Cell land  = new GrassLand();
        assertEquals(false, land.isFacility());
    }

    @Test
    public void isGrassExist_growGrass_removeGrass() {
        Cell land  = new GrassLand();
        assertEquals(false, land.isGrassExist());
        land.growGrass();
        assertEquals(true, land.isGrassExist());
        land.removeGrass();
        assertEquals(false, land.isGrassExist());
    }
}