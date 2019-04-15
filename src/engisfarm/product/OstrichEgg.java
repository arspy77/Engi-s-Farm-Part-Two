package engisfarm.product;


/** OstrichEgg adalah kelas turunan dari FarmProduct yang dihasilkan dengan interact dengan Ostrich */
public class OstrichEgg extends FarmProduct {

    /** getPrice mengembalikan harga yang didefinisikan */
    public int getPrice() {
        return 40000;
    }

    
    /** Mengembalikan kategori dari produk */
    public Category getCategory() {
        return Category.OSTRICHEGG;
    }
}