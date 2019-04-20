package engisfarm.cell;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Truck merupakan kelas turunan dari Facility yang dapat digunakan untuk
 * menjual seluruh barang di inventori
 */
public class Truck extends Facility {
    /** Return kategori dari objek ini */
    public Category getCategory()
    {
        return Category.TRUCK;
    }

    /** Mengembalikan Image yang menggambarkan Truck */
    public Image render()
    {
        ImageIcon icon;
        if (isGrassExist())
        {
            icon = new ImageIcon("resources/truckGrass.png");
        }
        else
        {
            icon = new ImageIcon("resources/truck.png");
        }
        return icon.getImage();
    }
}