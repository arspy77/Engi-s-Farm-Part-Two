package engisfarm.farmanimal;

import engisfarm.Point;
import engisfarm.Matrix;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import engisfarm.product.PigMeat;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Pig merupakan kelas turunan dari MeatProducer yang menghasilkan PigMeat saat
 * diinteract
 */
public class Pig extends MeatProducer {
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryPig = 15;

    /** Constructor */
    public Pig(Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell) {
        super(maxTimeToGetHungryPig, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduct yang akan dihasilkan Pig bila Pig di kill */
    public FarmProduct produceProduct(FarmAnimal.Action act) {
        if (act == FarmAnimal.Action.KILL) {
            FarmProduct fp = new PigMeat();
            return fp;
        } else {
            return null;
        }
    }

    /** Mengembalikan suara dari Pig */
    public String makeNoise() {
        return "bu~ bu~";
    }

    /**
     * Mengembalikan Image yang merepresentasikan Pig saat Hungry dan tidak Hungry
     */
    public Image render() {
        ImageIcon icon;
        if (isHungry()) {
            icon = new ImageIcon("../resources/pigHungry.png");
        } else {
            icon = new ImageIcon("../resources/pig.png");
        }
        return icon.getImage();
    }
}