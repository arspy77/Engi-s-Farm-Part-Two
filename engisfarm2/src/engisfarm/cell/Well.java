package engisfarm.cell;

import java.awt.Image;
import javax.swing.ImageIcon;

/** Well merupakan kelas turunan dari Facility yang digunakan untuk memberi Water untuk Player */
public class Well extends Facility {
    /** Return kategori dari objek ini */
    public Category getCategory()
    {
        return Category.WELL;
    }


    /** Mengembalikan Image yang menggambarkan Well */
    public Image render()
    {
        ImageIcon icon;
        if (isGrassExist())
        {
            icon = new ImageIcon("resources/wellGrass.png");
        }
        else
        {
            icon = new ImageIcon("resources/well.png");
        }
        return icon.getImage();
    }
}