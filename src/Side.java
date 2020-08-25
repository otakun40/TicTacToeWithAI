public enum Side {
    X('X'),
    O('O');

    private char symbol;

    Side(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
