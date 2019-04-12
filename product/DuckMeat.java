package product;


/** kelas turunan dari Farmproduct yang dihasilkan dengan interact dengan duck*/
public class DuckMeat extends FarmProduct {

    /** getPrice mengembalikan harga yang didefinisikan */
    public int getPrice() {
        return 25000;
    }

    
    /** Mengembalikan kategori dari produk */
    public Category getCategory() {
        return Category.DUCKMEAT;
    }
}