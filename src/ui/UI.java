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

    // Construtor padrão da classe UI
    public UI(

    ) {
    }

    // Método getter para obter o nome do jogador
    public String getPlayerName() {
        return playerName;
    }

    // Método setter para definir o nome do jogador
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Método getter para obter a instância do objeto Chess
    public Chess getChess() {
        return chess;
    }

    // Método setter para definir a instância do objeto Chess
    public void setChess(Chess chess) {
        this.chess = chess;
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

    // Método para imprimir as instruções do jogo e coletar o nome do jogador
    public void printInstructions(Scanner sc) {
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

    // Método para validar os parâmetros de entrada fornecidos ao jogo
    public boolean validateParams(String[] args) {
        // Verifica se os parâmetros são nulos
        if(args == null) {
            return false;
        }

        // Verifica se o número de parâmetros é exatamente 6
        if (args.length < 6 || args.length > 6) {
            return false;
        }

        try {
            // Verifica se os parâmetros possuem as letras corretas (w, h, g, s, p, n)
            for (int i = 0; i < args.length; i++) {
                String actualParam[] = args[i].split("=");
                String letter = actualParam[0];

                if(!(i == 0 && Objects.equals(letter, "w") ||
                        (i == 1 && Objects.equals(letter, "h")) ||
                        (i == 2 && Objects.equals(letter, "g")) ||
                        (i == 3 && Objects.equals(letter, "s")) ||
                        (i == 4 && Objects.equals(letter, "p")) ||
                        (i == 5 && Objects.equals(letter, "n")) ) ) {
                    return false;
                }
            }

            // Valida os valores de cada parâmetro conforme regras específicas
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
                    int rowParam = string[0].length(); //verifica se as linhas de células são iguais
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
        }

        return true;
    }

    // Método para adicionar os parâmetros validados ao objeto Chess
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

    // Método principal para gerar o tabuleiro de xadrez a partir dos parâmetros
    public boolean generateChess(String[] args) throws InterruptedException {
        // Verifica se os parâmetros são válidos
        if(validateParams(args) == false) {
            return false;
        } else {
            // Se válidos, adiciona os parâmetros ao objeto Chess
            addChessParams(args);
        }

        // Simula a geração do mundo com um tempo de espera
        System.out.println("\nGerando mundo...");
        System.out.println("Mundo gerado");

        // Imprime o tabuleiro gerado
        System.out.println("-----------------------------------------------");
        chess.executeGameGol();
        System.out.println("-----------------------------------------------\n");
        return true;
    }
}
