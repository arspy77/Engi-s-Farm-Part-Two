package engisfarm;

import engisfarm.cell.Cell;
import engisfarm.farmanimal.FarmAnimal;
import engisfarm.product.Product;
import engisfarm.product.SideProduct;
import engisfarm.product.BeefChickenOmelette;
import engisfarm.product.BeefHaramSate;
import engisfarm.product.SuperSecretSpecialProduct;
import java.util.*;
import java.awt.Image;
import javax.swing.ImageIcon;

/** Kelas yang merepresentasikan Pemain dan semua aksi yang dapat dia lakukan */
public class Player extends LivingThing {
    /** Constructor Player di position, recipeBook diinisalisasi dengan semua SideProduct yang terdefinisi */
    public Player(Point position, Matrix<Cell> worldMap, int nRowCell, int nCollumnCell) {
        super(position, worldMap, nRowCell, nCollumnCell);
        money = 0;
        water = 5;
        recipeBook = new LinkedList<SideProduct>();
        recipeBook.add(new BeefChickenOmelette());
        recipeBook.add(new BeefHaramSate());
        recipeBook.add(new SuperSecretSpecialProduct());
        inventory = new LinkedList<Product>();
    }

    /** Player berbicara dengan semua FarmAnimal terdekat. */
    public void talk(LinkedList<FarmAnimal> farmAnimal, LinkedList<String> mesQueue) {
        for(int i = 0; i<farmAnimal.size(); i++) {
            if(adjPosition(getPosition(), farmAnimal.get(i).getPosition())) {
                mesQueue.add(farmAnimal.get(i).makeNoise());
            }
	    }
    }

    /**
     * Player mengambil FarmProduct dari semua FarmAnimal terdekat tanpa membunuh FarmAnimal tersebut.
     * Bekerja untuk FarmAnimal jenis MilkProducing dan EggProducing.
     * Contoh FarmProduct : ChickenEgg, CowMilk.
     */
    public void interact(LinkedList<FarmAnimal> farmAnimal) throws GeneralException {
        boolean haveInteract = false;
        for(int i = 0; i < farmAnimal.size(); i++) {
            if(adjPosition(getPosition(), farmAnimal.get(i).getPosition())) {
                if(farmAnimal.get(i).getProduce()) {
                    inventory.add(farmAnimal.get(i).produceProduct(FarmAnimal.Action.INTERACT));
                    haveInteract = true;
                }
            }
        }
        if (haveInteract) {
            throw new GeneralException("Wonderfull! You have done something great! ");
        }
    }

    /**
     * Player mengambil FarmProduct dari semua FarmAnimal terdekat dengan cara membunuh FarmAnimal tersebut.
     * Bekerja untuk FarmAnimal jenis MeatProducing.
     * Contoh FarmProduct : CowMeat, ChickenMeat.
     */
    public void kill(LinkedList<FarmAnimal> farmAnimal) throws GeneralException {
        boolean haveKill = false;
        for(int i = farmAnimal.size() - 1; i >= 0; i--) {
            if(adjPosition(getPosition(), farmAnimal.get(i).getPosition())) {
                if(farmAnimal.get(i).getKillable()) {
                    inventory.add(farmAnimal.get(i).produceProduct(FarmAnimal.Action.KILL));
                    worldMap.get(farmAnimal.get(i).getPosition().y, farmAnimal.get(i).getPosition().x).setIsOcupied(false);
                    farmAnimal.remove(i);
                    haveKill = true;
                }
            }
        }
        if (haveKill) {
            throw new GeneralException("What have you done! You have killed kawaii beings!");
        }
    }

    /** Menumbuhkan rumput pada cell yang sedang ditempati oleh Player */
    public void grow() throws GeneralException {
        if (this.water > 0) {	
            worldMap.get(this.getPosition().y,this.getPosition().x).growGrass();
            this.water -= 1;
            throw new GeneralException("The land beneath you has sprout a new life!"); 
        } else {
            throw new GeneralException("You have no water!"); 
        }
    }

