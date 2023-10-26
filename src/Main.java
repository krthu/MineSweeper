import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MineSweeper game = new MineSweeper();
        game.readStatsFile();
        String meny = "";
        while (!meny.equals("4")){
            System.out.println("""
                    ---- MAIN MENU ----       
                    1.play Mine Sweeper
                    2.Settings
                    3.Show Stats
                    4.Quit
                    """);
            meny = sc.nextLine();
            if (meny.equals("1")){
                game.gameLoop();
            }
            else if (meny.equals("2")){
                game.settings();
            }
            else if (meny.equals("3")){
                game.stats();

            }
            else if (meny.equals("4")){
                game.saveStatsToFile();
                System.out.println("Stats saved.");
                System.out.println("Game Ended");
            }
        }
        sc.close();

    }
}
 