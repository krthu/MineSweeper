import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MineSweeper game = new MineSweeper();
        String meny = "";
        while (!meny.equals("5")){
            System.out.println("""
                    1.play Mine Sweeper
                    2.Settings
                    3.Show Stats
                    4.Rules
                    5.Quit
                    """);
            meny = sc.nextLine();
            if (meny.equals("1")){
                game.gameLoop();
            }
            if (meny.equals("2")){
                game.settings();
            }
            if (meny.equals("3")){
                game.stats();

            }
            if (meny.equals("4")){
                game.rules();
            }
            if (meny.equals("5")){
                System.out.println("Game Ended");
            }
        }
        sc.close();

    }
}
 