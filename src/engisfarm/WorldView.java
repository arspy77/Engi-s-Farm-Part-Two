package engisfarm;

import engisfarm.World;
import engisfarm.cell.Cell;
import engisfarm.farmanimal.FarmAnimal;
import engisfarm.product.Product;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/** WorldView adalah kelas yang menggambar World */
@SuppressWarnings("serial")    
public class WorldView extends JPanel {


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    private Image getCharImage(char c){
        ImageIcon icon = new ImageIcon("../resources/char/Gold " + Integer.toString((int)c) + ".png");
        return icon.getImage();
    }

    private void drawString(Graphics g, String s, int x, int y){
        for(int i = 0; i < s.length(); i++){
            g.drawImage(getCharImage(s.charAt(i)), x, y, this);
            x += CHARSPACE;
        }
    }

    private void drawFrame(Graphics g, int n, int x, int y){
        ImageIcon icon = new ImageIcon("../resources/frame.png");
        while(n > 0) {
            g.drawImage(icon.getImage(), x, y, this);
            x += SPACE;
            n--;
        }
    }

    /** Class KeyListener untuk menerima input key user*/
    private class KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e){

            int key = e.getKeyCode();
            
            try {    
                switch (key) {
                    case KeyEvent.VK_W:
                        pl.move(Direction.UP);
                        break;
                    
                    case KeyEvent.VK_S:
                        pl.move(Direction.DOWN);
                        break;
                    
                    case KeyEvent.VK_A:
                        pl.move(Direction.LEFT);
                        break;
                    
                    case KeyEvent.VK_D:
                        pl.move(Direction.RIGHT);
                        break;
                    
                    case KeyEvent.VK_T:
                        pl.talk(animalList, mesQueue);
                        break;
                    
                    case KeyEvent.VK_I:
                        pl.interact(animalList);
                        pl.sellAll();
                        pl.takeWater();
                        break;

                    case KeyEvent.VK_G:
                        pl.grow();
                        break;
                    
                    case KeyEvent.VK_K:
                        pl.kill(animalList);
                        break;
                    
                    case KeyEvent.VK_M:
                        pl.mix(in);
                        break;
                    
                    case KeyEvent.VK_H:
                        mesQueue.add("a,w,s,d   Move");
                        mesQueue.add("i         Interaksi dengan hewan atau benda");
                        mesQueue.add("m         Membuat side product pada mixer");
                        mesQueue.add("t         Berbicara dengan hewan");
                        mesQueue.add("k         Menyembelih hewan ternak");
                        mesQueue.add("g         Menumbuhkan rumput pada cell");
                        mesQueue.add("esc       Keluar dari game");
                        break;
                        
                    case KeyEvent.VK_ESCAPE:
                        in.close();
                        break;
                    
                    default:
                        break;
                }
            } catch(GeneralException ge) {
                mesQueue.add(ge.getMessage());
            }            
            repaint();
        }
    }

    public WorldView(World w) {
        addKeyListener(new KeyListener());
        setFocusable(true);

        pl = w.getPlayer();
        map = w.getMap();
        nRowCell = w.getNRow();
        nCollumnCell = w.getNCol();
        animalList = w.getAnimalList();
        mesQueue = w.getMesQueue();
    }

    /**
    * Menggambarkan representasi state program (World) seperti lokasi setiap objek, money, water, 
    * dan Inventory Player, dsb ke layar.
    */
    public void Draw(Graphics g){

        g.setColor(new Color(13, 63, 114));
        g.fillRect(0, 0,(1 + nCollumnCell + 1 + InventoryTabLength/2 + 2 + mesQueueLength/2 + 1) * SPACE, (nRowCell + 2) * SPACE);

        //char[, ] localMap = new char[nRowCell, nCollumnCell];
        for (int i = 0; i < nRowCell; i++) {
            for (int j = 0; j < nCollumnCell; j++) {
                //if ((!map[i, j].getIsOcupied()) || map[i, j].getCategory() == Cell.Category.WELL || map[i, j].getCategory() == Cell.Category.TRUCK || map[i, j].getCategory() == Cell.Category.MIXER){
                g.drawImage(map.get(i, j).render(), (j+1) * SPACE + OFFSET, (i+1) * SPACE + OFFSET, this);
                //}
            }
        }
        g.drawImage(pl.render(), (pl.getPosition().x+1) * SPACE + OFFSET, (pl.getPosition().y+1) * SPACE + OFFSET, this);
        //localMap[pl.getPosition().y, pl.getPosition().x] = 'P';
        for (int i = 0; i < animalList.size(); i++){
            g.drawImage(animalList.get(i).render(), (animalList.get(i).getPosition().x+1) * SPACE + OFFSET, (animalList.get(i).getPosition().y+1) * SPACE + OFFSET, this);
            //localMap[animalList.get(i).getPosition().y, animalList.get(i).getPosition().x] = animalList.get(i).render();
        }

        
        int x = OFFSET;
        int y = OFFSET;

        drawFrame(g, 1 + nCollumnCell + 1 + InventoryTabLength/2 + 1 + 1 + mesQueueLength/2 + 1, x, y); 
        y += SPACE;//log.write("\n");
        for (int i = 0; i < nRowCell; i++) {
            drawFrame(g, 1, x, y); //log.write("#  ");
            x += (1 + nCollumnCell) * SPACE;
            /*for (int j = 0; j < nCollumnCell; j++) {
                log.write(localMap[i, j]);
                if (j != nCollumnCell - 1) {
                    log.write("|");
                }
            }*/
            drawFrame(g, 1, x, y); //log.write("  #");
            x += SPACE;
            if (i == 0){
                drawString(g, " Inventory :", x, y);
            }
            else if (i >= 1 && i < nRowCell - 3) {
                String invObj = "";
                if ((i) < pl.getInventory().size()) {
                    if (pl.getInventory().get(i).getCategory() == Product.Category.CHICKENEGG) {
                        invObj = " Chicken Egg";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.COWMEAT) {
                        invObj = " Cow Meat";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.BEEFCHICKENOMELETTE) {
                        invObj = " Beef Chicken Omelette";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.BEEFHARAMSATE) {
                        invObj = " Beef Muton Sate";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.DUCKMEAT) {
                        invObj = " Duck Meat";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.HORSEMILK) {
                        invObj = " Horse Milk";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.OWLEGG) {
                        invObj = " Owl Egg";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.PIGMEAT) {
                        invObj = " Pig Meat";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.SUPERSECRETSPECIALPRODUCT) {
                        invObj = " Super Secret Special Product";
                    }
                    
                }
                drawString(g, invObj, x, y);//log.write(invObj);
            } else if (i == nRowCell - 3) {
                drawFrame(g, InventoryTabLength/2, x, y);
                
            } else if (i == nRowCell - 2) {
                drawString(g, " Money : " + Integer.toString(pl.getMoney()), x, y); //log.write(" Money : " + pl.getMoney());
            } else if (i == nRowCell - 1) {
                drawString(g, " Water : " + Integer.toString(pl.getWater()), x, y); //log.write(" Money : " + pl.getMoney());
            } 
            x += (InventoryTabLength) * CHARSPACE;            
            drawFrame(g, 1, x, y);
            x += 2 * SPACE;//log.write("# ");
            if (!mesQueue.isEmpty()) {
                drawString(g, mesQueue.remove(0), x, y);//log.write(mesQueue.remove(0));
            }
            x += mesQueueLength * CHARSPACE;
            drawFrame(g, 1, x, y);
            y += SPACE;//log.write("\n");
            x = OFFSET;
        }
        drawFrame(g, 1 + nCollumnCell + 1 + InventoryTabLength/2 + 1 + 1 + mesQueueLength/2 + 1, x, y);  //drawFrame(1 + nCollumnCell*2 + InventoryTabLength + 1 + 4);
    }


    public final int SPACE = 20;
    public final int InventoryTabLength = 30;
    public final int mesQueueLength = 50;
    
    private final int CHARSPACE = 10;
    private final int OFFSET = 0;

    /** Player yang berada pada World */
    private Player pl;

    /** Matrix dari pointer ke seluruh Cell pada World */
    private Matrix<Cell> map;

    /** Nilai efektif baris untuk Matrix Cell */
    private int nRowCell;
    
    /** Nilai efektif kolom untuk Matrix Cell */
    private int nCollumnCell;

    /** LinkedList dari seluruh pointer ke FarmAnimal yang berada pada World 000*/
    private LinkedList<FarmAnimal> animalList;

    /** 
    * Antrian pesan yang akan ditampilkan saat render 
    */
    private LinkedList<String> mesQueue;

    /** Object Scanner untuk menerima input dari user */
    private Scanner in = new Scanner(System.in);
}