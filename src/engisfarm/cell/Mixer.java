package engisfarm.cell;

import java.awt.Image;
import javax.swing.ImageIcon;

/** Mixer merupakan kelas turunan dari Facility yang digunakan untuk membuat SideProduct */
public class Mixer extends  Facility {
    /** Return kategori dari objek ini */
    public Category getCategory()
    {
        return Category.MIXER;
    }

    /** Mengembalikan Image yang menggambarkan Mixer */
    public Image render()
    {
        ImageIcon icon;
        if (isGrassExist())
        {
            icon = new ImageIcon("../resources/mixerGrass.png");
        }
        else
        {
            icon = new ImageIcon("../resources/mixer.png");
        }
        return icon.getImage();
    }
}