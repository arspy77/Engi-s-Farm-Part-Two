package engisfarm.farmanimal;

/** 
 * MeatProducer adalah kelas abstrak turunan dari FarmAnimal yang tinggal di barn dan
 * dapat menghasilkan daging jiga dilakukan aksi kill
 */
public abstract class MeatProducer extends FarmAnimal{
    /** Constructor maxTimeToGetHungry dengan nilai H */
    public MeatProducer(int maxTimeToGetHungry, Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungry, position, worldMap, nRowCell, nCollumnCell);
    }
    
    /** Mengembalikan nilai dari killable */
    public boolean getKillable(){
        return killable;
    }

    /** Mengembalikan false karena MeatProducer tidak bisa di Interact */
    public boolean getProduce(){
        return false;
    }
    
    /** Mengecek apakah bisa pindah (tidak out of bound, bertipe Barn, tidak ada hewan lain) */
    protected boolean canMoveTo(Cell toWhere){
        return ( !toWhere.getIsOcupied() && (toWhere.getCategory() == BARN) );
    }

    /** 
     * Merepresentasikan nilai bool dari apakah MeatProducer bisa di Kill atau tidak,
     * bernilai true karena Meatproducer bisa di kill untuk menghasilkan Product Meat  
     * */
    private boolean killable = true;
}