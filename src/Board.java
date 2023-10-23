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

}
