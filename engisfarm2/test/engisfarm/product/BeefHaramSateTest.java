package engisfarm.product;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class BeefHaramSateTest {
    @Test
    public void getPrice() {
        Product prod = new BeefHaramSate();
        assertEquals(404000, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new BeefHaramSate();
        assertEquals(Product.Category.BEEFHARAMSATE, prod.getCategory());
    }

    @Test
    public void getRecipe() {
        SideProduct prod = new BeefHaramSate();
        LinkedList<Product> recipe = prod.getRecipe();
        assertEquals(2, recipe.size());
        Product ingred = new CowMeat();
        assertEquals(Product.Category.COWMEAT, recipe.get(0).getCategory());
        ingred = new ChickenEgg();
        assertEquals(Product.Category.PIGMEAT, recipe.get(1).getCategory());
    }
}