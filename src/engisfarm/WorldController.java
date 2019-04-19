package engisfarm;

import engisfarm.World;
import engisfarm.WorldView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/** World adalah kelas yang merepresentasikan dunia yang menyimpan semua Cell dan LivingThing di dalamnya */
public class WorldController implements Runnable {
    private World w;
    private WorldView wv;


    /** Menjalankan thread  */
    public void run() {
        try {
            while (true) {
                try {
                    Thread.sleep(1000);
                    w.update();
                    wv.repaint();
                } catch (Exception exp){
                    break;
                }
            }
        }catch(Exception e){

        }
    }

    public WorldView getWorldView() {
        return wv;
    }


    public WorldController(World w) {
        this.w = w;
        wv = new WorldView(w);
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

}