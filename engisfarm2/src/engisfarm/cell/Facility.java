package engisfarm.cell;


/**
 * Facility merupakan kelas turunan dari Cell yang menampung utilitas untuk Player yaitu Truck, Mixer, dan Well
 * dan tidak bisa ditempati oleh LivingThing
 *  */
public abstract class Facility extends Cell {
    /** Constructor untuk set isOcupied jadi true */
    public Facility()
    {
        // Set isOcupied jadi true
        isOcupied = true;
    }
    /** Return true bila Land adalah sebuah facility */
    public boolean isFacility()
    {
        return facility;
    }
    /** Mengembalikan false */
    public boolean isGrassExist()
    {
        return false;
    }

    /** Menandakan bahwa facility */
    private static final boolean facility = true;
}