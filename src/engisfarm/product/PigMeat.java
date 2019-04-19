package engisfarm.product;


/** PigMeat adalah kelas turunan dari FarmProduct yang dihasilkan dengan kill Pig */
public class PigMeat implements FarmProduct {

    /** getPrice mengembalikan harga yang didefinisikan */
    public int getPrice() {
        return 100000;
    }

    
    /** Mengembalikan kategori dari produk */
    public Category getCategory() {
        return Category.PIGMEAT;
    }
}