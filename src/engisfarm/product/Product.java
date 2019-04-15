package engisfarm.product;


/** Product adalah kelas abstrak yang merepresentasikan produk yang bisa dibuat dan dijual player */
public abstract class Product {
    
    /** enumerasi kategori dari suatu produk, return value dari getCategory */
        public enum Category {
        CHICKENEGG, BEEFCHICKENOMELETTE, BEEFMUTTONSATE, 
        COWMEAT, DUCKMEAT, HORSEMILK, OSTRICHEGG, 
        SHEEPMEAT, SUPERSECRETSPECIALPRODUCT;
    }


    /** getPrice mengembalikan harga yang didefinisikan */
    public abstract int getPrice();


    /** mengembalikan kategori dari produk ini */
    public abstract Category getCategory();


    /** mengembalikan kategori dari produk ini */
    public boolean equals(Product P) {
        return getCategory() == P.getCategory();
    }
}