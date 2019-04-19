package engisfarm.product;


/** CowMeat adalah kelas turunan dari FarmProduct yang dihasilkan dengan kill Cow */
public class CowMeat implements FarmProduct {

    /** getPrice mengembalikan harga yang didefinisikan */
    public int getPrice() {
        return 200000;
    }


    /** Mengembalikan kategori dari produk */
    public Category getCategory() {
        return Category.COWMEAT;
    }
}