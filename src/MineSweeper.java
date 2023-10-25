import java.util.Scanner;

public class MineSweeper {

    Scanner sc = new Scanner(System.in);
    private Board board;
    private int boardHeight;
    private int boardWidth;

    private int numberOfBombs;

    private final int MIN_BOARD_WIDTH = 5;
    private final int MAX_BOARD_WIDTH = 26;
    private final int MIN_BOARD_HEIGHT = 5;
    private final int MAX_BOARD_HEIGHT = 26;

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
        }

        System.out.println(board);

    }
 
    public void setSizeOfBoard() {
        while (true){
            int width = getSafeInt("Enter the width of the board:", MIN_BOARD_WIDTH, MAX_BOARD_WIDTH);
            int height = getSafeInt("Enter the height of the board:", MIN_BOARD_HEIGHT, MAX_BOARD_HEIGHT );
            if (width*height > numberOfBombs){
                boardWidth = width;
                boardHeight = height;
                return;
            }
        }
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

    public int getSafeInt(String question, int min, int max){
        while (true){
            System.out.println(question);
            try {
                int input = sc.nextInt();
                sc.nextLine();
                if (input >= min && input <= max){
                    return input;
                }else {
                    System.out.println("Has du be between " + min + "-" + max);
                }

            }catch (Exception e){
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }


    }




}
