package engisfarm.cell;

import org.junit.Test;
import static org.junit.Assert.*;

public class MixerTest {
    @Test
    public void getCategory() {
        Cell land = new Mixer();
        assertEquals(Cell.Category.MIXER, land.getCategory());
    }
    
    @Test
    public void getIsOcupied_setIsOcupied() {
        Cell land  = new Mixer();
        assertTrue(land.getIsOcupied());
    }

    @Test
    public void isFacility() {
        Cell land  = new Mixer();
        assertTrue(land.isFacility());
    }
}