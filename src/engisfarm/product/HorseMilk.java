package engisfarm.product;


/** HorseMilk adalah kelas turunan dari FarmProduct yang dihasilkan dengan interact dengan Horse */
public class HorseMilk extends FarmProduct {

    /** getPrice mengembalikan harga yang didefinisikan */
    public int getPrice() {
        return 35000;
    }

    
    /** Mengembalikan kategori dari produk */
    public Category getCategory() {
        return Category.HORSEMILK;
    }
}