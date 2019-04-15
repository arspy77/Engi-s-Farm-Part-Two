package engisfarm.farmanimal;
import engisfarm.Point;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import engisfarm.product.SheepMeat;

/** Sheep merupakan kelas turunan dari MeatProducer yang menghasilkan SheepMeat saat diinteract */
public class Sheep extends MeatProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungrySheep = 15;
    
   /** Constructor */
   public Sheep(Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
       super(maxTimeToGetHungrySheep, position, worldMap, nRowCell, nCollumnCell);
   }

   /** Mengembalikan FarmProduct yang akan dihasilkan Sheep bila Sheep di kill*/
   public FarmProduct produceProduct(FarmAnimal.Action act){
    if (act == FarmAnimal.Action.KILL){
         FarmProduct fp = new SheepMeat();
         return fp;
    }
    else{
        return null;
    }  
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