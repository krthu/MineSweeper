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

    public String toString(){
      StringBuilder builder = new StringBuilder();
      builder.append("   ");
      for (int i = 0; i < width; i++){
          builder.append("  ");
          builder.append(i+1);
          builder.append(" ");
      }
      builder.append("\n");
      builder.append(drawLine(width));
      for (int i = 0; i < board.length; i++){
        if( i % width == 0){
            if(i != 0){
                builder.append("\n");
            }

            char letterForColumn = (char) ('A' + i/width);
            String changeRowAndAddLetter = "\n " +letterForColumn + " ";
            builder.append(changeRowAndAddLetter);
        }

        builder.append(board[i].isOpen() ? board[i].getBombsAround() : "| X ");
        if (i % width == width -1 ){
            builder.append("|");
        }
      }
      builder.append("\n");
      builder.append(drawLine(width));
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
