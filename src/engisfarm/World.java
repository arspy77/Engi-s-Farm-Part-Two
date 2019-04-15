package engisfarm;

import java.util.Scanner;

/** World adalah kelas yang merepresentasikan dunia yang menyimpan semua Cell dan LivingThing di dalamnya */
public class World{
    private void drawTrueSpaces(int n) {
        while(n > 0) {
            printf(" ");
            n--;
        }
    }
    
    private void drawFrame(int n) {
        while(n > 0) {
            printf("#");
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
    public World(){
        System.out.println( "NRow NCol? ");
        Scanner in = new Scanner(System.in);
        
        String[] input = in.nextLine().split(" ");
        nRowCell = input[0];
        nCollumnCell = input[1];
        map = new Cell[nRowCell][];
        for (int i = 0; i < nRowCell; i++) {
            map[i] = new Cell[nCollumnCell];
        }
        for (int i = 0; i < nRowCell; i++) {
            for (int j = 0; j < nCollumnCell; j++) {
                map[i][j] = new GrassLand();
            }
        }
        for (int i = 0; i < (nRowCell*6/10); i++) {
            for (int j = 0; j < (nCollumnCell*4/11) ; j++) {
                map[i][j] = new Coop();
            }
        }
        for (int i = 0; i < (nRowCell*6/10); i++) {
            for (int j = (nRowCell*5/11); j < (nCollumnCell*8/11); j++) {
                map[i][j] = new Barn();
            }
        }
        map[0][nCollumnCell - 1] = new Truck();
        map[1][nCollumnCell - 1] = new Mixer();
        map[3][nCollumnCell - 1] = new Well();
    
        Point pLoc;
        pLoc.x = nCollumnCell / 2;
        pLoc.y = nRowCell / 2;
        pl = new Player(pLoc, map, nRowCell, nCollumnCell);
        map[pl.getPosition().y][pl.getPosition().x].setIsOcupied(true);
    
        // srand(time(nullptr));
        nAnimal = 0;
        //Horse
        for (int i = 0; i < (nRowCell+nCollumnCell)*15/40; i++) {
            pLoc.x = (int) Math.random() * nCollumnCell;
            pLoc.y = (int) Math.random() * nRowCell;
            while ((map[pLoc.y][pLoc.x].getCategory() != Cell::Category::GRASSLAND) || //bukan di grassland
                map[pLoc.y][pLoc.x].getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = (int) Math.random() * nCollumnCell;
                pLoc.y = (int) Math.random() * nRowCell;
            }
            animalList.add(new Horse(pLoc, map, nRowCell, nCollumnCell));
            nAnimal++;
            map[pLoc.y][pLoc.x].setIsOcupied(true);
        }
    
        //Chicken
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc.x = (int) Math.random() * nCollumnCell;
            pLoc.y = (int) Math.random() * nRowCell;
            while ((map[pLoc.y][pLoc.x].getCategory() != Cell::Category::COOP) || //bukan di grassland
                map[pLoc.y][pLoc.x].getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = (int) Math.random() * nCollumnCell;
                pLoc.y = (int) Math.random() * nRowCell;
            }
            animalList.add(new Chicken(pLoc, map, nRowCell, nCollumnCell));
            nAnimal++;
            map[pLoc.y][pLoc.x].setIsOcupied(true);
        }
    
        //Cow
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc.x = (int) Math.random() * nCollumnCell;
            pLoc.y = (int) Math.random() * nRowCell;
            while ((map[pLoc.y][pLoc.x].getCategory() != Cell::Category::BARN) || //bukan di grassland
                map[pLoc.y][pLoc.x].getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = (int) Math.random() * nCollumnCell;
                pLoc.y = (int) Math.random() * nRowCell;
            }
            animalList.add(new Cow(pLoc, map, nRowCell, nCollumnCell));
            nAnimal++;
            map[pLoc.y][pLoc.x].setIsOcupied(true);
        }
    
        //Duck
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc.x = (int) Math.random() * nCollumnCell;
            pLoc.y = (int) Math.random() * nRowCell;
            while ((map[pLoc.y][pLoc.x].getCategory() != Cell::Category::BARN) || //bukan di grassland
                map[pLoc.y][pLoc.x].getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = (int) Math.random() * nCollumnCell;
                pLoc.y = (int) Math.random() * nRowCell;
            }
            animalList.add(new Duck(pLoc, map, nRowCell, nCollumnCell));
            nAnimal++;
            map[pLoc.y][pLoc.x].setIsOcupied(true);
        }
    
