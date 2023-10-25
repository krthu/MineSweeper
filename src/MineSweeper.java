import java.util.Scanner;

public class MineSweeper {

    Scanner sc = new Scanner(System.in);
    private Board board;
    private int boardHeight;
    private int boardWidth;
    private int win;
    private int gamesPlayed;

    private int numberOfBombs;

    private final int MIN_BOARD_WIDTH = 5;
    private final int MAX_BOARD_WIDTH = 26;
    private final int MIN_BOARD_HEIGHT = 5;
    private final int MAX_BOARD_HEIGHT = 26;

    private Mode inputMode = Mode.OPEN;

    private enum Mode{
        OPEN,
        FLAG
    }
  
    public int getWin() {
        return win;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public MineSweeper(){
        boardWidth = 7;
        boardHeight = 7;
        numberOfBombs = 5;

        }


        public void gameLoop(){
        createBoard(boardWidth, boardHeight, numberOfBombs);
        boolean win = false;
        boolean bomb = false;
        while (!win && !bomb){
            System.out.println(board);
            int index = getUserInput();
             if (index == -1) {
                System.out.println("Invalid input!");
                continue;
              } else if (index == -2) {
                 toggleMode();
                 continue;
             }
             if (inputMode == Mode.OPEN){
                 if (board.isTileFlagged(index)){
                     System.out.println("Tile is flagged! Remove flag to open.");
                 }else{
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
                }
             }else {
                 if (!board.toggleFlag(index)){
                     System.out.println("Already open.");
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

    public void toggleMode(){
        if (inputMode == Mode.FLAG){
            inputMode = Mode.OPEN;
        } else {
            inputMode = Mode.FLAG;
        }
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
            System.out.println("The board has " + (width*height) + " tiles.");
            System.out.println("The number of bombs is set at " + numberOfBombs +". Pick a bigger board.");
        }
    }
    public void setBombs(){
        int min = 1;
        int max = boardHeight * boardWidth;
            int setBombs = getSafeInt("Enter how many bombs you want:",min,max);
            setNumberOfBombs(setBombs);
    }

    public int getUserInput(){
        int index = -1;
        do {
            System.out.println("Choose a coordinate to " + inputMode +" (for example 'A1')");
            String userInput = sc.nextLine();
            if (userInput.trim().equalsIgnoreCase("mode"))
            {
                return -2;
            }
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
    public void settings(){
        String settingsMeny = "";
        while (!settingsMeny.equals("3")){
            System.out.println("""
                    Settings
                    1.Set size of board
                    2.Set numbers of bombs
                    3.Go back to menu
                    """);
            settingsMeny = sc.nextLine();
            if (settingsMeny.equals("1")){
                setSizeOfBoard();
            }
            if (settingsMeny.equals("2")){
                setBombs();
            }
            if (settingsMeny.equals("3")) {
            }
        }

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
                    System.out.println("Has to be between " + min + "-" + max);
                }

            }catch (Exception e){
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }


    }




}
