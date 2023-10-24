public class MineSweeper {
    Board board;

    public MineSweeper(){
        

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
        board = new Board(width, height, numberOfBombs);
    }




}
