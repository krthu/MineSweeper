public class MineSweeper {
    private Board board;

    public MineSweeper(){

    }

    public int getIndexFromCoordinate(int width, int height, String coordinate){
        int row = coordinate.toUpperCase().charAt(0) - 'A';
        try{
            int column = Integer.parseInt((coordinate.substring(1)))-1;
            if (column >= width || row >= height || column < 0 || height < 0){
                return -1;
            }
            return (row * width + column);

        }catch (Exception e){
            return -1;
        }
    }


}
