package engisfarm.product;


/** ChickenEgg adalah kelas turunan dari FarmProduct yang dihasilkan dengan interact dengan Chicken */
public class ChickenEgg implements FarmProduct {

    /** getPrice mengembalikan harga yang didefinisikan */
    public int getPrice() {
        return 2000;
    }

    
    /** Mengembalikan kategori dari produk */
    public Category getCategory() {
        return Category.CHICKENEGG;
    }
}