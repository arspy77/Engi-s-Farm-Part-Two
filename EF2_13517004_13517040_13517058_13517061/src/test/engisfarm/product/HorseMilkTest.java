package engisfarm.product;

import org.junit.Test;

import static org.junit.Assert.*;

public class HorseMilkTest {
    @Test
    public void getPrice() {
        Product prod = new HorseMilk();
        assertEquals(35000, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new HorseMilk();
        assertEquals(Product.Category.HORSEMILK, prod.getCategory());
    }
}