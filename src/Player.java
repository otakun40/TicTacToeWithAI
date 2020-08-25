import java.util.Random;

public abstract class Player {

    final protected Board board;

    final protected Side playerSide;

    final protected int[][] checkLines = {
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    public Player(Board board, Side playerSide) {
        this.board = board;
        this.playerSide = playerSide;
    }


    abstract void move();
}

class Human extends Player {

    public Human(Board board, Side playerSide) {
        super(board, playerSide);
    }

    @Override
    void move() {
        int c;
        while (true) {
            c = board.inputCoordinates();
            if (board.isCellEmpty(c)) {
                board.setCell(c, playerSide);
                break;
            }   else System.out.println("This cell is occupied! Choose another one!");
        }
    }
}

class EasyAI extends Player {

    public EasyAI(Board board, Side playerSide) {
        super(board, playerSide);
    }

    @Override
    void move() {
        Random random = new Random();
        while (true) {
            int c = random.nextInt(9);
            if (board.isCellEmpty(c)) {
                board.setCell(c, playerSide);
                System.out.println("Making move level \"easy\"");
                break;
            }
        }
    }
}

class MediumAI extends Player{

    Side opponentSide = playerSide == Side.X? Side.O : Side.X;

    public MediumAI(Board board, Side playerSide) {
        super(board, playerSide);
    }


    @Override
    public void move() {
        int cellToPlayerWin = cellToWin(playerSide);
        int cellToOpponentWin = cellToWin(opponentSide);

        if (cellToPlayerWin != -1) {
            board.setCell(cellToPlayerWin, playerSide);
            System.out.println("Making move level \"medium\"");
            return;

        }   else if (cellToOpponentWin != -1) {
            board.setCell(cellToOpponentWin, opponentSide);
            System.out.println("Making move level \"medium\"");
            return;

        }

        Random random = new Random();
        while (true) {
            int c = random.nextInt(9);
            if (board.isCellEmpty(c)) {
                board.setCell(c, playerSide);
                System.out.println("Making move level \"medium\"");
                break;
            }
        }
    }

    private int cellToWin(Side playerSide) {
        char ch = playerSide.getSymbol();
        int counter = 0;
        int emptyCellPos = -1;

        for (int i = 0; i < board.checkLines.length; i++) {
            for (int j = 0; j < board.checkLines[i].length; j++) {

                if (board.getBoardArray()[board.checkLines[i][j]] == ch) {
                    counter++;
                }

                if (board.getBoardArray()[board.checkLines[i][j]] == ' ') {
                    emptyCellPos = board.checkLines[i][j];
                }

                if (j == 2 && counter == 2 && emptyCellPos != -1) {
                    return emptyCellPos;
                }
            }
            counter = 0;
            emptyCellPos = -1;
        }
        return -1;
    }
}

class HardAI extends Player {

    public HardAI(Board board, Side playerSide) {
        super(board, playerSide);
    }

    @Override
    void move() {
    }
}
