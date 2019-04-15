package engisfarm.cell;

/** Truck merupakan kelas turunan dari Facility yang dapat digunakan untuk
 *  menjual seluruh barang di inventori */
public class Truck extends Facility {
        /** Return kategori dari objek ini */
        public Category getCategory()
        {
            return category;
        }
        
        /** Menandakan bahwa land bertipe Truck */
        private static final Category category = TRUCK;
}