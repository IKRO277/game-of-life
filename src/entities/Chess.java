package entities;

public class Chess {
    private int rows;
    private int cols;
    private int generations;
    private int speedGenerations;
    private String[] populationRule;
    private int quantityGerations;
    private Cell[][] chess;
    private int neigh;

    // Construtor da classe Chess que inicializa o tabuleiro com as dimensões e parâmetros fornecidos
    public Chess(int rows, int cols, int generations, int speedGenerations, String[] populationRule, int neighborhood) {
        this.rows = rows;
        this.cols = cols;
        this.generations = generations;
        this.speedGenerations = speedGenerations;
        this.populationRule = populationRule;
        this.chess = new Cell[rows][cols];
        generateCells(this, neighborhood); // Gera as células iniciais no tabuleiro
    }

    // Método getter para obter o tabuleiro de células
    public Cell[][] getChess() {
        return chess;
    }

    // Método setter para definir o tabuleiro de células
    public void setChess(Cell[][] chess) {
        this.chess = chess;
    }

    // Método getter para obter a quantidade de gerações
    public int getQuantityGerations() {
        return quantityGerations;
    }

    // Método setter para definir a quantidade de gerações
    public void setQuantityGerations(int quantityGerations) {
        this.quantityGerations = quantityGerations;
    }

    // Método getter para obter a regra de população
    public String[] getPopulationRule() {
        return populationRule;
    }

    // Método setter para definir a regra de população
    public void setPopulationRule(String[] populationRule) {
        this.populationRule = populationRule;
    }

    // Método getter para obter a velocidade das gerações
    public int getSpeedGenerations() {
        return speedGenerations;
    }

    // Método setter para definir a velocidade das gerações
    public void setSpeedGenerations(int speedGenerations) {
        this.speedGenerations = speedGenerations;
    }

    // Método getter para obter o número de gerações
    public int getGenerations() {
        return generations;
    }

    // Método setter para definir o número de gerações
    public void setGenerations(int generations) {
        this.generations = generations;
    }

    // Método getter para obter o número de colunas do tabuleiro
    public int getCols() {
        return cols;
    }

    // Método setter para definir o número de colunas do tabuleiro
    public void setCols(int cols) {
        this.cols = cols;
    }

    // Método getter para obter o número de linhas do tabuleiro
    public int getRows() {
        return rows;
    }

    // Método setter para definir o número de linhas do tabuleiro
    public void setRows(int rows) {
        this.rows = rows;
    }

    // Método para definir uma célula específica no tabuleiro
    public void setCell(int row, int col, Cell cell) {
        chess[row][col] = cell;
    }

    // Método para gerar as células iniciais do tabuleiro
    public Chess generateCells(Chess chess, int neighborhood) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Inicializa cada célula com o estado de "morto" e define suas coordenadas e vizinhança
                chess.setCell(i, j, new Cell(false, i, j, neighborhood));
            }
        }
        return chess;
    }

    // Método para imprimir o tabuleiro de células no console
    public void printChess()  {
        for (int i = 0; i < rows; i++) {
            System.out.print("{ ");
            for (int j = 0; j < cols; j++) {
                // Imprime "F" para células mortas e "T" para células vivas
                if(chess[i][j].getLife() == false) {
                    System.out.print("\uD83D\uDFE6 "); //Quadrado AZUL
                } else {
                    System.out.print("\uD83D\uDFE5 "); //Quadrado VERMELHO
                }
            }
            System.out.println("}");
        }
    }

    public void cellLife(int xCell, int yCell) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
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

    public void execGame (int neighborhood) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                chess[i][j].setNeighborhood(neighborhood);
            }
        }
    }

    public void executeGameGol() throws InterruptedException {
        execGame(this.neigh);
        for (int g = 0; g < generations; g++) {
            if (this.neigh == 1){
                for (int i = 0; i < chess.length; i++) {
                    for (int j = 0; j < chess[i].length; j++) {
                        chess[i][j].cellNeighborhoodOne();
                    }
                }
            } else if (this.neigh == 2) {
                for (int i = 0; i < chess.length; i++) {
                    for (int j = 0; j < chess[i].length; j++) {
                        chess[i][j].cellNeighborhoodTwo();
                    }
                }
            }
            System.out.println(getGenerations());
            printChess();
            System.out.println();
            Thread.sleep(speedGenerations);
        }
    }
}

