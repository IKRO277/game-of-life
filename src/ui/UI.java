package ui;

import entities.Chess;
import security.Security;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class UI {
    private String playerName;
    private Chess chess;
    private int rows;
    private int cols;


    public UI() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

    public void printInstructions(Scanner sc) { // imprime as instruções e regras de jogo
        //System.out.print("Por favor informe seu nome: ");
        //setPlayerName(sc.nextLine());

        System.out.println("\n-------  Game of Life  -------\n\nINSTRUÇÕES BÁSICAS:");
        System.out.println("Célula viva: \uD83D\uDFE6\nCélula morta: " + "\uD83D\uDFE5" + "\n - Você informará quais células estarão vivas e mortas");
        System.out.println("-----------------------------------------------");
        System.out.println("CONDIÇÕES GERAIS:\nSobrevivência: permanece vivo se tiver 2 ou 3 vizinhos\n" +
                "Morte: Se a célula tiver menos de 2 vizinhos(subpopulação) ou mais de 3 vizinhos(superpopulação).");
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


        String[] populationParams = args[4].split("=");
        String[] population = populationParams[1].substring(1, populationParams[1].length() - 1).split("#");

        String[] neighborhoodParams = args[5].split("=");
        int neighborhood = Integer.parseInt(neighborhoodParams[1]);

        setChess(new Chess(rows, cols, generations, speed, population, neighborhood));
        chess.generateCells(chess, neighborhood);
        chess.cellLife(5, 5);
    }

    public boolean generateChess(String[] args) throws InterruptedException {

        if(!Security.validateParams(args)) {
            return false;
        } else {
            addChessParams(args);
        }

        System.out.println("\nGerando mundo...");

        sleep(1500);
        System.out.println("Mundo gerado");

        System.out.println("-----------------------------------------------");
        chess.printChess();
        System.out.println("-----------------------------------------------\n");
        return true;
    }

    public void runChess(boolean activation) throws InterruptedException {
        if (activation) chess.executeGameGol();
        System.out.println("\nDados incompletos\n");
    }
}
