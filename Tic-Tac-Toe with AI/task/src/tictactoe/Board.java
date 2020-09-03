package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

    private Side turn;

    final private char[] field = new char[9];

    final int[][] checkLines = {
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    public Board() {
        this.turn = Side.X;
        Arrays.fill(field, ' ');
    }

    public int[][] getCheckLines() {
        return checkLines;
    }

    public void print() {
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", field[0], field[1], field[2]);
        System.out.printf("| %c %c %c |\n", field[3], field[4], field[5]);
        System.out.printf("| %c %c %c |\n", field[6], field[7], field[8]);
        System.out.println("---------");
    }

    public int inputCoordinates() {
        Scanner sc = new Scanner(System.in);
        boolean legalInput = false;
        int coordinate = -1;
        int x;
        int y;
        while (!legalInput) {
            try {
                System.out.print("Enter the coordinates: ");
                String[] xy = sc.nextLine().trim().split(" ");

                if (xy.length != 2) {
                    System.out.println("You should enter two numbers!");
                    continue;
                }

                x = Integer.parseInt(xy[0]);
                y = Integer.parseInt(xy[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (x == 1 && y == 1) {
                coordinate = 6;
            } else if (x == 1 && y == 2) {
                coordinate = 3;
            } else if (x == 1 && y == 3) {
                coordinate = 0;
            } else if (x == 2 && y == 1) {
                coordinate = 7;
            } else if (x == 2 && y == 2) {
                coordinate = 4;
            } else if (x == 2 && y == 3) {
                coordinate = 1;
            } else if (x == 3 && y == 1) {
                coordinate = 8;
            } else if (x == 3 && y == 2) {
                coordinate = 5;
            } else if (x == 3 && y == 3) {
                coordinate = 2;
            }
            legalInput = true;
        }
        return coordinate;
    }

    public char[] getField() {
        return field;
    }

    public Side getCurrentTurn() {
        return turn;
    }

    public boolean isCellEmpty(int coordinate) {
        return field[coordinate] == ' ';
    }

    public void setCell(int coordinate, Side side) {
        field[coordinate] = side.getSymbol();
    }

    public void nextTurn() {
        turn = turn == Side.X ? Side.O : Side.X;
    }

    public ArrayList<Integer> getEmptySpacePositions() {

        ArrayList<Integer> emptySpaces = new ArrayList<>();

        for (int i = 0; i < field.length; i++) {
            if (field[i] == ' ') {
                emptySpaces.add(i);
            }
        }

        return emptySpaces;
    }


}
