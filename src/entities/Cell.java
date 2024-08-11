package entities;

public class Cell {
    private Boolean life = false; // Indica se a célula está viva ou morta
    private Integer row;
    private Integer col;
    private Cell[][] chess;
    private int neighborhood; // Indica o tipo de vizinhança (por exemplo, a quantidade de vizinhos considerados)

    // Construtor da classe Cell que inicializa uma célula com o estado de vida, posição e tipo de vizinhança
    public Cell(Boolean life,  Integer row, Integer col, int neighborhood) {
        this.life = life;
        this.col = col;
        this.row = row;
        this.neighborhood = neighborhood;
    }

    // Método getter para obter o estado de vida da célula
    public Boolean getLife() {
        return life;
    }

    // Método setter para definir o estado de vida da célula
    public void setLife(Boolean life) {
        this.life = life;
    }

    // Método getter para obter o tabuleiro de células
    public Cell[][] getChess() {
        return chess;
    }

    // Método setter para definir o tabuleiro de células
    public void setChess(Cell[][] chess) {
        this.chess = chess;
    }

    // Método getter para obter a linha da célula no tabuleiro
    public Integer getRow() {
        return row;
    }

    // Método setter para definir a linha da célula no tabuleiro
    public void setRow(Integer row) {
        this.row = row;
    }

    // Método getter para obter a coluna da célula no tabuleiro
    public Integer getCol() {
        return col;
    }

    // Método setter para definir a coluna da célula no tabuleiro
    public void setCol(Integer col) {
        this.col = col;
    }

    // Método getter para obter o tipo de vizinhança da célula
    public int getNeighborhood() {
        return neighborhood;
    }

    // Método setter para definir o tipo de vizinhança da célula
    public void setNeighborhood(int neighborhood) {
        this.neighborhood = neighborhood;
    }

    public boolean viewUpCell(){
        if (row -1 >= 0 ){
            return chess[row - 1][col].getLife();
        }
        return false;
    }

    public boolean viewDownCell(){
        if (row + 1 < chess.length){
            return chess[row + 1][col].getLife();
        }
        return false;
    }

    public boolean viewLeftCell(){
        if (col -1 >= 0 ){
            return chess[row][col - 1].getLife();
        }
        return false;
    }

    public boolean viewRightCell(){
        if (col + 1 < chess[row].length){
            return chess[row][col + 1].getLife();
        }
        return false;
    }

    public boolean viewLeftUpCell(){
        if (row - 1 >= 0 && col - 1 >= 0){
            return chess[row - 1][col - 1].getLife();
        }
        return false;
    }

    public boolean viewLeftDownCell(){
        if (row + 1 >= chess.length && col - 1 < chess.length){
            return chess[row + 1][col - 1].getLife();
        }
        return false;
    }

    public boolean viewRightUpCell(){
        if (col + 1 < chess[row].length && row - 1 >= 0){
            return chess[row - 1][col + 1].getLife();
        }
        return false;
    }

    public boolean viewRightDownCell(){
        if (row + 1 >= chess.length && col + 1 < chess[row].length){
            return chess[row + 1][col + 1].getLife();
        }
        return false;
    }

    public void cellNeighborhoodOne(){
        int neighborhoodQnt = 0;

        if (viewDownCell()) neighborhood++;
        if (viewLeftCell()) neighborhood++;
        if (viewRightCell()) neighborhood++;
        if (viewUpCell()) neighborhood++;

        if(neighborhoodQnt < 2 || neighborhoodQnt > 3){
            setLife(false);
        } else {
            setLife(true);
        }
    }

    public void cellNeighborhoodTwo(){
        int neighborhoodQnt = 0;

        if (viewUpCell()) neighborhood++;
        if (viewDownCell()) neighborhood++;
        if (viewLeftCell()) neighborhood++;
        if (viewRightCell()) neighborhood++;
        if (viewLeftUpCell()) neighborhood++;
        if (viewLeftDownCell()) neighborhood++;
        if (viewRightUpCell()) neighborhood++;
        if (viewRightDownCell()) neighborhood++;

        if (neighborhoodQnt < 2 || neighborhoodQnt > 3){
            setLife(false);
        }else {
            setLife(true);
        }
    }
}
