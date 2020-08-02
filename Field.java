package tictactoe;

import java.util.Scanner;

public class Field {
    private char[][] array;
    private int countX;
    private int countO;
    private Side side;
    private int moveCounter;

    Field () {
        this.countX = 0;
        this.countO = 0;
        this.side = Side.X;
        this.array = new char[3][3];
        for (int i = 0; i < this.array.length; i++) {
            for (int j = 0; j < this.array[i].length; j++) {
                this.array[i][j] = ' ';
            }
        }
    }

    Field (String string) {
        int count = 0;
        for (int i = 0; i < this.array.length; i++) {
            for (int j = 0; j < this.array[i].length; j++) {
                this.array[i][j] = string.charAt(count);
                count++;
            }
        }
    }

    void play () {
        print(array);
        String result = "";
       do {
           move();
           result = gameResult();
           if (!result.equals("")) {
               System.out.println(result);
           }
       } while (result.equals(""));
    }

    void print(char[][] array) {

        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", array[0][0], array[0][1], array[0][2]);
        System.out.printf("| %s %s %s |\n", array[1][0], array[1][1], array[1][2]);
        System.out.printf("| %s %s %s |\n", array[2][0], array[2][1], array[2][2]);
        System.out.println("---------");
    }

    boolean isWin (char letter) {
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

    String gameResult () {
        if (!isWin(side.X.getSymbol()) && !isWin(side.O.getSymbol()) && moveCounter == 9) {
            return "Draw";
        }   else if (isWin(side.X.getSymbol())) {
            return "X wins";
        }   else if (isWin(side.O.getSymbol())) {
            return "O wins";
        }   else return "";

    }

    int[] inputCoordinates () {
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

    void move () {
        boolean notEmpty = true;
        int[] coordinates;
        while (notEmpty) {
            coordinates = inputCoordinates();
            if (array[coordinates[0]][coordinates[1]] == ' ') {
                if (side == Side.X) {
                    array[coordinates[0]][coordinates[1]] = 'X';
                    side = Side.O;
                } else {
                    array[coordinates[0]][coordinates[1]] = 'O';
                    side = Side.X;
                }
                moveCounter++;
                print(array);
                notEmpty = false;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }

    }
}