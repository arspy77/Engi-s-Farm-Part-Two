package engisfarm.farmanimal;

/** Duck merupakan kelas turunan dari MeatProducer yang menghasilkan DuckMeat saat diinteract */
public class Duck extends MeatProducer{
     /** Nilai dari maxTimeToGetHungry */
     private static final int maxTimeToGetHungryDuck = 15;
     
    /** Constructor */
    public Duck(Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungryDuck, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan FarmProduk yang akan dihasilkan Duck bila Duck di kill*/
    public FarmProduct produceProduct(Action act){
        FarmProduct fp;
        if (act == KILL){
            fp = new DuckMeat();
        }
        return fp;
    }
    
    /** Mengembalikan suara dari Duck */
    public String makeNoise(){
        return "ga~ ga~";
    }

    /** Mengembalikan karakter yang merepresentasikan Duck saat Hungry dan tidak Hungry */
    public char render(){
        if (this.isHungry()) {
            return 'd';
        } else {
            return 'D';
        }
    }
}