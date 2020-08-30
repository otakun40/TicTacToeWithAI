import java.util.Random;

public abstract class Player {

    final protected Side playerSide;

    public Player(Side playerSide) {
        this.playerSide = playerSide;
    }

    abstract void move(Board board);

    public Side getPlayerSide() {
        return playerSide;
    }
}

class Human extends Player {

    public Human(Side playerSide) {
        super(playerSide);
    }

    @Override
    void move(Board board) {
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

    public EasyAI(Side playerSide) {
        super(playerSide);
    }

    @Override
    void move(Board board) {
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

    public MediumAI(Side playerSide) {
        super(playerSide);
    }


    @Override
    public void move(Board board) {
        int cellToPlayerWin = cellToWin(board, playerSide);
        int cellToOpponentWin = cellToWin(board, opponentSide);

        if (cellToPlayerWin != -1) {
            board.setCell(cellToPlayerWin, playerSide);
            System.out.println("Making move level \"medium\"");
            return;

        }   else if (cellToOpponentWin != -1) {
            board.setCell(cellToOpponentWin, playerSide);
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

    public int cellToWin(Board board, Side playerSide) {
        char ch = playerSide.getSymbol();
        int counter = 0;
        int emptyCellPos = -1;

        for (int i = 0; i < board.getCheckLines().length; i++) {
            for (int j = 0; j < board.getCheckLines()[i].length; j++) {

                if (board.getField()[board.getCheckLines()[i][j]] == ch) {
                    counter++;
                }

                if (board.getField()[board.getCheckLines()[i][j]] == ' ') {
                    emptyCellPos = board.getCheckLines()[i][j];
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

    public HardAI(Side playerSide) {
        super(playerSide);
    }

    @Override
    void move(Board board) {
    }
}
