package engisfarm.product;


/** Product adalah kelas abstrak yang merepresentasikan produk yang bisa dibuat dan dijual player */
public interface Product {
    
    /** enumerasi kategori dari suatu produk, return value dari getCategory */
        public enum Category {
        CHICKENEGG, BEEFCHICKENOMELETTE, BEEFHARAMSATE, 
        COWMEAT, DUCKMEAT, HORSEMILK, OWLEGG, 
        PIGMEAT, SUPERSECRETSPECIALPRODUCT;
    }


    /** getPrice mengembalikan harga yang didefinisikan */
    public abstract int getPrice();


    /** mengembalikan kategori dari produk ini */
    public abstract Category getCategory();
}