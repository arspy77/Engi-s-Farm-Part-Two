package engisfarm.farmanimal;

/** Chicken merupakan kelas turunan dari EggProducer yang menghasilkan ChickenEgg saat diinteract */
public class Chicken extends EggProducer{
    /** Nilai dari maxTimeToGetHungry */
    private static final int maxTimeToGetHungryChicken =15;

    /** Constructor */
    public Chicken(Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungryChicken, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduk yang akan dihasilkan Chicken bila Chicken di interact */
    public FarmProduct produceProduct(Action act){
        FarmProduct fp;
        if (act == INTERACT){
            fp = new ChickenEgg();
            this.canProduce = false;
        }
        return fp;
    }
    
    /** Mengembalikan suara dari Chicken */
    public String makeNoise(){
        return "kokekokko~";
    }

    /** Mengembalikan karakter yang merepresentasikan Chicken saat Hungry dan tidak Hungry */
    public char render(){
        if (this.isHungry()) {
            return 'c';
        } else {
            return 'C';
        }
    }
}