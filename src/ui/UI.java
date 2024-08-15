package ui;

import entities.Cell;
import entities.Chess;
import static java.lang.Thread.sleep;
import java.util.Scanner;
import security.ValidateChessParams;

public class UI {
    private Chess chess;
    private int rows, cols;

    public UI() {
    }

    public Chess getChess() {
        return chess;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
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

    public void printInstructions(Scanner sc) {

        System.out.println("\n-------  Game of Life  -------\n\nINSTRUÇÕES BÁSICAS:");
        System.out.println("""
                Célula viva: \uD83D\uDFE6
                Célula morta: \uD83D\uDFE5
                 - Você informará quais células estarão vivas e mortas""");
        System.out.println("-----------------------------------------------");
        System.out.println("""
                CONDIÇÕES GERAIS:
                Sobrevivência: permanece vivo se tiver 2 ou 3 vizinhos
                Morte: Se a célula tiver menos de 2 vizinhos(subpopulação) ou mais de 3 vizinhos(superpopulação).""");
        System.out.print("Digite qualquer coisa para continuar --> ");
        sc.nextLine();

        System.out.println("-----------------------------------------------");
    }

    public void addChessParams(String[] args) {
        String[] rowParams = args[0].split("=");
        setRows(Integer.parseInt(rowParams[1]));

        String[] colParams = args[1].split("=");
        setCols(Integer.parseInt(colParams[1]));

        String[] generationParams = args[2].split("=");
        int generations = Integer.parseInt(generationParams[1]);

        String[] speedParams = args[3].split("=");
        int speed = Integer.parseInt(speedParams[1]);

        try { createChess(addAndValidateChessParams(generations, speed, args)); } catch (Exception e) {System.out.println("Dados inválidos");}
    }

    public Chess addAndValidateChessParams(int generations, int speed, String[] args) {
        String[] population;
        String[] populationParams = args[4].split("=");

        if (populationParams.length == 2) population = populationParams[1].split("#");
        else population = new String[]{"random"};

        String[] neighborhoodParams = args[5].split("=");
        int neighborhood;
        if(neighborhoodParams.length == 2) neighborhood = Integer.parseInt(neighborhoodParams[1]);
        else neighborhood = 3;

        return new Chess(rows, cols, generations, speed, population, neighborhood);
    }

    public void createChess(Chess chess) {
        setChess(chess);
        if("random".equals(chess.getPopulationRule()[0])) chess.generateRandomCells(chess);
        else {
            chess.generateCells(chess);
            chess.cellLife();
        }
    }

    public boolean generateChess(String[] args) throws InterruptedException {
        if(!ValidateChessParams.validateParams((args))) return false;
        else addChessParams(args);

        System.out.println("\nGerando mundo...");
        sleep(1500);
        System.out.println("Mundo gerado");

        System.out.println("-----------------------------------------------");
        chess.printChess();
        System.out.println("-----------------------------------------------\n");
        return true;
    }

    public void printChess(Cell[][] chess, int rows, int cols){
        for (int i = 0; i < rows; i++){
            System.out.println("{");
            for (int j = 0; j < cols; j++) {
                if(!chess[i][j].getLife()) System.out.println("\\uD83D\\uDFE6 ");
                else System.out.println("\\uD83D\\uDFE5 ");
            }
            System.out.println("}");
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void runChess(boolean activation) throws InterruptedException {
        if (activation){
            chess.executeGameGol();
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nDeseja continuar jogando? (s/n)");
            String response = scanner.nextLine();
            if ("s".equalsIgnoreCase(response)) {
                System.out.println("Reiniciando o jogo...\n");
                runChess(true);
            } else {
                System.out.println("Encerrando o jogo.");
            }
        } else {
        System.out.println("\nDados incompletos\n");
        }
    }

}
