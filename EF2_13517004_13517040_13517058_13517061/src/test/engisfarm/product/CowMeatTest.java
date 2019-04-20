package engisfarm.product;

import org.junit.Test;

import static org.junit.Assert.*;

public class CowMeatTest {
    @Test
    public void getPrice() {
        Product prod = new CowMeat();
        assertEquals(200000, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new CowMeat();
        assertEquals(Product.Category.COWMEAT, prod.getCategory());
    }
}