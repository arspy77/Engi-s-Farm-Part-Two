package engisfarm.product;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChickenEggTest {
    @Test
    public void getPrice() {
        Product prod = new ChickenEgg();
        assertEquals(2000, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new ChickenEgg();
        assertEquals(Product.Category.CHICKENEGG, prod.getCategory());
    }
}