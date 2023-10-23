import java.lang.reflect.Array;

public class Board {
  private Tile [] board;
  private int width;
  private int height;

  public Board( int width, int height){
      this.width = width;
      this.height = height;
      this.board = new Tile[width*height];
  }
    public Tile[] getBoard() {
        return board;
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
      return builder.toString();
    }

    public String getNumberOfBombsAsString(int numberOfBombs){
      switch (numberOfBombs){
          case 0 -> {return "|   ";}
          case 9 -> { return "| B ";}
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
