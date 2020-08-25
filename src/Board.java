import java.util.Arrays;
import java.util.Scanner;

public class Board {

    private int movesCounter;

    private Side side;

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

    final private int[] boardArray = new int[9];

    public Board() {
        this.side = Side.X;
        movesCounter = 0;
        Arrays.fill(boardArray, ' ');
    }

    public int getMovesCounter() {
        return movesCounter;
    }

    public Side getSide() {
        return side;
    }

    public int[][] getCheckLines() {
        return checkLines;
    }

    public void print() {
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", boardArray[0], boardArray[1], boardArray[2]);
        System.out.printf("| %s %s %s |\n", boardArray[3], boardArray[4], boardArray[5]);
        System.out.printf("| %s %s %s |\n", boardArray[6], boardArray[7], boardArray[8]);
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

            if (x < 0 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (x == 1 && y == 1) {
                coordinate = 6;
            }   else if (x == 1 && y == 2) {
                coordinate = 3;
            }   else if (x == 1 && y == 3) {
                coordinate = 0;
            }   else if (x == 2 && y == 1) {
                coordinate = 7;
            }   else if (x == 2 && y == 2) {
                coordinate = 4;
            }   else if (x == 2 && y == 3) {
                coordinate = 1;
            }   else if (x == 3 && y == 1) {
                coordinate = 8;
            }   else if (x == 3 && y == 2) {
                coordinate = 5;
            }   else if (x == 3 && y == 3) {
                coordinate = 2;
            }

            legalInput = true;
        }
        return coordinate;
    }

    public boolean isPlayerWin(Player player) {

        char ch = side.getSymbol();

        int counter = 0;

        for (int i = 0; i < checkLines.length; i++) {
            for (int j = 0; j < checkLines[i].length; j++) {
                if (boardArray[checkLines[i][j]] == ch) {
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

    public int[] getBoardArray() {
        return boardArray;
    }

    public Side getCurrentSide() {
        return side;
    }

    public String currentGameResult() {
        if (isPlayerWin(player1) || isPlayerWin(player2)) {
            return String.format("%c wins\n", side.getSymbol());
        }   else return "Draw";
    }

    public boolean isCellEmpty(int coordinate) {
        if (boardArray[coordinate] == ' ') {
            return true;
        }   else {
            return false;
        }
    }

    public void setCell(int coordinate, Side side) {
        boardArray[coordinate] = side.getSymbol();
    }
}
