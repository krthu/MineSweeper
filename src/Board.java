import java.lang.reflect.Array;
import java.util.Random;

public class Board {

  private Tile [] board;
  private int width;
  private int height;



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

    public int getHeight() {
        return height;
    }
    public int addBombs(int numberOfBombs){
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


}
