package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Field {
    private int xMoves;
    private int oMoves;
    private char[][] array;
    private Side side;

    private Player player1;
    private Player player2;

    Field () {
        this.xMoves = 0;
        this.oMoves = 0;
        this.side = Side.X;
        this.array = new char[3][3];
        for (int i = 0; i < this.array.length; i++) {
            Arrays.fill(array[i], ' ');
        }
    }

    Field (String string) {
        this.xMoves = 0;
        this.oMoves = 0;
        this.side = Side.X;
        this.array = new char[3][3];
        int count = 0;
        String upperString = string.toUpperCase();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (upperString.charAt(count) == '_') {
                    this.array[i][j] = ' ';
                }   else {
                this.array[i][j] = upperString.charAt(count);
                }
                count++;
            }
        }
    }

    public void playWithHuman () {

        String result = "";
        do {
            move();
            result = gameResult();
            if (!result.equals("")) {
                System.out.println(result);
            }
        } while (result.equals(""));
    }

    public void playWithAI () {
        printField();
        String result = "";
        do {
            if (side == Side.X) {
                move();
            }   else {
                AIEasyMove();
            }
            result = gameResult();
            if (!result.equals("")) {
                System.out.println(result);
            }
        } while (result.equals(""));
    }

    public void printField() {

        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", array[0][0], array[0][1], array[0][2]);
        System.out.printf("| %s %s %s |\n", array[1][0], array[1][1], array[1][2]);
        System.out.printf("| %s %s %s |\n", array[2][0], array[2][1], array[2][2]);
        System.out.println("---------");
    }

    private boolean isWin (char letter) {
        int winLines = 0;

        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == letter) {
                    count++;
                }
                if (j == 2 && count == 3)  {
                    winLines++;
                }
            }
            count = 0;
        }

        for (int i = 0; i < array.length; i++) {
            int count = 0;
            for (int j = 0; j < array[i].length; j++) {
                if (array[j][i] == letter) {
                    count++;
                }
                if (j == 2 && count == 3)  {
                    winLines++;
                }
            }
            count = 0;
        }

        if (array[0][0] == letter && array [1][1] == letter && array[2][2] == letter) {
            winLines++;
        }
        if (array[0][2] == letter && array[1][1] == letter && array[2][0] == letter) {
            winLines++;
        }

        if (winLines == 1) {
            return true;
        }   else {
            return false;
        }
    }

    private String gameResult () {
        if (!isWin(side.X.getSymbol()) && !isWin(side.O.getSymbol()) && xMoves + oMoves == 9) {
            return "Draw";
        }   else if (isWin(side.X.getSymbol())) {
            return "X wins";
        }   else if (isWin(side.O.getSymbol())) {
            return "O wins";
        }   else return "";

    }

    private int[] inputCoordinates () {
        Scanner sc = new Scanner(System.in);
        int[] coordinates = new int[2];
        boolean legalInput = false;
        while (!legalInput) {
            try {
                System.out.print("Enter the coordinates: ");
                String[] xy = sc.nextLine().trim().split(" ");
                coordinates[1] = Integer.parseInt(xy[0]);
                coordinates[0] = Integer.parseInt(xy[1]);

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (coordinates[0] < 0 || coordinates[0] > 3 || coordinates[1] < 1 || coordinates[1] > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (coordinates[0] == 1) {
                coordinates[0] = 2;
            } else if (coordinates[0] == 2) {
                coordinates[0] = 1;
            } else if (coordinates[0] == 3) {
                coordinates[0] = 0;
            }

            coordinates[1]--;
            legalInput = true;
        }
        return coordinates;
    }

    private void move () {
        boolean notEmpty = true;
        int[] coordinates;
        while (notEmpty) {
            coordinates = inputCoordinates();
            if (array[coordinates[0]][coordinates[1]] == ' ') {
                if (side == Side.X) {
                    array[coordinates[0]][coordinates[1]] = 'X';
                    xMoves++;
                } else {
                    array[coordinates[0]][coordinates[1]] = 'O';
                    oMoves++;
                }
                setSide();
                printField();
                notEmpty = false;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }

    private void AIEasyMove () {
        boolean notEmpty = true;
        while (notEmpty) {
            Random random = new Random();
            int xc = random.nextInt(3);
            int yc = random.nextInt(3);
            if (array[xc][yc] == ' ') {
                if (side == Side.X) {
                    array[xc][yc] = 'X';
                    xMoves++;
                }   else {
                    array[xc][yc] = 'O';
                    oMoves++;
                }
                setSide();
                System.out.println("Making move level \"easy\"");
                printField();
                notEmpty = false;
            } else {
                continue;
            }
        }
    }

    private void setSide () {
        if (xMoves -oMoves == 0) {
            side = Side.X;
        }   else if (xMoves - oMoves == 1) {
            side = Side.O;
        }
    }

    void choosePlayers() {
        Scanner scanner = new Scanner(System.in);
        String[] array;

        boolean isPlayer1Legal = false;
        boolean isPlayer2Legal = false;
        while (!(isPlayer1Legal && isPlayer2Legal)) {
            System.out.print("Input command: ");
            isPlayer1Legal = false;
            isPlayer2Legal = false;
            array = scanner.nextLine().trim().split(" ");
            if (array.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }
            switch (array[1]) {
                case "user":
                    player1 = Player.HUMAN;
                    isPlayer1Legal = true;
                    break;
                case "easy":
                    player1 = Player.AI;
                    isPlayer1Legal = true;
                    break;
                default: break;
            }
            switch (array[2]) {
                case "user":
                    player2 = Player.HUMAN;
                    isPlayer2Legal = true;
                    break;
                case "easy":
                    player2 = Player.AI;
                    isPlayer2Legal = true;
                    break;
                default: break;
            }
            if (!(isPlayer1Legal && isPlayer2Legal)) {
                System.out.println("Bad parameters!");
            }
        }
        System.out.println(player1);
        System.out.println(player2);
    }
}