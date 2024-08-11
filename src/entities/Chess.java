package entities;

public class Chess {
    private int rows;
    private int cols;
    private int generations;
    private int speedGenerations;
    private String[] populationRule;
    private int quantityGenerations;
    private Cell[][] chess;
    private final int neighborhood;

    public Chess(int rows, int cols, int generations, int speedGenerations, String[] populationRule, int neighborhood) {
        this.rows = rows;
        this.cols = cols;
        this.generations = generations;
        this.speedGenerations = speedGenerations;
        this.populationRule = populationRule;
        this.chess = new Cell[rows][cols];
        this.neighborhood = neighborhood;
        generateCells(this, neighborhood); // Gera as células iniciais no tabuleiro
    }

    public Cell[][] getChess() {
        return chess;
    }

    public void setChess(Cell[][] chess) {
        this.chess = chess;
    }

    public int getQuantityGenerations() {
        return quantityGenerations;
    }

    public void setQuantityGenerations(int quantityGenerations) {
        this.quantityGenerations = quantityGenerations;
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

    public void generateCells(Chess chess, int neighborhood) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                chess.setCell(i, j, new Cell(false, i, j, this.chess));
            }
        }
    }

    public void printChess()  {
        for (int i = 0; i < rows; i++) {
            System.out.print("{ ");
            for (int j = 0; j < cols; j++) {
                if (!chess[i][j].getLife()) System.out.print("\uD83D\uDFE6 ");
                else System.out.print("\uD83D\uDFE5 ");
            }
            System.out.println("}");
        }
    }

    public void cellLife(int xCell, int yCell) {
        for (Cell[] cells : chess) {
            for (int j = 0; j < cells.length; j++) {
                chess[yCell][xCell].setLife(true);
                chess[yCell - 1][xCell].setLife(true);
                chess[yCell + 1][xCell].setLife(true);
                chess[yCell][xCell - 1].setLife(true);
                chess[yCell][xCell + 1].setLife(true);
                chess[yCell - 1][xCell - 1].setLife(true);
                chess[yCell - 1][xCell + 1].setLife(true);
                chess[yCell + 1][xCell - 1].setLife(true);
                chess[yCell + 1][xCell + 1].setLife(true);
            }
        }
    }

    public void executeGameGol() throws InterruptedException {

        for (int g = 0; g < generations; g++) {
            Cell[][] nextChess = new Cell[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    nextChess[i][j] = new Cell(false, i, j, nextChess);
                }
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    chess[i][j].setNeighborhood(0);
                    boolean newLifeState = switch (neighborhood) {
                        case 1 -> chess[i][j].cellNeighborhoodOne();
                        case 2 -> chess[i][j].cellNeighborhoodTwo();
                        case 4 -> chess[i][j].cellNeighborhoodFour();
                        case 5 -> chess[i][j].cellNeighborhoodFive();
                        default -> chess[i][j].cellNeighborhoodThree();
                    };
                    nextChess[i][j].setLife(newLifeState);
                }
            }

            setChess(nextChess);
            System.out.println("Geração: " + g);
            printChess();
            System.out.println();

            Thread.sleep(speedGenerations);
            }
    }

}
