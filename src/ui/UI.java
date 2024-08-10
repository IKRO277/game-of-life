package ui;

import entities.Cell;
import entities.Chess;

import java.util.InputMismatchException;
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
        System.out.print("Por favor informe seu nome: ");
        setPlayerName(sc.nextLine());

        System.out.println("\n-------  Game of Life  -------\n\nINSTRUÇÕES BÁSICAS:");
        System.out.println("Célula viva:  o\nCélula morta: -\n - Você informará quais células estarão vivas e mortas");
        System.out.println("-----------------------------------------------");
        System.out.println("CONDIÇÕES GERAIS:\nSobrevivência: permanece vivo se tiver 2 ou 3 vizinhos\n" +
                "Morte: Se a célula tiver menos de 2 vizinhos(subpopulação) ou mais de 3 vizinhos(superpopulação).");
        System.out.print("Digite qualquer coisa para continuar --> ");
        sc.nextLine();

        System.out.println("-----------------------------------------------");
    }

    public boolean validateParams(String[] args) { // caso falso, o parâmetro não passou na validação
        if(args == null) {
            return false;
        } // valida se existe parâmetro

        if (args.length < 6 || args.length > 6) {
            return false;
        } // valida se possui apenas a quantidade de dados esperados

        try {
            for (int i = 0; i < args.length; i++) {
                String actualParam[] = args[i].split("=");
                String letter = actualParam[0];

                if(!(i == 0 && Objects.equals(letter, "w") || (i == 1 && Objects.equals(letter, "h")) || (i == 2 && Objects.equals(letter, "g")) || (i == 3 && Objects.equals(letter, "s")) || (i == 4 && Objects.equals(letter, "p")) || (i == 5 && Objects.equals(letter, "n")) ) ) {
                    return false;
                }
            }

            for (int i = 0; i < args.length; i++) {
                String actualParam[] = args[i].split("=");
                if(i != 4) {
                    int integerParam = Integer.parseInt(actualParam[1]);
                    if(i != 5 && integerParam < 0) {
                        return false;
                    }
                    if (i == 5 && (integerParam < 1 || integerParam > 5)) {
                        return false;
                    }
                    if(i == 0 && integerParam != 10 && integerParam != 20 && integerParam != 40 && integerParam != 80) {
                        return false;
                    }
                    if(i == 1 && integerParam != 10 && integerParam != 20 && integerParam != 40) {
                        return false;
                    }
                    if(i == 3 && (integerParam < 300 || integerParam > 1000)) {
                        return false;
                    }
                }
                if(i == 4) {
                    String[] string = actualParam[1].substring(1, actualParam[1].length() - 1).split("#"); // Faz uma subString tirando as aspas, e depois cria uma lista de String cortando os hashtags
                    int rowParam = string[0].length(); //verifica se os as linhas de células são iguais
                    for(int j = 0; j < string.length; j++) {
                        if(string[j].length() != rowParam) {
                            return false;
                        }
                    }
                    for (int k = 0; k < string.length; k++) { // verifica se só possuem 0 e 1
                        String[] binaryString = string[k].split("");
                        for(int j = 0; j < binaryString.length; j++) {
                            if(Integer.parseInt(binaryString[j]) != 0 && Integer.parseInt(binaryString[j]) != 1) {
                                return false;
                            }
                        }
                    }
                }
            }

        } catch(Exception e) {
            return false;
        } // valida cada parãmetro um por um, verificando de a letra e o dado estão como esperados

        return true;
    }

    public void addChessParams(String[] args) {
        String rowParams[] = args[0].split("=");
        setRows(Integer.parseInt(rowParams[1]));

        String colParams[] = args[1].split("=");
        setCols(Integer.parseInt(colParams[1]));

        String generationParams[] = args[2].split("=");
        int generations = Integer.parseInt(generationParams[1]);


        String speedParams[] = args[3].split("=");
        int speed = Integer.parseInt(speedParams[1]);


        String populationParams[] = args[4].split("=");
        String[] population = populationParams[1].substring(1, populationParams[1].length() - 1).split("#");

        String neighborhoodParams[] = args[5].split("=");
        int neighborhood = Integer.parseInt(neighborhoodParams[1]);

        setChess(new Chess(rows, cols, generations, speed, population, neighborhood));
        chess.generateCells(chess, neighborhood);
    }

    public boolean generateChess(String[] args) throws InterruptedException { // filtra os parametros e instancia o mapa com todos os dados repassados

        if(validateParams(args) == false) { // caso parâmetro falso, retorna falso
            return false;
        } else { //caso parâmetro True, gera o mapa nessa função
            addChessParams(args);
        }

        System.out.println("\nGerando mundo...");

        sleep(1500);
        System.out.println("Mundo gerado");

        System.out.println("-----------------------------------------------");
        chess.printChess(); // função do próprio mapa que printa ele mesmo na tela
        System.out.println("-----------------------------------------------\n");
        return true;
    }

}
