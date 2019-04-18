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
public class WorldController extends JPanel implements Runnable {
    /** Menjalankan thread  */
    public void run() {
        try {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Update();
                    repaint();
                } catch (Exception exp){
                    break;
                }
            }
        }catch(Exception e){

        }
    }

    /** Object Scanner untuk menerima input dari user */
    private Scanner in = new Scanner(System.in);


    private final int OFFSET = 0;
    public final int SPACE = 20;
    private final int CHARSPACE = 10;
    public final int InventoryTabLength = 30;
    public final int mesQueueLength = 50;
    
    private Random rand = new Random();
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
    
    public int getNRow(){
        return nRowCell;
    }

    public int getNCol(){
        return nCollumnCell;
    }
    
    /** 
    * Constructor World.
    * Memanggil ctor dan menginisialisasi semua atribut world;
    * Pertama, map diinisialisasi sesuai dengan spesifikasi, saat penginisialisasian map, ctor untuk 
    * object riil dari cell seperti coop, barn, dan well dipanggil
    * Kedua, ctor Player dipanggil dengan argumen Point lokasi awal player dan reference ke map yang sudah 
    * didefinisikan pada tahap pertama
    * Terakhir, animalList diinisialisasi dengan beberapa FarmAnimal secara random
    */ 

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
    * Pada Update(), setiap fungsi yang dipanggil secara berkala seperti FarmAnimal::tick()
    * akan dipanggil.
    */
    public void Update(){
        int x, y;
        for (int i = 0; i < rand.nextInt((int)(nRowCell+nCollumnCell)*20/40); i++) {
            x = rand.nextInt(nCollumnCell);
            y = rand.nextInt(nRowCell);
            map.get(y, x).growGrass();
        }
    
        for (int i = animalList.size() - 1; i >= 0; i--) {
            animalList.get(i).tick();
            if (animalList.get(i).isDead()) {
                map.get(animalList.get(i).getPosition().y, animalList.get(i).getPosition().x).setIsOcupied(false);
                animalList.remove(i);
            }
        }
    
        mesQueue.add("========RECIPE=======");
        for (int i = 0; i < pl.getrecipeBook().size(); i++) {
            String sideProd = "";
            if (pl.getrecipeBook().get(i).getCategory() == Product.Category.BEEFCHICKENOMELETTE) {
                    sideProd = ".  *Beef Chicken Omelette*";
            } else if (pl.getrecipeBook().get(i).getCategory() == Product.Category.BEEFMUTTONSATE) {
                sideProd = ".  *Beef Muton Sate*";
            } else if (pl.getrecipeBook().get(i).getCategory() == Product.Category.SUPERSECRETSPECIALPRODUCT) {
                sideProd = ".  *Super Secret Special Product*";
            }
            mesQueue.add((i+1) + sideProd);
            for (int j = 0; j < pl.getrecipeBook().get(i).getRecipe().size(); j++){
                String prod = "";
                if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.CHICKENEGG) {
                    prod = "       -Chicken Egg";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.COWMEAT) {
                    prod = "       -Cow Meat";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.BEEFCHICKENOMELETTE) {
                    prod = "       -Beef Chicken Omelette";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.BEEFMUTTONSATE) {
                    prod = "       -Beef Muton Sate";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.DUCKMEAT) {
                    prod = "       -Duck Meat";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.HORSEMILK) {
                    prod = "       -Horse Milk";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.OwlEGG) {
                    prod = "       -Owl Egg";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.PigMEAT) {
                    prod = "       -Pig Meat";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.SUPERSECRETSPECIALPRODUCT) {
                    prod = "       -Super Secret Special Product";
                }
                mesQueue.add(prod);
            }
            /*switch (i) :
                case 0:	
                    mesQueue.add("*BeefChickenOmelette*");
                    mesQueue.add(" -Beef Meat          ");
                    mesQueue.add(" -Chicken Egg        ");
                    break;*/
        }
    }


    /**
    * Menggambarkan representasi state program (World) seperti lokasi setiap objek, money, water, 
    * dan Inventory Player, dsb ke layar.
    */

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