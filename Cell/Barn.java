public class Barn extends Land
{
        /** Return kategori dari objek ini */
       public Category getCategory()
       {
           return category;
       }

        /** Menandakan bahwa land bertipe Barn */
        private static final Category category = BARN;
}