import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class Board {

  private Tile [] board;
  private int width;
  private int height;




  private int totalNumberOfBombs;



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
        for (int i = 0; i < numberOfBombs ; i++) {
            int position = randomBomb.nextInt(board.length);
            if (!board[position].isBomb()) {
                board[position].setBomb(true);
            } else {
                i--;
            }
        }
        return numberOfBombs;
    }


    public void fillBoard(){
        for (int i = 0; i < board.length; i++){
            board[i]= new Tile();
        }
    }

    public void addNumberOfBombsAround(){
      for (int i = 0; i < board.length; i++){
          board[i].setBombsAround(checkForBombsAround(i));
      }
    }

    public int checkForBombsAround(int index){
      if (board[index].isBomb()){
          return 9;
      }
      int numberOfBombs = 0;
      ArrayList<Integer> indexOfTilesAround = getIndexOfSurroundingTiles(index);
        for (int indexOfTile: indexOfTilesAround) {
            if (board[indexOfTile].isBomb()){
                numberOfBombs++;
            }
        }
      return numberOfBombs;
    }

    public ArrayList<Integer> getIndexOfSurroundingTiles(int index){
        ArrayList<Integer> indexOfSurroundingTiles = new ArrayList<>();
        int row = index/width;
        int column = index%width;

        if (row > 0){
            indexOfSurroundingTiles.add(index - width);
        }

        if (row < height -1 ){
            indexOfSurroundingTiles.add(index + height);
        }

        if (column > 0){
            indexOfSurroundingTiles.add(index - 1);
        }

        if (column < width -1){
            indexOfSurroundingTiles.add(index + 1);
        }

        if (row > 0 && column > 0){
            indexOfSurroundingTiles.add(index -width - 1);
        }
        if (row > 0 && column < width -1 ){
            indexOfSurroundingTiles.add(index - width + 1);
        }

        if (row < height -1 && column > 0){
            indexOfSurroundingTiles.add(index + width -1);
        }

        if (row < height -1 && column > width -1){
            indexOfSurroundingTiles.add(index + width + 1);
        }

        return indexOfSurroundingTiles;
    }



    public String toString(){
      StringBuilder builder = new StringBuilder();
      builder.append("   ");
      for (int i = 0; i < width; i++){
          String number = "";
          if (i+1 < 10){
              number = "  " + (i + 1) + " ";
          }else {
              number = " " + (i + 1) + " ";
          }
          builder.append(number);
      }
      builder.append("\n");
      builder.append(drawLine(width));
      for (int i = 0; i < board.length; i++){
        if( i % width == 0){
            if(i != 0){
                builder.append("\n   ");
                builder.append(drawDivider(width));
            }

            char letterForColumn = (char) ('A' + i/width);
            String changeRowAndAddLetter = "\n " +letterForColumn + " ";
            builder.append(changeRowAndAddLetter);
        }
        String numberOfBombs = getNumberOfBombsAsString(board[i].getBombsAround());
        builder.append(board[i].isOpen() ? numberOfBombs : "| X ");
        if (i % width == width -1 ){
            builder.append("|");

        }
      }
      builder.append("\n");
      builder.append(drawLine(width));
      builder.append("Bombs; " + totalNumberOfBombs);
      return builder.toString();
    }

    public String getNumberOfBombsAsString(int numberOfBombs){

      switch (numberOfBombs){
          case 0 -> {return "|   ";}
          case 9 -> { return "| \u001B[31mB\u001B[0m ";}
          default -> {return "| "+ numberOfBombs + " ";}
      }
    }


    public String drawDivider(int width){
      StringBuilder builder = new StringBuilder();
        for (int i = 0; i < width; i++) {
            if (i == 0) {
                builder.append("-");
            }
            builder.append(i < width - 1 ? "---|" : "----");
        }
      return builder.toString();
    }

    public String drawLine(int width){
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < width; i++){
          if (i == 0){
              builder.append("   +----");
          } else if (i == width-1) {
              builder.append("---+");
          }else {
              builder.append("----");
          }
      }

      return builder.toString();

    }

}
