import java.util.Scanner;

public class MineSweeper {

    Scanner sc = new Scanner(System.in);
    private Board board;
    private int boardHeight;
    private int boardWidth;
    private int win;
    private int gamesPlayed;

    private int numberOfBombs;

    public int getWin() {
        return win;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public MineSweeper(){

        numberOfBombs = 5;
        }


        public void gameLoop(){
        setSizeOfBoard();
        createBoard(boardWidth, boardHeight, numberOfBombs);
        boolean win = false;
        boolean bomb = false;
        while (!win && !bomb){
            System.out.println(board);
            int index = getUserInput();
             if (index == -1) {
            System.out.println("Invalid input!");
            continue;
              }
            switch (board.openTile(index)){
                case -1 -> {
                    bomb = true;
                    System.out.println("You hit a Bomb!");
                }
                case 0 ->{
                    System.out.println("Already open.");
                }
                case 1 ->{

                }
            }
            // Check  win = board.win()
        }
        if (win){
            System.out.println("Congratulations only bombs left!");
            addwin();
        }

        System.out.println(board);
        gamesPlayed();

    }
 
    public void setSizeOfBoard() {
        System.out.println("Enter the width of the board:");
        int width = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the height of the board:");
        int height = sc.nextInt();
        sc.nextLine();
        boardWidth = width;
        boardHeight = height;
    }

    public int getUserInput(){
        int index = -1;
        do {
            System.out.println("Choose a cordinate (for example 'A1')");
            String userInput = sc.nextLine();
            index = getIndexFromCoordinate(board.getWidth(), board.getHeight(), userInput);
            if (index == -1){
                System.out.println("Invalid input!");
            }
        }while (index == -1);
         return index;
    }

    public int getIndexFromCoordinate(int width, int height, String coordinate){
        if (coordinate == null || coordinate.isEmpty()) {
            return -1;
        }
        int row = coordinate.toUpperCase().charAt(0) - 'A';
        try{
            int column = Integer.parseInt((coordinate.substring(1)))-1;
            int index = row * width + column;
            if (column >= width || row >= height || index < 0){
                return -1;
            }
            return (index);

        }catch (Exception e){
            return -1;
        }
    }

    public void createBoard(int width, int height, int numberOfBombs){
        board = new Board(width, height);
        board.fillBoard();
        board.addBombs(numberOfBombs);
        board.addNumberOfBombsAround();
    }
    public void addwin(){
        this.win++;
    }
    public void gamesPlayed(){
        this.gamesPlayed++;
    }
    public void stats(){
        System.out.println("Wins: " + getWin());
        System.out.println("Games played: " + getGamesPlayed());
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }




}