    /** Menciptakan SideProduct dari FarmProduct bila Player dekat dengan mixer */
    public void mix(Scanner in) throws GeneralException {
        int choice = -1;
        if (((getPosition().y + 1 >= 0 && getPosition().y + 1 < nRowCell && getPosition().x >= 0 && getPosition().x < nCollumnCell) && worldMap.get(getPosition().y + 1,getPosition().x).getCategory() == Cell.Category.MIXER) ||
            ((getPosition().y >= 0 && getPosition().y < nRowCell && getPosition().x + 1 >= 0 && getPosition().x + 1 < nCollumnCell) && worldMap.get(getPosition().y,getPosition().x + 1).getCategory() == Cell.Category.MIXER) ||
            ((getPosition().y - 1 >= 0 && getPosition().y - 1 < nRowCell && getPosition().x >= 0 && getPosition().x < nCollumnCell) && worldMap.get(getPosition().y - 1,getPosition().x).getCategory() == Cell.Category.MIXER) ||
            ((getPosition().y >= 0 && getPosition().y < nRowCell && getPosition().x - 1 >= 0 && getPosition().x - 1 < nCollumnCell) && worldMap.get(getPosition().y,getPosition().x - 1).getCategory() == Cell.Category.MIXER)) {
            System.out.println("Which recipe are you going to make?");
            choice = Integer.parseInt(in.nextLine());
            if (choice > 0 && choice <= recipeBook.size()) { //dari 1 sampai 3
                boolean allExist = true;
                for (int i = 0; i < recipeBook.get(choice - 1).getRecipe().size() && allExist; i++) {
                    if (inventory.indexOf(recipeBook.get(choice - 1).getRecipe().get(i)) == -1) { //gak ketemu
                        allExist = false;
                    }
                }
                if (allExist) {
                    for (int i = 0; i < recipeBook.get(choice - 1).getRecipe().size(); i++) { //delete semua resep yg ada di dalem inventory
                        int delIdx = inventory.indexOf(recipeBook.get(choice - 1).getRecipe().get(i));
                        inventory.remove(delIdx);
                    }
                    if (recipeBook.get(choice - 1).getCategory() == Product.Category.BEEFCHICKENOMELETTE) {
                        inventory.add(new BeefChickenOmelette());
                    } else if (recipeBook.get(choice - 1).getCategory() == Product.Category.BEEFHARAMSATE) {
                        inventory.add(new BeefHaramSate());
                    } else if (recipeBook.get(choice - 1).getCategory() == Product.Category.SUPERSECRETSPECIALPRODUCT) {
                        inventory.add(new SuperSecretSpecialProduct());
                    }
                    throw new GeneralException("What a great leap for humanity! You have made something!");
                } else {
                    throw new GeneralException("You don't have the required ingredients!");
                }
            }
        }
    }

    /** Mengembalikan Image untuk dirender ke layar */
    public Image render() {
        ImageIcon icon = new ImageIcon("../resources/player.png");
        return icon.getImage();
    }

    /** Mengambil air dari well */ //Awalnya tidak ada
    public void takeWater() throws GeneralException {
        if (((getPosition().y + 1 >= 0 && getPosition().y + 1 < nRowCell && getPosition().x >= 0 && getPosition().x < nCollumnCell) && worldMap.get(getPosition().y + 1,getPosition().x).getCategory() == Cell.Category.WELL) ||
            ((getPosition().y >= 0 && getPosition().y < nRowCell && getPosition().x + 1 >= 0 && getPosition().x + 1 < nCollumnCell) && worldMap.get(getPosition().y,getPosition().x + 1).getCategory() == Cell.Category.WELL) ||
            ((getPosition().y - 1 >= 0 && getPosition().y - 1 < nRowCell && getPosition().x >= 0 && getPosition().x < nCollumnCell) && worldMap.get(getPosition().y - 1,getPosition().x).getCategory() == Cell.Category.WELL) ||
            ((getPosition().y >= 0 && getPosition().y < nRowCell && getPosition().x - 1 >= 0 && getPosition().x - 1 < nCollumnCell) && worldMap.get(getPosition().y,getPosition().x - 1).getCategory() == Cell.Category.WELL)) {
            this.water = 20;
            throw new GeneralException("You take clear water from the well before you");
        }
    }

