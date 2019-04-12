/** Ostrich merupakan kelas turunan dari EggProducer yang menghasilkan OstrichEgg saat diinteract */
public class Ostrich extends EggProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryOstrich = 15;

    /** Constructor */
    public Ostrich(Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungryOstrich, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduct yang akan dihasilkan Ostrich bila Ostrich di interact */
    public FarmProduct produceProduct(Action act){
        FarmProduct fp;
        if (act == INTERACT){
            fp = new OstrichEgg();
            this.canProduce = false;
        }
        return fp;
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