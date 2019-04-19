package engisfarm.cell;

import java.awt.Image;
import javax.swing.ImageIcon;

/** GrassLand merupakan kelas turunan dari Land yang hanya bisa ditempati oleh Player dan MilkProducer */
public class GrassLand extends Land{

    /** Return kategori dari objek ini */
    public Category getCategory()
    {
        return Category.GRASSLAND;
    }

    /** Mengembalikan Image yang menggambarkan GrassLand */
    public Image render()
    {
        ImageIcon icon;
        if (isGrassExist())
        {
            icon = new ImageIcon("resources/grassLandGrass.png");
        }
        else
        {
            icon = new ImageIcon("resources/grassLand.png");
        }
        return icon.getImage();
    }
}