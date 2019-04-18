package engisfarm;

import engisfarm.cell.*;
import engisfarm.farmanimal.*;
import engisfarm.product.*;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.util.LinkedList;
import java.util.Random;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/** World adalah kelas yang merepresentasikan dunia yang menyimpan semua Cell dan LivingThing di dalamnya */
public class WorldV extends JPanel {
    /** Object Random agar setiap playthrough berbeda */
    private Random rand = new Random();

    /** Object Scanner untuk menerima input dari user */
    private Scanner in = new Scanner(System.in);


    private final int OFFSET = 0;
    public final int SPACE = 20;
    private final int CHARSPACE = 10;
    public final int InventoryTabLength = 30;
    public final int mesQueueLength = 50;
    
    public int getNRow(){
        return nRowCell;
    }

    public int getNCol(){
        return nCollumnCell;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);
    }

    private Image getCharImage(char c){
        ImageIcon icon = new ImageIcon("../resources/huruf/Gold " + Integer.toString((int)c) + ".png");
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
    
    

    /**
    * Membaca input user dari stdin lalu melakukan aksi sesuai degan spesifikasi,
    * misal, input == MOVELEFT, maka akan dipanggil pl.move(LEFT). 
    * Bila input == INTERACT, maka akan dipanggil pl.interact(animalList), dsb.
    */
    /*public void Input() throws Exception{
        String inp = in.nextLine().toLowerCase();
        if (inp.equals("w")) {
            pl.move(Direction.UP);		
        } else if (inp.equals("s")) {
            pl.move(Direction.DOWN);		
        } else if (inp.equals("a")) {
            pl.move(Direction.LEFT);
        } else if (inp.equals("d")) {
            pl.move(Direction.RIGHT);
        } else if (inp.equals("talk")) {
            pl.talk(animalList, mesQueue);
        } else if (inp.equals("grow")) {
            pl.grow(mesQueue);
        } else if (inp.equals("kill")) {
            pl.kill(animalList);
        } else if (inp.equals("interact")) {
            pl.interact(animalList);
            pl.takeWater();
            pl.sellAll();
        } else if (inp.equals("mix")) {
            pl.mix(mesQueue, in);
        } else if (inp.equals("exit")) {
            in.close();
            throw new Exception();            
        } else if (inp.equals("")){
            mesQueue.add("a,w,s,d	Move");
            mesQueue.add("interact	Interaksi dengan hewan atau benda");
            mesQueue.add("mix		Membuat side product pada mixer");
            mesQueue.add("talk		Berbicara dengan hewan");
            mesQueue.add("kill		Menyembelih hewan ternak");
            mesQueue.add("grow		Menumbuhkan rumput pada cell");
            mesQueue.add("exit		Keluar dari game");
        }
    }*/


    /**
    * Pada World::Update(), setiap fungsi yang dipanggil secara berkala seperti FarmAnimal::tick()
    * akan dipanggil.
    */

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
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.BEEFMUTTONSATE) {
                        invObj = " Beef Muton Sate";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.DUCKMEAT) {
                        invObj = " Duck Meat";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.HORSEMILK) {
                        invObj = " Horse Milk";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.OwlEGG) {
                        invObj = " Owl Egg";
                    } else if (pl.getInventory().get(i).getCategory() == Product.Category.PigMEAT) {
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
        //log.write("\n");
        //log.flush();
    }

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
}