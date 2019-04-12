package product;


/** SheepMeat adalah kelas turunan dari FarmProduct yang dihasilkan dengan kill Sheep */
public class SheepMeat extends FarmProduct {

    /** getPrice mengembalikan harga yang didefinisikan */
    public int getPrice() {
        return 100000;
    }

    
    /** Mengembalikan kategori dari produk */
    public Category getCategory() {
        return Category.SHEEPMEAT;
    }
}