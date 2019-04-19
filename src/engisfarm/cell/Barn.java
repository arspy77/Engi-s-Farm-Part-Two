package engisfarm.cell;

import java.awt.Image;
import javax.swing.ImageIcon;

/** Barn merupakan kelas turunan dari Land yang hanya bisa ditempati oleh Player dan MeatProducer */
public class Barn extends Land
{
    /** Return kategori dari objek ini */
    public Category getCategory()
    {
        return Category.BARN;
    }


    /** Mengembalikan Image yang menggambarkan Barn */
    public Image render()
    {
        ImageIcon icon;
        if (isGrassExist())
        {
            icon = new ImageIcon("../resources/barnGrass.png");
        }
        else
        {
            icon = new ImageIcon("../resources/barn.png");
        }
        return icon.getImage();
    }
}