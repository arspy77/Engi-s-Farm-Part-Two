package engisfarm.farmanimal;
import engisfarm.Point;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import engisfarm.product.CowMeat;
import engisfarm.Matrix;
import java.awt.Image;
import javax.swing.ImageIcon;

/** Cow merupakan kelas turunan dari MeatProducer yang menghasilkan CowMeat saat diinteract */
public class Cow extends MeatProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryCow = 20;
    
   /** Constructor */
   public Cow(Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell){
       super(maxTimeToGetHungryCow, position, worldMap, nRowCell, nCollumnCell);
    }

   /** Mengembalikan FarmProduct yang akan dihasilkan Cow bila Cow di kill*/
   public FarmProduct produceProduct(FarmAnimal.Action act){
       if (act == FarmAnimal.Action.KILL){
            FarmProduct fp = new CowMeat();
            return fp;
       }
       else{
           return null;
       }  
   }
   
   /** Mengembalikan suara dari Cow */
   public String makeNoise(){
       return "mo~ mo~";
   }

   /** Mengembalikan Image yang merepresentasikan Cow saat Hungry dan tidak Hungry */
   public Image render() {
    ImageIcon icon;
    if (isHungry()){
        icon = new ImageIcon("../resources/cowHungry.png");
    }
    else{
        icon = new ImageIcon("../resources/cow.png");
    }
    return icon.getImage();
}
}