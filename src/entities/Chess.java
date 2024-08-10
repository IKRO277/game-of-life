package entities;

public class Chess {
    private int rows;
    private int cols;
    private int generations;
    private int speedGenerations;
    private String[] populationRule;
    private int quantityGerations;
    private Cell[][] chess;

    public Chess(int rows, int cols, int generations, int speedGenerations, String[] populationRule, int neighborhood) {
        this.rows = rows;
        this.cols = cols;
        this.generations = generations;
        this.speedGenerations = speedGenerations;
        this.populationRule = populationRule;
        this.chess = new Cell[rows][cols];
        generateCells(this, neighborhood);
    }

    public Cell[][] getChess() {
        return chess;
    }

    public void setChess(Cell[][] chess) {
        this.chess = chess;
    }

    public int getQuantityGerations() {
        return quantityGerations;
    }

    public void setQuantityGerations(int quantityGerations) {
        this.quantityGerations = quantityGerations;
    }

    public String[] getPopulationRule() {
        return populationRule;
    }

    public void setPopulationRule(String[] populationRule) {
        this.populationRule = populationRule;
    }

    public int getSpeedGenerations() {
        return speedGenerations;
    }

    public void setSpeedGenerations(int speedGenerations) {
        this.speedGenerations = speedGenerations;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCell(int row, int col, Cell cell) {
        chess[row][col] = cell;
    }

    public Chess generateCells(Chess chess, int neighborhood) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                chess.setCell(i, j, new Cell(false, i, j, neighborhood));
            }
        }
        return chess;
    }

    public void printChess()  {
        for (int i = 0; i < rows; i++) {
            System.out.print("{ ");
            for (int j = 0; j < cols; j++) {
                if(chess[i][j].getLife() == false) {
                    System.out.print("- ");
                } else {
                    System.out.print("o ");
                }
            }
            System.out.println("}");
        }
    }
}
