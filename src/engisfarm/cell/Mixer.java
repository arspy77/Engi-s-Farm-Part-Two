package engisfarm.cell;

/** Mixer merupakan kelas turunan dari Facility yang digunakan untuk membuat SideProduct */
public class Mixer extends  Facility {
        /** Return kategori dari objek ini */
        public Category getCategory()
        {
            return category;
        }
        
    
        /** Menandakan bahwa land bertipe Mixer */
        private static final Category category = Cell.Category.MIXER;
}