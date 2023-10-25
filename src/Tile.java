public class Tile {
    private boolean isBomb;

    private boolean isOpen;

    private int bombsAround;

    public Tile(){
        this.isOpen = false;
        this.bombsAround = 0;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }

    @Override
    public String toString(){
        return "isBomb: " + isBomb + " isOpen " + isOpen + " bombsAround " + bombsAround;
    }

}




