package entities;

public class Cell {
    private Boolean life;
    private Integer row;
    private Integer col;
    private Cell[][] chess;
    private int neighborhoodQuantity = 0;

    public Cell(Boolean life,  Integer row, Integer col, Cell[][] chess) {
        this.life = life;
        this.col = col;
        this.row = row;
        this.chess = chess;
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
        return neighborhoodQuantity;
    }

    public void setNeighborhood(int neighborhoodQuantity) {
        this.neighborhoodQuantity = neighborhoodQuantity;
    }

    public boolean verifyCellIndex(String directionCell) {
        return switch (directionCell) {
            case "up" -> row - 1 >= 0;
            case "down" -> row + 1 < chess.length;
            case "left" -> col - 1 >= 0;
            case "right" -> col + 1 < chess[row].length;
            case "leftUp" -> row - 1 >= 0 && col - 1 >= 0;
            case "leftDown" -> row + 1 < chess.length && col - 1 >= 0;
            case "rightUp" -> row - 1 >= 0 && col + 1 < chess[row].length;
            case "rightDown" -> row + 1 < chess.length && col + 1 < chess[row].length;
            default -> false;
        };
    }

    public boolean viewUpCell() {
        return verifyCellIndex("up") ? chess[row - 1][col].getLife() : false;
    }

    public boolean viewDownCell() {
        return verifyCellIndex("down") ? chess[row + 1][col].getLife() : false;
    }

    public boolean viewLeftCell() {
        return verifyCellIndex("left") ? chess[row][col - 1].getLife() : false;
    }

    public boolean viewRightCell() {
        return verifyCellIndex("right") ? chess[row][col + 1].getLife() : false;
    }

    public boolean viewLeftUpCell() {
        return verifyCellIndex("leftUp") ? chess[row - 1][col - 1].getLife() : false;
    }

    public boolean viewLeftDownCell() {
        return verifyCellIndex("leftDown") ? chess[row + 1][col - 1].getLife() : false;
    }

    public boolean viewRightUpCell() {
        return verifyCellIndex("rightUp") ? chess[row - 1][col + 1].getLife() : false;
    }

    public boolean viewRightDownCell() {
        return verifyCellIndex("rightDown") ? chess[row + 1][col + 1].getLife() : false;
    }

    public boolean verifyCellLife(int neighborhoodQuantity) {
        if (life) return neighborhoodQuantity == 2 || neighborhoodQuantity == 3;
        return neighborhoodQuantity == 3;
    }

    public boolean cellNeighborhoodOne(){

        if (viewDownCell()) neighborhoodQuantity++;
        if (viewLeftCell()) neighborhoodQuantity++;
        if (viewRightCell()) neighborhoodQuantity++;
        if (viewUpCell()) neighborhoodQuantity++;

        return verifyCellLife(neighborhoodQuantity);
    }

    public boolean cellNeighborhoodTwo(){
        if (viewUpCell()) neighborhoodQuantity++;
        if (viewDownCell()) neighborhoodQuantity++;
        if (viewLeftCell()) neighborhoodQuantity++;
        if (viewLeftUpCell()) neighborhoodQuantity++;
        if (viewRightDownCell()) neighborhoodQuantity++;
        if (viewRightUpCell()) neighborhoodQuantity++;

        return verifyCellLife(neighborhoodQuantity);
    }

    public boolean cellNeighborhoodThree(){
        if (viewUpCell()) neighborhoodQuantity++;
        if (viewDownCell()) neighborhoodQuantity++;
        if (viewLeftCell()) neighborhoodQuantity++;
        if (viewRightCell()) neighborhoodQuantity++;
        if (viewLeftUpCell()) neighborhoodQuantity++;
        if (viewLeftDownCell()) neighborhoodQuantity++;
        if (viewRightDownCell()) neighborhoodQuantity++;
        if (viewRightUpCell()) neighborhoodQuantity++;

        return verifyCellLife(neighborhoodQuantity);
    }

    public boolean cellNeighborhoodFour(){
        if (viewLeftUpCell()) neighborhoodQuantity++;
        if (viewLeftDownCell()) neighborhoodQuantity++;
        if (viewRightDownCell()) neighborhoodQuantity++;
        if (viewRightUpCell()) neighborhoodQuantity++;

        return verifyCellLife(neighborhoodQuantity);
    }

    public boolean cellNeighborhoodFive(){
        if (viewLeftUpCell()) neighborhoodQuantity++;
        if (viewLeftDownCell()) neighborhoodQuantity++;
        if (viewRightDownCell()) neighborhoodQuantity++;
        if (viewRightUpCell()) neighborhoodQuantity++;

        return verifyCellLife(neighborhoodQuantity);
    }
}
