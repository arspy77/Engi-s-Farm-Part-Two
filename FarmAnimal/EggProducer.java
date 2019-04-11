/** EggProducer merupakan kelas abstrak turunan dari FarmAnimal yang tinggal di Coop dan menghasilkan Egg saat diinteract */
public abstract class EggProducer extends FarmAnimal{
    /** Menentukan apakah FarmAnimal dapat menghasilkan produk apabila diinteract */
    protected boolean canProduce = false;

    /** Constructor maxTimeToGetHungry dengan nilai H */
    public EggProducer(int maxTimeToGetHungry, Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
        super(maxTimeToGetHungry, position, worldMap, nRowCell, nCollumnCell);
    }

    /** Mengembalikan nilai dari canProduce */
    public boolean getProduce(){
        return canProduce;
    }

    /** Mengembalikan false karena EggProducer tidak bisa di kill */
    public boolean getKillable(){
        return false;
    }

    /** Mengubah nilai canProduce */
    protected void eat(){
        super.eat();
        if (this.timeToGetHungry == this.maxTimeToGetHungry){
            this.canProduce = true;
        }
    }

    /** Mengecek apakah bisa pindah (tidak out of bound, bertipe Coop, tidak ada hewan lain) */
    protected boolean canMoveTo(Cell toWhere){
        return (!toWhere.getIsOcupied() && (toWhere.getCategory() == COOP) ); 
    }
}