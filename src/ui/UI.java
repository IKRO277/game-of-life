package ui;

import entities.Chess;

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

    public boolean validateParams(String[] args) {
        if(args == null) {
            return false;
        }

        if (args.length != 6) {
            return false;
        }

        try {
            for (int i = 0; i < args.length; i++) {
                String[] actualParam = args[i].split("=");
                String letter = actualParam[0];

                switch (i) {
                    case 0: if (!(Objects.equals(letter, "w"))) return false; break;
                    case 1: if (!(Objects.equals(letter, "h"))) return false; break;
                    case 2: if (!(Objects.equals(letter, "g"))) return false; break;
                    case 3: if (!(Objects.equals(letter, "s"))) return false; break;
                    case 4: if (!(Objects.equals(letter, "p"))) return false; break;
                    case 5: if (!(Objects.equals(letter, "n"))) return false; break;
                    default: return false;
                } // verifica se as letras presentes são as esperadas

            }

            for (int i = 0; i < args.length; i++) {
                String[] actualParam = args[i].split("=");
                if(i != 4) {
                    int integerParam = Integer.parseInt(actualParam[1]);
                    if(i != 5 && integerParam < 0) return false;
                    if (i == 5 && (integerParam < 1 || integerParam > 5)) return false;
                    if(i == 0 && integerParam != 10 && integerParam != 20 && integerParam != 40 && integerParam != 80) return false;
                    if(i == 1 && integerParam != 10 && integerParam != 20 && integerParam != 40) return false;
                    if(i == 3 && (integerParam < 300 || integerParam > 1000)) return false;
                }
                if(i == 4) {
                    String[] string = actualParam[1].substring(1, actualParam[1].length() - 1).split("#");
                    for (String s : string) {
                        String[] binaryString = s.split("");
                        for (String value : binaryString) {
                            if (Integer.parseInt(value) != 0 && Integer.parseInt(value) != 1) return false;
                        }
                    }
                }
            }

        } catch(Exception e) {
            return false;
        }

        return true;
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

        if(!validateParams(args)) {
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
