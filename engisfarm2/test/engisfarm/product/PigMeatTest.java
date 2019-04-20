package engisfarm.product;

import org.junit.Test;

import static org.junit.Assert.*;

public class PigMeatTest {
    @Test
    public void getPrice() {
        Product prod = new PigMeat();
        assertEquals(100000, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new PigMeat();
        assertEquals(Product.Category.PIGMEAT, prod.getCategory());
    }
}