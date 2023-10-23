public class Tile {
    private boolean isBomb;
    private boolean isOpen;

    private int bombsAround;

    public Tile(){
        this.isOpen = false;
        this.bombsAround = 0;
    }

}
