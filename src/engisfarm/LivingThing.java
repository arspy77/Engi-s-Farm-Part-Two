package engisfarm;
import engisfarm.cell.Cell;
import engisfarm.Point;
import java.awt.Image;

/** LivingThing adalah kelas abstrak yang merepresentasikan makhluk hidup berupa Player dan FarmAnimal */
public abstract class LivingThing{
    /** Representasi dunia tempat LivingThing tinggal */
    protected Matrix<Cell> worldMap;

    /** Nilai efektif baris untuk Matrix Cell */
    protected int nRowCell;
    
    /** Nilai efektif kolom untuk Matrix Cell */
    protected int nCollumnCell;

    /** Posisi dari LivingThing */
    private Point position;

    /** Constructor LivingThing */
    public LivingThing(Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell){
        this.position = position;
        this.worldMap = worldMap;
        this.nRowCell = nRowCell;
        this.nCollumnCell = nCollumnCell;
    }

    /** Mengembalikan position */
    public Point getPosition(){
        return this.position;
    }

    /** 
     *  Berpindah ke suatu lokasi.
     *  Apabila tidak bisa (!canMoveTo), throw "Cannot move to the direction".
     */
    public void move(Direction toWhere){
        worldMap.get(this.getPosition().y,this.getPosition().x).setIsOcupied(false);
        switch (toWhere){
            case LEFT:
                if ((this.getPosition().x - 1 >= 0 && this.getPosition().x - 1 < nCollumnCell) &&
                    (this.canMoveTo(worldMap.get(this.getPosition().y,(this.getPosition().x - 1))))) {    
                    this.position.x--;
                }
                break;
            case RIGHT:
                if ((this.getPosition().x + 1 >= 0 && this.getPosition().x + 1 < nCollumnCell) &&
                    (this.canMoveTo(worldMap.get(this.getPosition().y,this.getPosition().x + 1)))) {    
                    this.position.x++;
                }
                break;
            case UP:
                if ((this.getPosition().y - 1 >= 0 && this.getPosition().y - 1 < nRowCell) &&
                    (this.canMoveTo(worldMap.get(this.getPosition().y - 1,this.getPosition().x)))) {    
                    this.position.y--;
                }
                break;
            case DOWN:
                if ((this.getPosition().y + 1 >= 0 && this.getPosition().y + 1 < nRowCell) &&
                    (this.canMoveTo(worldMap.get(this.getPosition().y + 1,this.getPosition().x)))) {    
                    this.position.y++;
                }
                break;
        }
        worldMap.get(this.getPosition().y,this.getPosition().x).setIsOcupied(true);
    }

    /** Mengembalikan image untuk dirender ke layar */
    public abstract Image render();
    
    // diganti jadi protected karena abstract gabisa private
    /** Apakah bisa masuk suatu area (cek out of bound, jenis Cell, kekosongan Cell) */
    protected abstract boolean canMoveTo(Cell toWhere);
}