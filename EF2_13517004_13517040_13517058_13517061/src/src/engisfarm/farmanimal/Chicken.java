package engisfarm.farmanimal;

import engisfarm.Point;
import engisfarm.Matrix;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import engisfarm.product.ChickenEgg;
import java.awt.Image;
import javax.swing.ImageIcon;

/** Chicken merupakan kelas turunan dari EggProducer yang menghasilkan ChickenEgg saat diinteract */
public class Chicken extends EggProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryChicken = 15;

    /** Constructor */
    public Chicken(Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungryChicken, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduk yang akan dihasilkan Chicken bila Chicken di interact */
    public FarmProduct produceProduct(FarmAnimal.Action act){
        if (act == FarmAnimal.Action.INTERACT){
            FarmProduct fp = new ChickenEgg();
            this.canProduce = false;
            return fp;
        }
        else{
            return null;
        }
    }

    /** Mengembalikan suara dari Chicken */
    public String makeNoise(){
        return "kokekokko~";
    }

    /** Mengembalikan Image yang merepresentasikan Chicken saat Hungry dan tidak Hungry */
    public Image render() {
        ImageIcon icon;
        if (isHungry()){
            icon = new ImageIcon("resources/chickenHungry.png");
        }
        else{
            icon = new ImageIcon("resources/chicken.png");
        }
        return icon.getImage();
    }
}