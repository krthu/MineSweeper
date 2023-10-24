import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MineSweeper game = new MineSweeper();
        String meny = "";
        while (!meny.equals("2")){
            System.out.println("""
                    1.play Mine Sweeper
                    2.Quit
                    """);
            meny = sc.nextLine();
            if (meny.equals("1")){
                game.gameLoop();
            }
            if (meny.equals("2")){
                System.out.println("Game Ended");
            }
        }
        sc.close();
    }
}
