package engisfarm.farmanimal;

import engisfarm.Point;
import engisfarm.Matrix;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import engisfarm.product.HorseMilk;
import java.awt.Image;
import javax.swing.ImageIcon;

/** Horse merupakan kelas turunan dari MilkProducer yang menghasilkan HorseMilk saat diinteract */
public class Horse extends MilkProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryHorse = 18;

    /** Constructor */
    public Horse(Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungryHorse, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduct yang akan dihasilkan Horse bila Horse di interact */
    public FarmProduct produceProduct(FarmAnimal.Action act){
        if (act == FarmAnimal.Action.INTERACT){
            FarmProduct fp = new HorseMilk();
            this.canProduce = false;
            return fp;
        }
        else{
            return null;
        }
    }

    /** Mengembalikan suara dari Horse */
    public String makeNoise(){
        return "hihiin~";
    }

    /** Mengembalikan Image yang merepresentasikan Horse saat Hungry dan tidak Hungry */
    public Image render() {
        ImageIcon icon;
        if (isHungry()){
            icon = new ImageIcon("resources/horseHungry.png");
        }
        else{
            icon = new ImageIcon("resources/horse.png");
        }
        return icon.getImage();
    }
}