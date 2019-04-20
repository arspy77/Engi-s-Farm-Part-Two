package engisfarm.product;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class SuperSecretSpecialProductTest {
    @Test
    public void getPrice() {
        Product prod = new SuperSecretSpecialProduct();
        assertEquals(999999, prod.getPrice());
    }

    @Test
    public void getCategory() {
        Product prod = new SuperSecretSpecialProduct();
        assertEquals(Product.Category.SUPERSECRETSPECIALPRODUCT, prod.getCategory());
    }

    @Test
    public void getRecipe() {
        SideProduct prod = new SuperSecretSpecialProduct();
        LinkedList<Product> recipe = prod.getRecipe();
        assertEquals(2, recipe.size());
        Product ingred = new CowMeat();
        assertEquals(Product.Category.OWLEGG, recipe.get(0).getCategory());
        ingred = new ChickenEgg();
        assertEquals(Product.Category.HORSEMILK, recipe.get(1).getCategory());
    }
}