package engisfarm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Main{
    private static BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static void titleScreen()  throws IOException{
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
    }

    public static void main(String[] args){
        try{
            World W = new World(log);
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
            }
        }
        catch (IOException e){

        }
    }
}