package engisfarm.farmanimal;

import engisfarm.Matrix;
import engisfarm.Point;
import engisfarm.cell.Cell;
import engisfarm.cell.Coop;
import engisfarm.product.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class HorseTest {
    @Test
    public void ctor() {
        Point pos = new Point();
        pos.x = 1;
        pos.y = 3;
        Matrix<Cell> map = new Matrix<Cell>(10, 11, new Coop());
        FarmAnimal anima = new Horse(pos, map, 10, 11);
        assertEquals(1, anima.getPosition().x);
        assertEquals(3, anima.getPosition().y);
    }

    @Test
    public void produceProduct() {
        Point pos = new Point();
        pos.x = 1;
        pos.y = 3;
        Matrix<Cell> map = new Matrix<Cell>(10, 11, new Coop());
        FarmAnimal anima = new Horse(pos, map, 10, 11);
        map.get(3, 1).growGrass();
        anima.tick();
        assertEquals(Product.Category.HORSEMILK, anima.produceProduct(FarmAnimal.Action.INTERACT).getCategory());
    }

    @Test
    public void makeNoise() {
        Point pos = new Point();
        pos.x = 1;
        pos.y = 3;
        Matrix<Cell> map = new Matrix<Cell>(10, 11, new Coop());
        FarmAnimal anima = new Horse(pos, map, 10, 11);
        assertEquals("hihiin~", anima.makeNoise());
    }

    @Test
    public void isDead() {
        Point pos = new Point();
        pos.x = 1;
        pos.y = 3;
        Matrix<Cell> map = new Matrix<Cell>(10, 11, new Coop());
        FarmAnimal anima = new Horse(pos, map, 10, 11);
        assertFalse(anima.isDead());
        for (int i = 0; i < anima.maxTimeToGetHungry + 5; i++) {
            anima.tick();
        }
        assertTrue(anima.isDead());
    }
}