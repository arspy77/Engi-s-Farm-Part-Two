package engisfarm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.EventQueue;
import javax.swing.JFrame;


public class Main extends JFrame{
    //private static BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
    
    /*private static void titleScreen()  throws IOException, InterruptedException{
        log.write(" /$$$$$$$$                     /$$ /$$              /$$$$$$$$                              \n");
        log.write("| $$_____/                    |__/| $/             | $$_____/                              \n");
        log.write("| $$       /$$$$$$$   /$$$$$$  /$$|_//$$$$$$$      | $$    /$$$$$$   /$$$$$$  /$$$$$$/$$$$ \n");
        log.write("| $$$$$   | $$__  $$ /$$__  $$| $$  /$$_____/      | $$$$$|____  $$ /$$__  $$| $$_  $$_  $$\n");
        log.write("| $$__/   | $$  \\ $$| $$  \\ $$| $$ |  $$$$$$       | $$__/ /$$$$$$$| $$  \\__/| $$ \\ $$ \\ $$\n");
        log.write("| $$      | $$  | $$| $$  | $$| $$  \\____  $$      | $$   /$$__  $$| $$      | $$ | $$ | $$\n");
        log.write("| $$$$$$$$| $$  | $$|  $$$$$$$| $$  /$$$$$$$/      | $$  |  $$$$$$$| $$      | $$ | $$ | $$\n");
        log.write("|________/|__/  |__/ \\____  $$|__/ |_______/       |__/   \\_______/|__/      |__/ |__/ |__/\n");
        log.write("                     /$$  \\ $$                                                             \n");
        log.write("                    |  $$$$$$/                                                             \n");
        log.write("                     \\______/                                                              \n");
        log.flush();
        Thread.sleep(1000);
    }*/

    private final int xOffset = 18;
    private final int yOffset = 47;

    private Main(){
        World w = new World(20, 22);
        add(w);
        setTitle("Engi's Farm");
        Thread t1 = new Thread(w,"T1");
        t1.start();
        setSize((1 + w.getNCol() + 1 + w.InventoryTabLength/2 + 2 + w.mesQueueLength/2 + 1) * w.SPACE + xOffset, (w.getNRow() + 2) * w.SPACE + yOffset);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Main m = new Main();
            m.setVisible(true);
        });
        /*World W = new World(log);
        for (int i = 0; i < 50; ++i) log.write("\n");
        titleScreen();
        while (true) {
            try {
                for (int i = 0; i < 50; ++i) log.write("\n");
                W.Draw();
                W.Input();
                W.Update();
            } catch (Exception exp) {
                log.flush();
                break;
            }
        }*/
    }
}