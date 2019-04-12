/** Coop merupakan kelas turunan dari Land yang hanya bisa ditempati oleh Player dan EggProducer */
public class Coop extends  Land {
        /** Return kategori dari objek ini */
        public Category getCategory()
        {
            return category;
        }
        /** Menandakan bahwa land bertipe Coop */
        private static final Category category = COOP;
}