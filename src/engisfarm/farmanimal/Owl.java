package engisfarm.farmanimal;

import engisfarm.Point;
import engisfarm.cell.Cell;
import engisfarm.Matrix;
import engisfarm.product.FarmProduct;
import engisfarm.product.OwlEgg;
import java.awt.Image;
import javax.swing.ImageIcon;

/** Owl merupakan kelas turunan dari EggProducer yang menghasilkan OwlEgg saat diinteract */
public class Owl extends EggProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryOwl = 15;

    /** Constructor */
    public Owl(Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungryOwl, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduct yang akan dihasilkan Owl bila Owl di interact */
    public FarmProduct produceProduct(FarmAnimal.Action act){
        if (act == FarmAnimal.Action.INTERACT){
            FarmProduct fp = new OwlEgg();
            this.canProduce = false;
            return fp;
        }
        else{
            return null;
        }  
    }
    
    /** Mengembalikan suara dari Owl */
    public String makeNoise(){
        return "ho~ ho~";
    }

    /** Mengembalikan Image yang merepresentasikan Owl saat Hungry dan tidak Hungry */
    public Image render() {
        ImageIcon icon;
        if (isHungry()){
            icon = new ImageIcon("../resources/owlHungry.png");
        }
        else{
            icon = new ImageIcon("../resources/owl.png");
        }
        return icon.getImage();
    }
}