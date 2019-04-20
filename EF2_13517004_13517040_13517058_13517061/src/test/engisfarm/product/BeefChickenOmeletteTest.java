package engisfarm.product;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class BeefChickenOmeletteTest {
    @Test
    public void getPrice() {
        Product prod = new BeefChickenOmelette();
        assertEquals(250000, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new BeefChickenOmelette();
        assertEquals(Product.Category.BEEFCHICKENOMELETTE, prod.getCategory());
    }

    @Test
    public void getRecipe() {
        SideProduct prod = new BeefChickenOmelette();
        LinkedList<Product> recipe = prod.getRecipe();
        assertEquals(2, recipe.size());
        Product ingred = new CowMeat();
        assertEquals(Product.Category.COWMEAT, recipe.get(0).getCategory());
        ingred = new ChickenEgg();
        assertEquals(Product.Category.CHICKENEGG, recipe.get(1).getCategory());
    }
}