/** 
 * Kelas FarmAnimal merupakan kelas turunan dari living thing yang dapat berupa
 * EggProducer,MilkProducer,dan MeatProducer\
 * */
public abstract class FarmAnimal extends LivingThing{
    /** Jenis aksi yang dapat dilakukan ke FarmAnimal */
    enum Action {
        INTERACT, KILL
    }

    /** Waktu FarmAnimal sampai menjadi lapar */
    protected int timeToGetHungry;

    /** Nilai max dari timeToGetHungry */
    protected final int maxTimeToGetHungry;

    /** 
     *  Waktu FarmAnimal yang lapar sampai mati
     *  Jika tidak lapar, timeToDeath maksimum
     */
    private int timeToDeath;

    /** Nilai max dari timeToDeath */
    private final int maxTimeToDeath = 5;
    
    /** Constructor maxTimeToGetHungry dengan nilai H */
    public FarmAnimal(int maxTimeToGetHungry, Point position, Cell[][] worldMap, int nRowCell, int nCollumnCell){
        super(positon, worldMap, nRowCell, nCollumnCell);
        this.maxTimeToGetHungry = maxTimeToGetHungry;
        this.timeToGetHungry = maxTimeToGetHungry;
        this.timeToDeath = maxTimeToDeath;
    }

    /** Melakukan aksi yang dilakukan FarmAnimal setiap satuan waktu */
    public void tick(){
        this.moveHeuristically();
        if (this.isHungry()){
            this.timeToDeath--;
        } else {
            this.timeToGetHungry--;
        }
        this.eat();
    }

    /** Mengembalikan produk yang dihasilkan FarmAnimal apabila diinteract/dikill */
    public abstract FarmProduct produceProduct(Action act);

    /** Mengembalikan suara dari FarmAnimal */
    public abstract String makeNoise();

    /** Mengembalikan true jika timeToDeath == 0, lalu di destruct di main atau di class world */
    public boolean isDead(){
        return this.timeToDeath == 0;
    }

    /** Mengembalikan true jika FarmAnimal bisa di Interact untuk menghasilkan Product */
    public abstract boolean getProduce();

    /** Mengembalikan true jika FarmAnimal bisa di Kill untuk menghasilkan Product */
    public abstract boolean getKillable();

    /**
     *  Jika FarmAnimal sedang berdiri pada land dengan rumput,
     *  maka timeToDeath di set nilai semula dan timeToGdengan nilai sesuai dengan derived
     *  classnya, lalu grass di land dihapus
     */
    protected void eat(){
        if (worldMap[this.getPosition().y][this.getPosition().x].isGrassExist()) {    
            timeToDeath = maxTimeToDeath;
            timeToGetHungry = maxTimeToGetHungry;
            worldMap[getPosition().y][getPosition().x].removeGrass();
        }
    }

    /** return true apabila timeToGetHungry <= 0 */
    protected boolean isHungry(){
        return (this.timeToGetHungry == 0);
    }

    /** mengurangi timeToGetHungry */
    private void decTimeToGetHungry(){
        this.timeToGetHungry--;
    }
    
    /** mengurangi timeToDeath */
    private void decTimetoDeath(){
        this.timeToDeath--;
    }
    
    /** Menggerakan FarmAnimal secara random ke posisi yang mungkin ditempati */
    protected void moveHeuristically(){
        Point nearestPoint = new Point(), currPoint = new Point();
        nearestPoint.x = -1;
        nearestPoint.y = -1;
        for (int i = 0; i < nRowCell; i++) {
            for (int j = 0; j < nCollumnCell; j++) {
                if (worldMap[i][j].isGrassExist() && this.canMoveTo(worldMap[i][j])) {
                    currPoint.x = j;
                    currPoint.y = i;
                    if (Point.manhattanDist(this.getPosition(), currPoint) < Point.manhattanDist(this.getPosition(), nearestPoint)){
                        nearestPoint.x = currPoint.x;
                        nearestPoint.y = currPoint.y;
                    }
                }
            }
        } //nearestPoint Found
        Direction d = UP;
        if (nearestPoint.x == -1) {
            int randomInt = (int) Math.random() * 4;
            switch(randomInt){
                case 0:
                    d = LEFT;
                    break;
                case 1:
                    d = RIGHT;
                    break;
                case 2:
                    d = UP;
                    break;
                case 3:
                    d = DOWN;
                    break;
            }
        } else if (Point.manhattanDist(this.getPosition(), nearestPoint) != 0) {
            if (Math.abs(this.getPosition().x - nearestPoint.x) > Math.abs(this.getPosition().y - nearestPoint.y)) {
                if (this.getPosition().x < nearestPoint.x) {
                    d = RIGHT;
                } else {
                    d = LEFT;
                }
            } else {
                if (this.getPosition().y < nearestPoint.y) {
                    d = DOWN;
                } else {
                    d = UP;
                }
            }
        }
        move(d);
    }

    /** Apakah bisa masuk suatu area (cek out of bound, jenis Cell, kekosongan Cell) */
    protected abstract boolean canMoveTo(Cell toWhere);  
}