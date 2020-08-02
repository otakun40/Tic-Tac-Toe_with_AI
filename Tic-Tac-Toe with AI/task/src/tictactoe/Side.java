package tictactoe;

public enum Side {
    X('X'),
    O('O');

    private char symbol;
    private String wins;

    Side(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