    /** Menjual semua product di inventory */ //Awalnya tidak ada
    public void sellAll() throws GeneralException {
        int sum = 0;
        if (((getPosition().y + 1 >= 0 && getPosition().y + 1 < nRowCell && getPosition().x >= 0 && getPosition().x < nCollumnCell) && worldMap.get(getPosition().y + 1,getPosition().x).getCategory() == Cell.Category.TRUCK) ||
	        ((getPosition().y >= 0 && getPosition().y < nRowCell && getPosition().x + 1 >= 0 && getPosition().x + 1 < nCollumnCell) && worldMap.get(getPosition().y,getPosition().x + 1).getCategory() == Cell.Category.TRUCK) ||
	        ((getPosition().y - 1 >= 0 && getPosition().y - 1 < nRowCell && getPosition().x >= 0 && getPosition().x < nCollumnCell) && worldMap.get(getPosition().y - 1,getPosition().x).getCategory() == Cell.Category.TRUCK) ||
	        ((getPosition().y >= 0 && getPosition().y < nRowCell && getPosition().x - 1 >= 0 && getPosition().x - 1 < nCollumnCell) && worldMap.get(getPosition().y,getPosition().x - 1).getCategory() == Cell.Category.TRUCK)) {
            while(!inventory.isEmpty()){
                this.money += inventory.get(0).getPrice();
                sum += inventory.get(0).getPrice();
                inventory.remove(0);
            }
            throw new GeneralException("Oh no! Your inventory is gone. But you just got Rp" + Integer.toString(sum));
        }
    }

    /** Getter banyak uang yang dimiliki Player */ //Awalnya tidak ada
    public int getMoney() {
        return money;
    }

    /** Getter banyak air yang dimiliki Player */
    public int getWater() {
        return water;
    }

    /** Getter inventory yang dipegang Player */
    public LinkedList<Product> getInventory() {
        return inventory;
    }

    /** Getter daftar resep yang dimiliki Player */
    public LinkedList<SideProduct> getrecipeBook() {
        return recipeBook;
    }

    //=====================Atribute==========================

    /** Product yang dipegang Player */
    private LinkedList<Product> inventory;

    /** Uang yang dimiliki Player */
    private int money;

    /** Air yang dipegang Player */
    private int water; 

    /** 
        * Digunakan untuk melakukan pengecekan saat melakukan method mix 
        * Contoh Pengunaan : 
        * Bila player ingin membuat BeefHaramSate, program tranversal di recipeeBook sampai
        * menemukan sideProdect dengan Category = BEEFHARAMSATE lalu melihat resep dari objek
        * tersebut.
        * recipeBook diinisalisasi di implementasi
        */
    private LinkedList<SideProduct> recipeBook;
    
    /** Apakah bisa masuk suatu area (cek out of bound, jenis Cell, kekosongan Cell) */
    protected boolean canMoveTo(Cell toWhere) {
        return !toWhere.getIsOcupied();
    }

    /** Mengembalikan true jika kedua Point bersebelahan */
    boolean adjPosition(Point P1, Point P2) {
        if(P1.x+1 == P2.x && P1.y == P2.y) {
            return true;
        }
        if(P1.x-1 == P2.x && P1.y == P2.y) {
            return true;
        }
        if(P1.x == P2.x && P1.y+1 == P2.y) {
            return true;
        }
        if(P1.x == P2.x && P1.y-1 == P2.y) {
            return true;
        }
        return false;
    }
};