package engisfarm.product;

import java.util.LinkedList;


/** SideProduct adalah kelas abstrak turunan dari kelas Product yang didapat dari hasil mix */
public abstract class SideProduct extends Product {

    /** Mengembailkan resep dari produk */
    public abstract LinkedList<Product> getRecipe();
}