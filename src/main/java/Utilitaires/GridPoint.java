package Utilitaires;

public class GridPoint implements java.io.Serializable{

    private final int row;
    private final int col;

    public GridPoint(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }
}
