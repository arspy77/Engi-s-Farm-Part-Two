package engisfarm.product;


/** OwlEgg adalah kelas turunan dari FarmProduct yang dihasilkan dengan interact dengan Owl */
public class OwlEgg implements FarmProduct {

    /** getPrice mengembalikan harga yang didefinisikan */
    public int getPrice() {
        return 40000;
    }

    
    /** Mengembalikan kategori dari produk */
    public Category getCategory() {
        return Category.OwlEGG;
    }
}