package engisfarm;


public class Main{
    private static void titleScreen() {
        System.out.printf(" /$$$$$$$$                     /$$ /$$              /$$$$$$$$                              \n");
        System.out.printf("| $$_____/                    |__/| $/             | $$_____/                              \n");
        System.out.printf("| $$       /$$$$$$$   /$$$$$$  /$$|_//$$$$$$$      | $$    /$$$$$$   /$$$$$$  /$$$$$$/$$$$ \n");
        System.out.printf("| $$$$$   | $$__  $$ /$$__  $$| $$  /$$_____/      | $$$$$|____  $$ /$$__  $$| $$_  $$_  $$\n");
        System.out.printf("| $$__/   | $$  \\ $$| $$  \\ $$| $$ |  $$$$$$       | $$__/ /$$$$$$$| $$  \\__/| $$ \\ $$ \\ $$\n");
        System.out.printf("| $$      | $$  | $$| $$  | $$| $$  \\____  $$      | $$   /$$__  $$| $$      | $$ | $$ | $$\n");
        System.out.printf("| $$$$$$$$| $$  | $$|  $$$$$$$| $$  /$$$$$$$/      | $$  |  $$$$$$$| $$      | $$ | $$ | $$\n");
        System.out.printf("|________/|__/  |__/ \\____  $$|__/ |_______/       |__/   \\_______/|__/      |__/ |__/ |__/\n");
        System.out.printf("                     /$$  \\ $$                                                             \n");
        System.out.printf("                    |  $$$$$$/                                                             \n");
        System.out.printf("                     \\______/                                                              \n");
    }

    public static void main(String[] args) {
        World W = new World();
        titleScreen();
        while (true) {
            try {
                W.Draw();
                W.Input();
                W.Update();
            } catch (Exception exp) {
                    break;
            }
        }
    }
}