package engisfarm.cell;

import java.awt.Image;
import javax.swing.ImageIcon;

/** Coop merupakan kelas turunan dari Land yang hanya bisa ditempati oleh Player dan EggProducer */
public class Coop extends  Land {
    /** Return kategori dari objek ini */
    public Category getCategory()
    {
        return Category.COOP;
    }

    /** Mengembalikan Image yang menggambarkan Coop */
    public Image render()
    {
        ImageIcon icon;
        if (isGrassExist())
        {
            icon = new ImageIcon("resources/coopGrass.png");
        }
        else
        {
            icon = new ImageIcon("resources/coop.png");
        }
        return icon.getImage();
    }
}