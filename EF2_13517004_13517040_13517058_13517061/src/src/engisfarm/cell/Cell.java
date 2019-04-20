package engisfarm.cell;

import java.awt.Image;

/** Cell adalah kelas abstrak yang merepresentasikan petak pada Engi's farm */
public abstract class Cell {
    /** Jenis kategori Cell */
    public enum Category {
        WELL, MIXER, TRUCK, COOP, GRASSLAND, BARN
    }

    /** Return true jika objek adalah Facility */
    public abstract boolean isFacility();

    /** Return categori dari objek kategori */
    public abstract Category getCategory();

    /** Mengambil nilai boolean isOcupied */
    public boolean getIsOcupied() {
        return isOcupied;
    }

    /** Mengganti nilai boolean isOcupied */
    public void setIsOcupied(boolean a) {
        isOcupied = a;
    }

    /**
     * Menambah air pada cell. Jika bertipe Land akan menumbuhkan rumput. Jika
     * tidak, tidak akan berefek apa-apa.
     */
    public void growGrass() {
        // do nothing
    }

    /** Jika Cell bertipe Land dan memiliki rumput maka rumput akan dihilangkan */
    public void removeGrass() {
        // do nothing
    }

    /** Mengembalikan keberadaan grass jika Cell bertipe Land */
    public abstract boolean isGrassExist();

    /**
     * Flag yang menandakan cell ditempati oleh sesuatu (Player/FarmAnimal/Facility)
     * atau tidak. True bila cell sedang ditempati oleh sesuatu.
     */
    protected boolean isOcupied = false;

    /** Mengembalikan Image yang merepresentasikan Cell */
    public abstract Image render();
}