package product;

import java.util.LinkedList;


/** SuperSecretSpecialProduct adalah kelas turunan dari SideProduct yang dihasilkan dengan mix HorseMilk dan OstrichEgg */
public class SuperSecretSpecialProduct extends SideProduct {
    
    /**
    *  Resep SuperSecretSpecialProduct.
    *  Terdiri dari OstrichEgg dan HorseMilk.
    */
    private static LinkedList<Product> recipe;


    /** Menginisialisasi recipe */
    static {
        recipe = new LinkedList<Product>();
        recipe.add(new OstrichEgg());
        recipe.add(new HorseMilk());
    }


    /** getPrice mengembalikan harga yang didefinisikan */  
    public int getPrice() {
        return 999999;
    }

    /** Mengembalikan category dari produk */
    public Category getCategory() {
        return Category.SUPERSECRETSPECIALPRODUCT;
    }

    /** Mengembalikan resep dari produk */
    public LinkedList<Product> getRecipe() {
        return recipe;
    }


}