        //Ostrich
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc.x = (int) Math.random() * nCollumnCell;
            pLoc.y = (int) Math.random() * nRowCell;
            while ((map[pLoc.y][pLoc.x].getCategory() != Cell::Category::COOP) || //bukan di grassland
                map[pLoc.y][pLoc.x].getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = (int) Math.random() * nCollumnCell;
                pLoc.y = (int) Math.random() * nRowCell;
            }
            animalList.add(new Ostrich(pLoc, map, nRowCell, nCollumnCell));
            nAnimal++;
            map[pLoc.y][pLoc.x].setIsOcupied(true);
        }
    
        //Sheep
        for (int i = 0; i < (nRowCell+nCollumnCell)*5/40; i++) {
            pLoc.x = (int) Math.random() * nCollumnCell;
            pLoc.y = (int) Math.random() * nRowCell;
            while ((map[pLoc.y][pLoc.x].getCategory() != Cell::Category::BARN) || //bukan di grassland
                map[pLoc.y][pLoc.x].getIsOcupied()) { //ocupied, true kalo ocupied
                pLoc.x = (int) Math.random() * nCollumnCell;
                pLoc.y = (int) Math.random() * nRowCell;
            }
            animalList.add(new Sheep(pLoc, map, nRowCell, nCollumnCell));
            nAnimal++;
            map[pLoc.y][pLoc.x].setIsOcupied(true);
        }
    }

    /**
    * Membaca input user dari stdin lalu melakukan aksi sesuai degan spesifikasi,
    * misal, input == MOVELEFT, maka akan dipanggil pl.move(LEFT). 
    * Bila input == INTERACT, maka akan dipanggil pl.interact(animalList), dsb.
    */
    public void Input(){
        Scanner in = new Scanner(System.in);
        String inp = in.nextLine().toLowerCase();
        if (inp == "w") {
            pl.move(Direction::UP);		
        } else if (inp == "s") {
            pl.move(Direction::DOWN);		
        } else if (inp == "a") {
            pl.move(Direction::LEFT);
        } else if (inp == "d") {
            pl.move(Direction::RIGHT);
        } else if (inp == "talk") {
            pl.talk(animalList, mesQueue);
        } else if (inp == "grow") {
            pl.grow(mesQueue);
        } else if (inp == "kill") {
            pl.kill(animalList,nAnimal);
        } else if (inp == "interact") {
            pl.interact(animalList);
            pl.takeWater();
            pl.sellAll();
        } else if (inp == "mix") {
            pl.mix(mesQueue);
        } else if (inp == "exit") {
            throw "EXIT";
        } else if(inp == "help"){
            mesQueue.add("a,w,s,d	Move");
            mesQueue.add("interact	Interaksi dengan hewan atau benda");
            mesQueue.add("mix		Membuat side product pada mixer");
            mesQueue.add("talk		Berbicara dengan hewan");
            mesQueue.add("kill		Menyembelih hewan ternak");
            mesQueue.add("grow		Menumbuhkan rumput pada cell");
            mesQueue.add("exit		Keluar dari game");
        }
    }


    /**
    * Pada World::Update(), setiap fungsi yang dipanggil secara berkala seperti FarmAnimal::tick()
    * akan dipanggil.
    */
    public void Update(){
        int x, y;
        for (int i = 0; i < (int) Math.random() * (nRowCell+nCollumnCell)*20/40; i++) {
            x = (int) Math.random() * nCollumnCell;
            y = (int) Math.random() * nRowCell;
            map[y][x].growGrass();
        }
    
        for (int i = 0; i < nAnimal; i++) {
            animalList[i].tick();
            if (animalList[i].isDead()) {
                map[animalList[i].getPosition().y][animalList[i].getPosition().x].setIsOcupied(false);
                animalList.removeIdx(i);
                nAnimal--;
            }
        }
    
        mesQueue.add("========RECIPE=======");
        for (int i = 0; i < pl.getrecipeBook().len(); i++) {
            String sideProd;
            if (pl.getrecipeBook()[i].getCategory() == Product::Category::BEEFCHICKENOMELETTE) {
                    sideProd =  (i+1) + ".  *Beef Chicken Omelette*";
            } else if (pl.getrecipeBook()[i].getCategory() == Product::Category::BEEFMUTONSATE) {
                sideProd = ".  *Beef Muton Sate*";
            } else if (pl.getrecipeBook()[i].getCategory() == Product::Category::SUPERSECRETSPECIALPRODUCT) {
                sideProd = ".  *Super Secret Special Product*";
            }
            mesQueue.add((i+1) + sideProd);
            for (int j = 0; j < pl.getrecipeBook()[i].getRecipe().len(); j++){
                String prod;
                if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::CHICKENEGG) {
                    prod = "      -Chicken Egg";
                } else if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::COWMEAT) {
                    prod = "       -Cow Meat";
                } else if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::BEEFCHICKENOMELETTE) {
                    prod = "       -Beef Chicken Omelette";
                } else if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::BEEFMUTONSATE) {
                    prod = "       -Beef Muton Sate";
                } else if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::DUCKMEAT) {
                    prod = "       -Duck Meat";
                } else if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::HORSEMILK) {
                    prod = "       -Horse Milk";
                } else if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::OSTRICHEGG) {
                    prod = "       -Ostrich Egg";
                } else if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::SHEEPMEAT) {
                    prod = "       -Sheep Meat";
                } else if (pl.getrecipeBook()[i].getRecipe()[j].getCategory() == Product::Category::SUPERSECRETSPECIALPRODUCT) {
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
    * Megambarkar representasi state program (World) seperti lokasi setiap objek, money, water, 
    * dan Inventory Player, dsb ke layar.
    */
    public void Draw(){
        char localMap[][] = new char[nRowCell][nCollumnCell];
        for (int i = 0; i < nRowCell; i++) {
            for (int j = 0; j < nCollumnCell; j++) {
                if (map[i][j].getCategory() == Cell::Category::GRASSLAND) {
                    if (map[i][j].isGrassExist()) {
                        localMap[i][j] = '#';
                    } else {
                        localMap[i][j] = '-';
                    }
                } else if(map[i][j].getCategory() == Cell::Category::COOP) {
                    if (map[i][j].isGrassExist()) {
                        localMap[i][j] = '*';
                    } else {
                        localMap[i][j] = 'o';
                    }
                } else if(map[i][j].getCategory() == Cell::Category::BARN) {
                    if (map[i][j].isGrassExist()) {
                        localMap[i][j] = '@';
                    } else {
                        localMap[i][j] = 'x';
                    }
                } else if(map[i][j].getCategory() == Cell::Category::TRUCK) {
                    localMap[i][j] = 'T';
                } else if(map[i][j].getCategory() == Cell::Category::MIXER) {
                    localMap[i][j] = 'M';
                } else if(map[i][j].getCategory() == Cell::Category::WELL) {
                    localMap[i][j] = 'W';
                } else {
                    localMap[i][j] = '#';
                }
            }
        }
    
        localMap[pl.getPosition().y][pl.getPosition().x] = 'P';
        for (int i = 0; i < nAnimal; i++){
            localMap[animalList[i].getPosition().y][animalList[i].getPosition().x] = animalList[i].render();
        }
    
        int InventoryTabLength = 30;
        drawFrame(1 + nCollumnCell*2 + InventoryTabLength + 1 + 4); printf("\n");
        printf("#"); drawTrueSpaces(nCollumnCell*2 + 1 + 2); printf("#");
        printf(" Inventory");drawTrueSpaces(InventoryTabLength - strlen(" Inventory"));
        printf("#\n");
        for (int i = 0; i < nRowCell; i++) {
            printf("#  ");
            for (int j = 0; j < nCollumnCell; j++) {
                printf("%c", localMap[i][j]);
                if (j != nCollumnCell - 1) {
                    printf("|");
                }
            }
            printf("  #");
            if (i >= 0 && i < nRowCell - 2) {
                char invObj[] = new char[30];
                strcpy(invObj, "");
                if ((i) < pl.getInventory().len()) {
                    if (pl.getInventory()[i].getCategory() == Product::Category::CHICKENEGG) {
                        strcpy(invObj, " Chicken Egg");
                    } else if (pl.getInventory()[i].getCategory() == Product::Category::COWMEAT) {
                        strcpy(invObj, " Cow Meat");
                    } else if (pl.getInventory()[i].getCategory() == Product::Category::BEEFCHICKENOMELETTE) {
                        strcpy(invObj, " Beef Chicken Omelette");
                    } else if (pl.getInventory()[i].getCategory() == Product::Category::BEEFMUTONSATE) {
                        strcpy(invObj, " Beef Muton Sate");
                    } else if (pl.getInventory()[i].getCategory() == Product::Category::DUCKMEAT) {
                        strcpy(invObj, " Duck Meat");
                    } else if (pl.getInventory()[i].getCategory() == Product::Category::HORSEMILK) {
                        strcpy(invObj, " Horse Milk");
                    } else if (pl.getInventory()[i].getCategory() == Product::Category::OSTRICHEGG) {
                        strcpy(invObj, " Ostrich Egg");
                    } else if (pl.getInventory()[i].getCategory() == Product::Category::SHEEPMEAT) {
                        strcpy(invObj, " Sheep Meat");
                    } else if (pl.getInventory()[i].getCategory() == Product::Category::SUPERSECRETSPECIALPRODUCT) {
                        strcpy(invObj, " Super Secret Special Product");
                    }
                    
                }
                printf("%s", invObj);drawTrueSpaces(InventoryTabLength - strlen(invObj));
            } else if (i == nRowCell - 2) {
                drawFrame(InventoryTabLength);
            } else if (i == nRowCell - 1) {
                printf(" Money : %d", pl.getMoney());drawTrueSpaces(InventoryTabLength - strlen(" Money : ") - intLen(pl.getMoney()));
            }
    
            printf("# ");
            if (!mesQueue.isEmpty()) {
                System.out.println( mesQueue[0]);
                mesQueue.removeIdx(0);
            }
            printf("\n");
        }
        printf("#"); drawTrueSpaces(nCollumnCell*2 + 1 + 2); printf("#");
        printf(" Water : %d", pl.getWater());drawTrueSpaces(InventoryTabLength - strlen(" Money : ") - intLen(pl.getWater()));	
        printf("#\n");
        drawFrame(1 + nCollumnCell*2 + InventoryTabLength + 1 + 4); printf("\n");
    }

    /** Player yang berada pada World */
    private Player pl;

    /** Matriks dari pointer ke seluruh Cell pada World */
    private Cell[][] map;

    /** Nilai efektif baris untuk Matriks Cell */
    private int nRowCell;
    
    /** Nilai efektif kolom untuk Matriks Cell */
    private int nCollumnCell;

    /** Banyaknya Animal yang hidup */
    private int nAnimal;

    /** LinkedList dari seluruh pointer ke FarmAnimal yang berada pada World 000*/
    private LinkedList<FarmAnimal> animalList;

    /** 
    * Antrian pesan yang akan ditampilkan saat render 
    */
    private LinkedList<String> mesQueue;
}