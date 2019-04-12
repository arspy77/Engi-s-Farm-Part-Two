/** Sheep merupakan kelas turunan dari MeatProducer yang menghasilkan SheepMeat saat diinteract */
public class Sheep extends MeatProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungrySheep = 15;
    
   /** Constructor */
   public Sheep(Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
       super(maxTimeToGetHungrySheep, position, worldMap, nRowCell, nCollumnCell);
   }

   /** Mengembalikan FarmProduct yang akan dihasilkan Sheep bila Sheep di kill*/
   public FarmProduct produceProduct(Action act){
       FarmProduct fp;
       if (act == KILL){
           fp = new SheepMeat();
       }
       return fp;
   }
   
   /** Mengembalikan suara dari Sheep */
   public String makeNoise(){
       return "me~ me~";
   }

   /** Mengembalikan karakter yang merepresentasikan Sheep saat Hungry dan tidak Hungry */
   public char render(){
       if (this.isHungry()) {
           return 's';
       } else {
           return 'S';
       }
   }
}