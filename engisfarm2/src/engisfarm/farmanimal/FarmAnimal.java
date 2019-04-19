package engisfarm.farmanimal;

import engisfarm.LivingThing;
import engisfarm.Matrix;
import engisfarm.Point;
import engisfarm.Direction;
import engisfarm.cell.Cell;
import engisfarm.product.FarmProduct;
import java.util.Random;
import java.awt.Image;

/**
 * Kelas FarmAnimal merupakan kelas turunan dari living thing yang dapat berupa
 * EggProducer, MilkProducer, dan MeatProducer
 * */
public abstract class FarmAnimal extends LivingThing{
    /** FarmAnimal jalan sendiri berdasarkan selang waktu tertentu */
    /*public void run()
    {
        System.out.println("animal");
        try{
        Thread.sleep(1000);
        tick();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    /** Jenis aksi yang dapat dilakukan ke FarmAnimal */
    public enum Action {
        INTERACT, KILL
    }

    /** Object Random agar setiap playthrough berbeda */
    private Random rand = new Random();

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
    public FarmAnimal(int maxTimeToGetHungry, Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell){
        super(position, worldMap, nRowCell, nCollumnCell);
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
        if (worldMap.get(this.getPosition().y, this.getPosition().x).isGrassExist()) {
            timeToDeath = maxTimeToDeath;
            timeToGetHungry = maxTimeToGetHungry;
            worldMap.get(getPosition().y, getPosition().x).removeGrass();
        }
    }

    /** return true apabila timeToGetHungry dibawah atau sama dengan 0 */
    protected boolean isHungry(){
        return (this.timeToGetHungry == 0);
    }

    /** Menggerakan FarmAnimal secara random ke posisi yang mungkin ditempati */
    protected void moveHeuristically(){
        Point nearestPoint = new Point(), currPoint = new Point();
        nearestPoint.x = -1;
        nearestPoint.y = -1;
        for (int i = 0; i < nRowCell; i++) {
            for (int j = 0; j < nCollumnCell; j++) {
                if (worldMap.get(i, j).isGrassExist() && this.canMoveTo(worldMap.get(i, j))) {
                    currPoint.x = j;
                    currPoint.y = i;
                    if (Point.manhattanDist(this.getPosition(), currPoint) < Point.manhattanDist(this.getPosition(), nearestPoint)){
                        nearestPoint.x = currPoint.x;
                        nearestPoint.y = currPoint.y;
                    }
                }
            }
        } //nearestPoint Found
        Direction d = Direction.UP;
        if (nearestPoint.x == -1) {
            int randomInt = rand.nextInt(4);
            switch(randomInt){
                case 0:
                    d = Direction.LEFT;
                    break;
                case 1:
                    d = Direction.RIGHT;
                    break;
                case 2:
                    d = Direction.UP;
                    break;
                case 3:
                    d = Direction.DOWN;
                    break;
            }
        } else if (Point.manhattanDist(this.getPosition(), nearestPoint) != 0) {
            if (Math.abs(this.getPosition().x - nearestPoint.x) > Math.abs(this.getPosition().y - nearestPoint.y)
                    && ((this.getPosition().x < nCollumnCell-1 && this.canMoveTo(worldMap.get(this.getPosition().y, this.getPosition().x+1)))
                    || (this.getPosition().x > 0 && this.canMoveTo(worldMap.get(this.getPosition().y, this.getPosition().x-1))) ) ) {
                if (this.getPosition().x < nearestPoint.x
                        && this.getPosition().x < nCollumnCell-1
                        && this.canMoveTo(worldMap.get(this.getPosition().y, this.getPosition().x+1))) {
                    d = Direction.RIGHT;
                } else {
                    d = Direction.LEFT;
                }
            } else {
                if (this.getPosition().y < nearestPoint.y
                        && this.getPosition().y < nRowCell-1
                        && this.canMoveTo(worldMap.get(this.getPosition().y+1, this.getPosition().x))) {
                    d = Direction.DOWN;
                } else {
                    d = Direction.UP;
                }
            }
        }
        move(d);
    }

    /** Apakah bisa masuk suatu area (cek out of bound, jenis Cell, kekosongan Cell) */
    protected abstract boolean canMoveTo(Cell toWhere);


    /** Mengembalikan Image yang merepresentasikan FarmAnimal saat Hungry dan tidak Hungry */
    public abstract Image render();
}