package engisfarm.farmanimal;
import engisfarm.Point;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import engisfarm.product.OstrichEgg;

/** Ostrich merupakan kelas turunan dari EggProducer yang menghasilkan OstrichEgg saat diinteract */
public class Ostrich extends EggProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryOstrich = 15;

    /** Constructor */
    public Ostrich(Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungryOstrich, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduct yang akan dihasilkan Ostrich bila Ostrich di interact */
    public FarmProduct produceProduct(FarmAnimal.Action act){
        if (act == FarmAnimal.Action.INTERACT){
            FarmProduct fp = new OstrichEgg();
            this.canProduce = false;
            return fp;
        }
        else{
            return null;
        }  
    }
    
    /** Mengembalikan suara dari Ostrich */
    public String makeNoise(){
        return "bleep~";
    }

    /** Mengembalikan karakter yang merepresentasikan Ostrich saat Hungry dan tidak Hungry */
    public char render(){
        if (this.isHungry()) {
            return 't';
        } else {
            return 'T';
        }
    }
}