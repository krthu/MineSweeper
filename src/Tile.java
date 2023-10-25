public class Tile {
    private boolean isBomb;
    private boolean isOpen;

    private boolean isFlagged;

    private int bombsAround;

    public Tile(){
        this.isOpen = false;
        this.isFlagged = false;
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

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }

    public void toggleFlag(){
        isFlagged = !isFlagged;
    }


    public String toString(){
        return "isBomb: " + isBomb + " isOpen " + isOpen + " bombsAround " + bombsAround;
    }

}
