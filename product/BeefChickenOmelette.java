package product;

import java.util.LinkedList;


/** BeefChickenOmelette adalah kelas turunan dari SideProduct yang dihasilkan dengan mix CowMeat dan ChickenEgg */
public class BeefChickenOmelette extends SideProduct {
    
    
    /** 
    *  Resep BeefChickenOmelette.
    *  Terdiri dari CowMeat dan ChickenEgg.
    */
    private static LinkedList<Product> recipe;


    /** Menginisialisasi recipe */
    static {
        recipe = new LinkedList<Product>();
        recipe.add(new CowMeat());
        recipe.add(new ChickenEgg());
    }


    /** getPrice mengembalikan harga yang didefinisikan */  
    public int getPrice() {
        return 250000;
    }

    /** Mengembalikan category dari produk */
    public Category getCategory() {
        return Category.BEEFCHICKENOMELETTE;
    }

    /** Mengembalikan resep dari produk */
    public LinkedList<Product> getRecipe() {
        return recipe;
    }


}