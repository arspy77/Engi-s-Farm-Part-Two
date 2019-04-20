package engisfarm.product;

import org.junit.Test;

import static org.junit.Assert.*;

public class OwlEggTest {
    @Test
    public void getPrice() {
        Product prod = new OwlEgg();
        assertEquals(40000, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new OwlEgg();
        assertEquals(Product.Category.OWLEGG, prod.getCategory());
    }
}