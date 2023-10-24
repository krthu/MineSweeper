import java.util.Scanner;

public class MineSweeper {

    Scanner sc = new Scanner(System.in);
    private Board board;
    private int boardHight;
    private int boardWidth;

    private int numberOfBombs;

    public MineSweeper(){
        boardHight = 5;
        boardWidth = 10;
        numberOfBombs = 5;
    }


    public void gameLoop(){
        createBoard(boardWidth, boardHight, numberOfBombs);
        System.out.println(board);
    }

    public int getUserInput(){
        int index = -1;
        do {
            System.out.println("Choose a cordinate (for example 'A1')");
            String userInput = sc.nextLine();
            index = getIndexFromCoordinate(board.getWidth(), board.getHeight(), userInput);
        }while (index == -1);
         return index;
    }

    public int getIndexFromCoordinate(int width, int height, String coordinate){
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




}
