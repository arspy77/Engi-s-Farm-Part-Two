package engisfarm.cell;

/** GrassLand merupakan kelas turunan dari Land yang hanya bisa ditempati oleh Player dan MilkProducer */
public class GrassLand extends Land{
    
        /** Return kategori dari objek ini */
    public Category getCategory()
    {
        return category;
    }
        
        /** Menandakan bahwa Land ini berkategori GrassLand */
    private static final Category category = Cell.Category.GRASSLAND;
}