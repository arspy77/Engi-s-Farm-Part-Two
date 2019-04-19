package engisfarm;

import java.awt.EventQueue;
import javax.swing.JFrame;

/** Kelas yang menjalankan program utama */
@SuppressWarnings("serial")
public class Main extends JFrame{

    /** Fungsi program utama */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Main m = new Main();
            m.setVisible(true);
        });
    }

    /** Nilai x dari selisih frame dan panel background */
    private final int xOffset = 18;
    /** Nilai y dari selisih frame dan panel background */
    private final int yOffset = 47;

    /** Constructor Main */
    private Main(){
        World w = new World(20, 22);
        WorldController wc = new WorldController(w);
        WorldView wv = wc.getWorldView();
        add(wv);
        setTitle("Engi's Farm");
        Thread t1 = new Thread(wc,"T1");
        t1.start();
        setSize((1 + w.getNCol() + 1 + wv.InventoryTabLength/2 + 2 + wv.mesQueueLength/2 + 1) * wv.SPACE + xOffset, (w.getNRow() + 2) * wv.SPACE + yOffset);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}