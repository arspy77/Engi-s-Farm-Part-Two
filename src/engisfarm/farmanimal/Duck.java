package engisfarm.farmanimal;

import engisfarm.Point;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import engisfarm.product.DuckMeat;
import engisfarm.Matrix;
import java.awt.Image;
import javax.swing.ImageIcon;

/** Duck merupakan kelas turunan dari MeatProducer yang menghasilkan DuckMeat saat diinteract */
public class Duck extends MeatProducer{
     /** Nilai dari maxTimeToGetHungry */
     private static final int maxTimeToGetHungryDuck = 15;
     
    /** Constructor */
    public Duck(Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungryDuck, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduk yang akan dihasilkan Duck bila Duck di kill*/
    public FarmProduct produceProduct(FarmAnimal.Action act){
        if (act == FarmAnimal.Action.KILL){
             FarmProduct fp = new DuckMeat();
             return fp;
        }
        else{
            return null;
        }  
    }
    
    /** Mengembalikan suara dari Duck */
    public String makeNoise(){
        return "ga~ ga~";
    }

    /** Mengembalikan Image yang merepresentasikan Duck saat Hungry dan tidak Hungry */
    public Image render() {
        ImageIcon icon;
        if (isHungry()){
            icon = new ImageIcon("../resources/duckHungry.png");
        }
        else{
            icon = new ImageIcon("../resources/duck.png");
        }
        return icon.getImage();
    }
}