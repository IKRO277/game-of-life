package entities;

import java.util.Random;

public class Chess {
    private int rows, cols, generations, speedGenerations, quantityGenerations, neighborhood;
    private String[] populationRule;
    private Cell[][] chess;

    public Chess(int rows, int cols, int generations, int speedGenerations, String[] populationRule, int neighborhood) {
        this.rows = rows;
        this.cols = cols;
        this.generations = generations;
        this.speedGenerations = speedGenerations;
        this.populationRule = populationRule;
        this.chess = new Cell[rows][cols];
        this.neighborhood = neighborhood;
        generateCells(this); //
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

    public void generateCells(Chess chess) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                chess.setCell(i, j, new Cell(false, i, j, this.chess));
            }
        }
    }

    public void generateRandomCells(Chess chess) {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                chess.setCell(i, j, new Cell(random.nextBoolean(), i, j, this.chess));
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
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void cellLife() {
        for (int i = 0; i < populationRule.length; i++) {
            String[] populationRow = populationRule[i].split("");
            for (int j = 0; j < populationRow.length; j++) {
                String populationCol = populationRow[j];
                if(Integer.parseInt(populationCol) == 1) chess[i + 4][j + 5].setLife(true);
            }
        }
    }

    public Cell[][] generateNextChess(Cell[][] nextChess) {
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
        return nextChess;
    }

    public void executeGameGol() throws InterruptedException {

        for (int g = 0; g < generations; g++) {

            Cell[][] nextChess = new Cell[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    nextChess[i][j] = new Cell(false, i, j, nextChess);
                }
            }

            setChess(generateNextChess(nextChess));
            System.out.println("Geração: " + g);
            printChess();
            System.out.println();

            Thread.sleep(speedGenerations);
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

}
