import java.util.ArrayList;
import java.util.Random;

public class Board {

    private Tile[] board;
    private int width;
    private int height;
    private int totalNumberOfBombs;

    public Board(int width, int height, int numberOfBombs) {
        this.width = width;
        this.height = height;
        this.board = new Tile[width * height];
        this.totalNumberOfBombs = numberOfBombs;

    }
  

    public Board(int width, int height){
      this.width = width;
      this.height = height;
      this.board = new Tile[width*height];
  }

    public Tile[] getBoard() {
        return board;
    }

    public int getWidth() {
        return width;
    }

    public int getTotalNumberOfBombs() {return totalNumberOfBombs;}

    public int getHeight() {
        return height;
    }

    public int addBombs(int numberOfBombs){
        totalNumberOfBombs=numberOfBombs;
        Random randomBomb = new Random();
        for (int i = 0; i < numberOfBombs; i++) {
            int position = randomBomb.nextInt(board.length);
            if (!board[position].isBomb()) {
                board[position].setBomb(true);
            } else {
                i--;
            }
        }
        return numberOfBombs;
    }

    public void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = new Tile();
        }
    }

    public void addNumberOfBombsAround() {
        for (int i = 0; i < board.length; i++) {
            board[i].setBombsAround(String.valueOf(checkForBombsAround(i)));
        }
    }

    public int checkForBombsAround(int index) {
        if (board[index].isBomb()) {
            return 9;
        }
        int numberOfBombs = 0;
        ArrayList<Integer> indexOfTilesAround = getIndexOfSurroundingTiles(index);
        for (int indexOfTile : indexOfTilesAround) {
            if (board[indexOfTile].isBomb()) {
                numberOfBombs++;
            }
        }
        return numberOfBombs;
    }
    public boolean checkWinConditions() {
        for (int i = 0; i < board.length; i++) {
            if (!board[i].isBomb() && !board[i].isOpen()) {
                return false;
            }
        }
        return true;
    }

    public void revealAllBombs() {
        for (int i = 0; i < board.length; i++) {
            if (board[i].isBomb()) {
                board[i].setOpen(true);
            }
        }
    }


    public ArrayList<Integer> getIndexOfSurroundingTiles(int index) {
        ArrayList<Integer> indexOfSurroundingTiles = new ArrayList<>();
        int row = index / width;
        int column = index % width;
        // Check if tile Above
        if (row > 0) {
            indexOfSurroundingTiles.add(index - width);
        }
        // Check if tile below
        if (row < height -1 ){
            indexOfSurroundingTiles.add(index + width);

        }
        // Check for tile left
        if (column > 0) {
            indexOfSurroundingTiles.add(index - 1);
        }
        // Check for tile right
        if (column < width - 1) {
            indexOfSurroundingTiles.add(index + 1);
        }
        // Check for tile Above left
        if (row > 0 && column > 0) {
            indexOfSurroundingTiles.add(index - width - 1);
        }
        // Check for tile above right
        if (row > 0 && column < width - 1) {
            indexOfSurroundingTiles.add(index - width + 1);
        }
        // Check for tile below left
        if (row < height - 1 && column > 0) {
            indexOfSurroundingTiles.add(index + width - 1);
        }
        // Check for tile below right
        if (row < height -1 && column < width -1){

            indexOfSurroundingTiles.add(index + width + 1);
        }

        return indexOfSurroundingTiles;
    }

    public int openTile(int index){
        if (board[index].isBomb()){
            board[index].setOpen(true);
            return -1;
        } else if (board[index].isOpen()) {
            return 0;
        }
        board[index].setOpen(true);
        if(board[index].getBombsAround().equals("0")){
            openTilesAround(index);
        }
        return 1;
    }

    public void openTilesAround(int index){
       ArrayList<Integer> indexOfSurroundingTiles = getIndexOfSurroundingTiles(index);
        for (int indexOfTileAround: indexOfSurroundingTiles) {
            if(!board[indexOfTileAround].isOpen() && board[indexOfTileAround].getBombsAround().equals("0")){
                board[indexOfTileAround].setOpen(true);
                openTilesAround(indexOfTileAround);
            } else if (!board[indexOfTileAround].isOpen()) {
                board[indexOfTileAround].setOpen(true);
            }
        }
    }

    public boolean toggleFlag(int index){
        if (board[index].isOpen()){
            return false;
        }
        board[index].toggleFlag();
        return true;
    }

    public boolean isTileFlagged(int index){
        return board[index].isFlagged();
    }


    public String toString() {
        final String CYAN = "\u001B[36m";
        final String RESET = "\u001B[0m";

        StringBuilder builder = new StringBuilder();
        builder.append("   ");
        for (int i = 0; i < width; i++) {
            String number = "";
            if (i + 1 < 10) {
                number = "  " + (i + 1) + " ";
            } else {
                number = " " + (i + 1) + " ";

            }
            builder.append(number);
        }
        builder.append("\n");
        builder.append(drawLine(width));
        for (int i = 0; i < board.length; i++) {
            if (i % width == 0) {
                if (i != 0) {
                    builder.append("\n   ");
                    builder.append(drawDivider(width));
                }

                char letterForColumn = (char) ('A' + i / width);
                String changeRowAndAddLetter = "\n " + letterForColumn + " ";
                builder.append(changeRowAndAddLetter);
            }
            String numberOfBombs = getNumberOfBombsAsString(board[i].getBombsAround());
            String tileHiddenOrFlagged = board[i].isFlagged() ? "| "+ CYAN + "F" + RESET +" ": "| X ";
            builder.append(board[i].isOpen() ? numberOfBombs : tileHiddenOrFlagged);
            if (i % width == width - 1) {
                builder.append("|");

            }
        }

      builder.append("\n");
      builder.append(drawLine(width));
      builder.append("\n   Bombs: " + totalNumberOfBombs);
      return builder.toString();

    }

    public String getNumberOfBombsAsString(String numberOfBombs) {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[35m";
        final String CYAN = "\u001B[36m";


        switch (numberOfBombs) {
            case "0":
                return "|   ";

            case "1":
                return "| "+ GREEN + numberOfBombs +RESET +" ";

            case "2":
                return "| "+ YELLOW + numberOfBombs +RESET +" ";

            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":

                return "| "+ RED + numberOfBombs +RESET +" ";

            case "9":
                return "| " + "B" +" ";

        }
        return "";
    }

    public String drawDivider(int width) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < width; i++) {
            if (i == 0) {
                builder.append("-");
            }
            builder.append(i < width - 1 ? "---|" : "----");
        }
        return builder.toString();
    }

    public String drawLine(int width) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < width; i++) {
            if (i == 0) {
                builder.append("   +----");
            } else if (i == width - 1) {
                builder.append("---+");
            } else {
                builder.append("----");
            }
        }

        return builder.toString();

    }

}
