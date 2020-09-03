package tictactoe;

import java.util.Scanner;

public class Game {

    private boolean run;
    private Player player1;
    private Player player2;
    private final Board board;
    private int movesCounter;


    public Game() {
        run = true;
        board = new Board();
        initializePlayers();
        movesCounter = 0;
    }

    public void play() {

        String gameResult;
        board.print();

        while (run) {
            if (board.getCurrentTurn() == Side.X) {
                player1.move(board);
            } else {
                player2.move(board);
            }

            gameResult = currentGameResult();

            board.nextTurn();

            movesCounter++;

            board.print();

            if ("No one".equals(gameResult) && movesCounter == 9) {
                System.out.println("Draw\n");
                return;
            } else if (!"No one".equals(gameResult)) {
                System.out.println(gameResult + "\n");
                return;
            }
        }
    }

    public void initializePlayers() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: ");
            String[] input = scanner.nextLine().trim().split(" ");

            if (input[0].equals("exit")) {
                run = false;
                return;
            }

            for (int i = 1; i < input.length; i++) {
                switch (input[i]) {
                    case "user":
                        if (i == 1) {
                            player1 = new Human(Side.X);
                        } else {
                            player2 = new Human(Side.O);
                        }
                        break;
                    case "easy":
                        if (i == 1) {
                            player1 = new EasyAI(Side.X);
                        } else {
                            player2 = new EasyAI(Side.O);
                        }
                        break;
                    case "medium":
                        if (i == 1) {
                            player1 = new MediumAI(Side.X);
                        } else {
                            player2 = new MediumAI(Side.O);
                        }
                        break;
                    case "hard":
                        if (i == 1) {
                            player1 = new HardAI(Side.X);
                        } else {
                            player2 = new HardAI(Side.O);
                        }
                        break;
                    default:
                        System.out.println("Bad parameters!");
                        break;
                }
                if (i == 2) {
                    return;
                }
            }
        }
    }

    public String currentGameResult() {
        if (isPlayerWin(player1) || isPlayerWin(player2)) {
            return String.format("%c wins\n", board.getCurrentTurn().getSymbol());
        } else {
            return "No one";
        }
    }

    public boolean isPlayerWin(Player player) {

        int counter = 0;

        for (int i = 0; i < board.getCheckLines().length; i++) {
            for (int j = 0; j < board.getCheckLines()[i].length; j++) {
                if (board.getField()[board.getCheckLines()[i][j]] == player.getPlayerSide().getSymbol()) {
                    counter++;
                }
                if (counter == 3) {
                    return true;
                }
            }
            counter = 0;
        }
        return false;
    }

    public boolean isRun() {
        return run;
    }
}
