package engisfarm.product;

import java.util.LinkedList;


/** BeefHaramSate adalah kelas turunan dari SideProduct yang dihasilkan dengan mix CowMeat dan PigMeat */
public class BeefHaramSate implements SideProduct {
    
    /** 
    *  Resep BeefHaramSate.
    *  Terdiri dari CowMeat dan PigMeat.
    */
    private static LinkedList<Product> recipe;


    /** Menginisialisasi recipe */
    static {
        recipe = new LinkedList<Product>();
        recipe.add(new CowMeat());
        recipe.add(new PigMeat());
    }


    /** getPrice mengembalikan harga yang didefinisikan */  
    public int getPrice() {
        return 404000;
    }

    /** Mengembalikan category dari produk */
    public Category getCategory() {
        return Category.BEEFHARAMSATE;
    }

    /** Mengembalikan resep dari produk */
    public LinkedList<Product> getRecipe() {
        return recipe;
    }


}