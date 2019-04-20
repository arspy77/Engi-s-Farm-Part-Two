package engisfarm;

import engisfarm.cell.*;
import engisfarm.farmanimal.Cow;
import engisfarm.farmanimal.FarmAnimal;
import engisfarm.farmanimal.Horse;
import engisfarm.product.ChickenEgg;
import engisfarm.product.CowMeat;
import engisfarm.product.CowMeatTest;
import engisfarm.product.Product;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void ctor() {
        Point pos = new Point();
        pos.x = 5;
        pos.y = 11;
        Matrix<Cell> map = new Matrix<Cell>(20, 22, new GrassLand());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 22; j++) {
                map.set(i, j, new GrassLand());
            }
        }
        Player pl = new Player(pos, map, 20, 22);

        assertEquals(5, pl.getPosition().x);
        assertEquals(11, pl.getPosition().y);
        assertEquals(5, pl.getWater());
        assertEquals(0, pl.getMoney());
        assertTrue(pl.getInventory().isEmpty());
    }

    @Test
    public void Talk() {
        Point pos = new Point();
        pos.x = 5;
        pos.y = 11;
        Matrix<Cell> map = new Matrix<Cell>(20, 22, new GrassLand());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 22; j++) {
                map.set(i, j, new GrassLand());
            }
        }
        Player pl = new Player(pos, map, 20, 22);

        LinkedList<String> mesQueue = new LinkedList<String>();
        LinkedList<FarmAnimal> farmAnimal = new LinkedList<FarmAnimal>();

        Point posh = new Point();
        posh.x = 6;
        posh.y = 11;

        FarmAnimal hrse = new Horse(posh, map, 20, 22);
        farmAnimal.add(hrse);
        pl.talk(farmAnimal, mesQueue);
        assertEquals(hrse.makeNoise(), mesQueue.get(0));
    }

    @Test
    public void interact() {
        Point pos = new Point();
        pos.x = 5;
        pos.y = 11;
        Matrix<Cell> map = new Matrix<Cell>(20, 22, new GrassLand());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 22; j++) {
                map.set(i, j, new GrassLand());
            }
        }
        Player pl = new Player(pos, map, 20, 22);

        LinkedList<FarmAnimal> farmAnimal = new LinkedList<FarmAnimal>();

        Point posh = new Point();
        posh.x = 6;
        posh.y = 11;

        FarmAnimal hrse = new Horse(posh, map, 20, 22);
        farmAnimal.add(hrse);
        map.get(posh.y, posh.x).growGrass();
        hrse.tick();
        assertTrue(hrse.getProduce());
        try {
            pl.interact(farmAnimal);
        } catch(GeneralException e) {
            //pass
        }
        assertEquals(Product.Category.HORSEMILK, pl.getInventory().get(0).getCategory());
    }

    @Test
    public void kill() {
        Point pos = new Point();
        pos.x = 5;
        pos.y = 11;
        Matrix<Cell> map = new Matrix<Cell>(20, 22, new Barn());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 22; j++) {
                map.set(i, j, new Barn());
            }
        }
        Player pl = new Player(pos, map, 20, 22);

        LinkedList<FarmAnimal> farmAnimal = new LinkedList<FarmAnimal>();

        Point posh = new Point();
        posh.x = 6;
        posh.y = 11;

        FarmAnimal hrse = new Cow(posh, map, 20, 22);
        farmAnimal.add(hrse);
        assertFalse(hrse.getProduce());
        try {
            pl.kill(farmAnimal);
        } catch(GeneralException e) {
            //pass
        }
        assertEquals(Product.Category.COWMEAT, pl.getInventory().get(0).getCategory());
    }

    @Test
    public void grow() {
        Point pos = new Point();
        pos.x = 5;
        pos.y = 11;
        Matrix<Cell> map = new Matrix<Cell>(20, 22, new Barn());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 22; j++) {
                map.set(i, j, new Barn());
            }
        }
        Player pl = new Player(pos, map, 20, 22);

        assertFalse(map.get(11, 5).isGrassExist());
        try {
            pl.grow();
        } catch (GeneralException ge) {

        }
        assertTrue(map.get(11, 5).isGrassExist());

    }

    @Test
    public void takeWater() {
        Point pos = new Point();
        pos.x = 5;
        pos.y = 11;
        Matrix<Cell> map = new Matrix<Cell>(20, 22, new Barn());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 22; j++) {
                map.set(i, j, new Barn());
            }
        }

        Player pl = new Player(pos, map, 20, 22);

        map.set(11, 6, new Well());

        assertEquals(5, pl.getWater());
        try {
            pl.takeWater();
        } catch (GeneralException e) {

        }
        assertEquals(20 , pl.getWater());
        try {
            pl.grow();
        } catch (GeneralException e) {

        }
        assertEquals(19 , pl.getWater());

    }

    @Test
    public void sellAll() {
        Point pos = new Point();
        pos.x = 5;
        pos.y = 11;
        Matrix<Cell> map = new Matrix<Cell>(20, 22, new Barn());
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 22; j++) {
                map.set(i, j, new Barn());
            }
        }

        Player pl = new Player(pos, map, 20, 22);

        Product prod1 = new ChickenEgg();
        Product prod2 = new CowMeat();

        pl.getInventory().add(prod1);
        pl.getInventory().add(prod2);

        assertEquals(2, pl.getInventory().size());
        assertEquals(0, pl.getMoney());
        map.set(11, 6, new Truck());
        try {
            pl.sellAll();
        } catch (GeneralException g) {

        }
        assertEquals(prod1.getPrice() + prod2.getPrice(), pl.getMoney());
    }
}