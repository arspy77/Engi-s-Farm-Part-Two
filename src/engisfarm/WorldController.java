package engisfarm;

import engisfarm.World;
import engisfarm.WorldView;

/** World adalah kelas yang merepresentasikan dunia yang menyimpan semua Cell dan LivingThing di dalamnya */
public class WorldController implements Runnable {

    /** Menjalankan thread. Thread akan melakukan update dan repaint secara berkala setiap 1 detik*/
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
    /** getter dari instance WorldView */
    public WorldView getWorldView() {
        return wv;
    }

    /** Constructor dari WorldController*/
    public WorldController(World w) {
        this.w = w;
        wv = new WorldView(w);
    }

    /** World yang diurus WorldController */
    private World w;

    /** WorldView yang digunakan WorldController */
    private WorldView wv;
}