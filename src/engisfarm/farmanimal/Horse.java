package engisfarm.farmanimal;
import engisfarm.Point;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import engisfarm.product.HorseMilk;

/** Horse merupakan kelas turunan dari MilkProducer yang menghasilkan HorseMilk saat diinteract */
public class Horse extends MilkProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryHorse = 18;

    /** Constructor */
    public Horse(Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
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

    /** Mengembalikan karakter yang merepresentasikan Horse saat Hungry dan tidak Hungry */
    public char render(){
        if (this.isHungry()) {
            return 'h';
        } else {
            return 'H';
        }
    }
}