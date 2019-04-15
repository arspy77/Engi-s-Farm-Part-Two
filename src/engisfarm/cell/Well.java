package engisfarm.cell;

/** Well merupakan kelas turunan dari Facility yang digunakan untuk memberi Water untuk Player */
public class Well extends Facility {
        /** Return kategori dari objek ini */
        public Category getCategory()
        {
            return category;
        }
        
        /** Menandakan bahwa land bertipe Well */
        public static final Category category = Cell.Category.WELL;
}