package entities;

public class Cell {
    private Boolean life = false;
    private Integer row;
    private Integer col;
    private Cell[][] chess;
    private int neighborhood;

    public Cell(Boolean life,  Integer row, Integer col,int neighborhood) {
        this.life = life;
        this.col = col;
        this.row = row;
        this.neighborhood = neighborhood;
    }

    public Boolean getLife() {
        return life;
    }

    public void setLife(Boolean life) {
        this.life = life;
    }

    public Cell[][] getChess() {
        return chess;
    }

    public void setChess(Cell[][] chess) {
        this.chess = chess;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public int getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(int neighborhood) {
        this.neighborhood = neighborhood;
    }

}
