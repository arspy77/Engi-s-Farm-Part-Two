package engisfarm.product;

import java.util.LinkedList;


/** BeefMuttonSate adalah kelas turunan dari SideProduct yang dihasilkan dengan mix CowMeat dan SheepMeat */
public class BeefMuttonSate extends SideProduct {
    
    /** 
    *  Resep BeefMuttonSate.
    *  Terdiri dari CowMeat dan SheepMeat.
    */
    private static LinkedList<Product> recipe;


    /** Menginisialisasi recipe */
    static {
        recipe = new LinkedList<Product>();
        recipe.add(new CowMeat());
        recipe.add(new SheepMeat());
    }


    /** getPrice mengembalikan harga yang didefinisikan */  
    public int getPrice() {
        return 404000;
    }

    /** Mengembalikan category dari produk */
    public Category getCategory() {
        return Category.BEEFMUTTONSATE;
    }

    /** Mengembalikan resep dari produk */
    public LinkedList<Product> getRecipe() {
        return recipe;
    }


}