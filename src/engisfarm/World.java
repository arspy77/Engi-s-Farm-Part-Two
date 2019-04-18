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
public class World extends JPanel implements Runnable {
    /** Menjalankan thread  */
    public void run()
    {
        try{
            
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
    /** Object Random agar setiap playthrough berbeda */
    private Random rand = new Random();

    /** Object Scanner untuk menerima input dari user */
    private Scanner in = new Scanner(System.in);


    private final int OFFSET = 0;
    public final int SPACE = 20;
    private final int CHARSPACE = 10;
    public final int InventoryTabLength = 30;
    public final int mesQueueLength = 50;
    
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
    
    /** Mengembalikan banyak digit dari suatu bilangan positif */
    private int intLen(int x) {
        if (x != 0) {	
            int len = 0;
            while (x != 0) {
                x /= 10;
                len++;
            }
            return len;
        } else {
            return 1;
        }
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
    public World(int _nRowCell, int _nCollumnCell){
        // KeyListener untuk key binding controls
        addKeyListener(new KeyListener());
        setFocusable(true);

        //inisialisasi buffered writer
        //this.log = log;

        // Inisialisasi LinkedList
        mesQueue = new LinkedList<String>();
        animalList = new LinkedList<FarmAnimal>();
        nRowCell = _nRowCell;
        nCollumnCell = _nCollumnCell;
        map = new Matrix<Cell>(nRowCell, nCollumnCell, new GrassLand());
        for (int i = 0; i < nRowCell; i++) {
            for (int j = 0; j < nCollumnCell; j++) {
                map.set(i,j,new GrassLand());
            }
        }
        for (int i = 0; i < (nRowCell*6/10); i++) {
            for (int j = 0; j < (nCollumnCell*4/11) ; j++) {
                map.set(i,j,new Coop());
            }
        }
        for (int i = 0; i < (nRowCell*6/10); i++) {
            for (int j = (nRowCell*5/11); j < (nCollumnCell*8/11); j++) {
                map.set(i,j,new Barn());
            }
        }
        map.set(0, nCollumnCell - 1, new Truck());
        map.set(1, nCollumnCell - 1, new Mixer());
        map.set(3,(nCollumnCell - 1),new Well());
        Point pLoc = new Point();
        pLoc.x = nCollumnCell / 2;
        pLoc.y = nRowCell / 2;
        pl = new Player(pLoc, map, nRowCell, nCollumnCell);
        map.get(pl.getPosition().y, pl.getPosition().x).setIsOcupied(true);
    
        //Horse
        for (int i = 0; i < (nRowCell+nCollumnCell)*15/40; i++) {
            pLoc = new Point();
            pLoc.x = rand.nextInt(nCollumnCell);
            pLoc.y = rand.nextInt(nRowCell);
            while ((map.get(pLoc.y, pLoc.x).getCategory() != Cell.Category.GRASSLAND) || //bukan di grassland
                map.get(pLoc.y, pLoc.x).getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = rand.nextInt(nCollumnCell);
                pLoc.y = rand.nextInt(nRowCell);
            }
            animalList.add(new Horse(pLoc, map, nRowCell, nCollumnCell));
            map.get(pLoc.y, pLoc.x).setIsOcupied(true);
        }

        //Chicken
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc = new Point();
            pLoc.x = rand.nextInt(nCollumnCell);
            pLoc.y = rand.nextInt(nRowCell);
            while ((map.get(pLoc.y, pLoc.x).getCategory() != Cell.Category.COOP) || //bukan di grassland
                map.get(pLoc.y, pLoc.x).getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = rand.nextInt(nCollumnCell);
                pLoc.y = rand.nextInt(nRowCell);
            }
            animalList.add(new Chicken(pLoc, map, nRowCell, nCollumnCell));
            map.get(pLoc.y, pLoc.x).setIsOcupied(true);
        }
    
        //Cow
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc = new Point();
            pLoc.x = rand.nextInt(nCollumnCell);
            pLoc.y = rand.nextInt(nRowCell);
            while ((map.get(pLoc.y, pLoc.x).getCategory() != Cell.Category.BARN) || //bukan di grassland
                map.get(pLoc.y, pLoc.x).getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = rand.nextInt(nCollumnCell);
                pLoc.y = rand.nextInt(nRowCell);
            }
            animalList.add(new Cow(pLoc, map, nRowCell, nCollumnCell));
            map.get(pLoc.y, pLoc.x).setIsOcupied(true);
        }
        
        //Duck
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc = new Point();
            pLoc.x = rand.nextInt(nCollumnCell);
            pLoc.y = rand.nextInt(nRowCell);
            while ((map.get(pLoc.y, pLoc.x).getCategory() != Cell.Category.BARN) || //bukan di grassland
                map.get(pLoc.y, pLoc.x).getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = rand.nextInt(nCollumnCell);
                pLoc.y = rand.nextInt(nRowCell);
            }
            animalList.add(new Duck(pLoc, map, nRowCell, nCollumnCell));
            map.get(pLoc.y, pLoc.x).setIsOcupied(true);
        }
        
        //Owl
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc = new Point();
            pLoc.x = rand.nextInt(nCollumnCell);
            pLoc.y = rand.nextInt(nRowCell);
            while ((map.get(pLoc.y, pLoc.x).getCategory() != Cell.Category.COOP) || //bukan di grassland
                map.get(pLoc.y, pLoc.x).getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = rand.nextInt(nCollumnCell);
                pLoc.y = rand.nextInt(nRowCell);
            }
            animalList.add(new Owl(pLoc, map, nRowCell, nCollumnCell));
            map.get(pLoc.y, pLoc.x).setIsOcupied(true);
        }
        
        //Pig
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc = new Point();
            pLoc.x = rand.nextInt(nCollumnCell);
            pLoc.y = rand.nextInt(nRowCell);
            while ((map.get(pLoc.y, pLoc.x).getCategory() != Cell.Category.BARN) || //bukan di grassland
                map.get(pLoc.y, pLoc.x).getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = rand.nextInt(nCollumnCell);
                pLoc.y = rand.nextInt(nRowCell);
            }
            animalList.add(new Pig(pLoc, map, nRowCell, nCollumnCell));
            map.get(pLoc.y, pLoc.x).setIsOcupied(true);
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