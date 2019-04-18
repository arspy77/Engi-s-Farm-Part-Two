package engisfarm.product;

import java.util.LinkedList;


/** SuperSecretSpecialProduct adalah kelas turunan dari SideProduct yang dihasilkan dengan mix HorseMilk dan OwlEgg */
public class SuperSecretSpecialProduct implements SideProduct {
    
    /**
    *  Resep SuperSecretSpecialProduct.
    *  Terdiri dari OwlEgg dan HorseMilk.
    */
    private static LinkedList<Product> recipe;


    /** Menginisialisasi recipe */
    static {
        recipe = new LinkedList<Product>();
        recipe.add(new OwlEgg());
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