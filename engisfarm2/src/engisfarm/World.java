package engisfarm;

import engisfarm.cell.*;
import engisfarm.farmanimal.*;
import engisfarm.product.*;
import java.util.LinkedList;
import java.util.Random;

/** World adalah kelas yang merepresentasikan dunia yang menyimpan semua Cell dan LivingThing di dalamnya */
public class World {


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

    /** Memasukkan resep kedalam message queue untuk ditampilkan pada saat draw */
    public void displayRecipe() {
        mesQueue.add("========RECIPE=======");
        for (int i = 0; i < pl.getrecipeBook().size(); i++) {
            String sideProd = "";
            if (pl.getrecipeBook().get(i).getCategory() == Product.Category.BEEFCHICKENOMELETTE) {
                sideProd = ".  *Beef Chicken Omelette*";
            } else if (pl.getrecipeBook().get(i).getCategory() == Product.Category.BEEFHARAMSATE) {
                sideProd = ".  *Beef Haram Sate*";
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
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.BEEFHARAMSATE) {
                    prod = "       -Beef Haram Sate";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.DUCKMEAT) {
                    prod = "       -Duck Meat";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.HORSEMILK) {
                    prod = "       -Horse Milk";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.OWLEGG) {
                    prod = "       -Owl Egg";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.PIGMEAT) {
                    prod = "       -Pig Meat";
                } else if (pl.getrecipeBook().get(i).getRecipe().get(j).getCategory() == Product.Category.SUPERSECRETSPECIALPRODUCT) {
                    prod = "       -Super Secret Special Product";
                }
                mesQueue.add(prod);
            }
        }
    }

    /**
     * Pada World::Update(), setiap fungsi yang dipanggil secara berkala seperti FarmAnimal::tick()
     * akan dipanggil.
     */
    public void update(){
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

        displayRecipe();
    }

    /** Getter */
    public int getNRow(){
        return nRowCell;
    }

    /** Getter */
    public int getNCol(){
        return nCollumnCell;
    }

    /** Getter */
    public Player getPlayer() {
        return pl;
    }

    /** Getter */
    public Matrix<Cell> getMap() {
        return map;
    }

    /** Getter */
    public LinkedList<FarmAnimal> getAnimalList() {
        return animalList;
    }

    /** Getter */
    public LinkedList<String> getMesQueue() {
        return mesQueue;
    }

    /** Object Random agar setiap playthrough berbeda */
    private Random rand = new Random();

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