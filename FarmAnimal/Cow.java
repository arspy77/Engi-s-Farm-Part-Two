/** Cow merupakan kelas turunan dari MeatProducer yang menghasilkan CowMeat saat diinteract */
public class Cow extends MeatProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryCow = 20;
    
   /** Constructor */
   public Cow(Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
       super(maxTimeToGetHungryCow, position, worldMap, nRowCell, nCollumnCell);
   }

   /** Mengembalikan FarmProduct yang akan dihasilkan Cow bila Cow di kill*/
   public FarmProduct produceProduct(Action act){
       FarmProduct fp;
       if (act == KILL){
           fp = new CowMeat();
       }
       return fp;
   }
   
   /** Mengembalikan suara dari Cow */
   public String makeNoise(){
       return "mo~ mo~";
   }

   /** Mengembalikan karakter yang merepresentasikan Cow saat Hungry dan tidak Hungry */
   public char render(){
       if (this.isHungry()) {
           return 'b';
       } else {
           return 'B';
       }
   }
}