import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MineSweeper game = new MineSweeper();
        game.readStatsFile();
        System.out.println("""
                      __  __ _            ____                                  \s
                     |  \\/  (_)_ __   ___/ ___|_      _____  ___ _ __   ___ _ __\s
                     | |\\/| | | '_ \\ / _ \\___ \\ \\  \\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|
                     | |  | | | | | |  __/___) \\ V  V /  __/  __/ |_) |  __/ |  \s
                     |_|  |_|_|_| |_|\\___|____/ \\_/\\_/ \\___|\\___| .__/ \\___|_|  \s
                                                                |_|             \s
                    Welcome to the MineSweeper!    
                """);
        String meny = "";
        while (!meny.equals("5")) {
            System.out.println("""

                    ---- MAIN MENU ----       
                    1.play Mine Sweeper
                    2.Settings
                    3.Show Stats
                    4.Rules
                    5.Quit
                    """);
            meny = sc.nextLine();
            if (meny.equals("1")) {
                game.gameLoop();
            } else if (meny.equals("2")) {
                game.settings();
            } else if (meny.equals("3")) {
                game.stats();
            } else if (meny.equals("4")) {
                game.rules();
            } else if (meny.equals("5")) {
                System.out.println("Game Ended");
            }
        }
        sc.close();
    }
}
 