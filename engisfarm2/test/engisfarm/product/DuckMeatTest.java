package engisfarm.product;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuckMeatTest {
    @Test
    public void getPrice() {
        Product prod = new DuckMeat();
        assertEquals(25000, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new DuckMeat();
        assertEquals(Product.Category.DUCKMEAT, prod.getCategory());
    }
}