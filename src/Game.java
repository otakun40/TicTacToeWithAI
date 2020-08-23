import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    Board board = new Board();

    class Board {

        private int movesCounter;

        private Side side;

        final private int[] boardArray = new int[9];

        public Board() {
            this.side = Side.X;
            movesCounter = 0;
            Arrays.fill(boardArray, ' ');
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
            if ((boardArray[0] == ch && boardArray[1] == ch && boardArray[2] == ch) ||
                    (boardArray[3] == ch && boardArray[4] == ch && boardArray[5] == ch) ||
                    (boardArray[6] == ch && boardArray[7] == ch && boardArray[8] == ch) ||
                    (boardArray[0] == ch && boardArray[3] == ch && boardArray[6] == ch) ||
                    (boardArray[1] == ch && boardArray[4] == ch && boardArray[7] == ch) ||
                    (boardArray[2] == ch && boardArray[5] == ch && boardArray[8] == ch) ||
                    (boardArray[0] == ch && boardArray[4] == ch && boardArray[8] == ch) ||
                    (boardArray[2] == ch && boardArray[4] == ch && boardArray[6] == ch)
            )   {
                return true;
            }   else {
                return false;
            }
        }

        public int[] getBoardArray() {
            return boardArray.clone();
        }

        public Side getSide() {
            return side;
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

    abstract class Player {

        final protected Side side;

        public Player(Side side) {
            this.side = side;
        }

        abstract void move();
    }

    class Human extends Player {

        public Human(Side side) {
            super(side);
        }

        @Override
        void move() {
            int c;
            while (true) {
                c = board.inputCoordinates();
                if (board.isCellEmpty(c)) {
                    board.setCell(c, side);
                    break;
                }   else System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }

    class EasyAI extends Player {

        public EasyAI(Side side) {
            super(side);
        }

        @Override
        void move() {
            Random random = new Random();
            while (true) {
                int c = random.nextInt(9);
                if (board.isCellEmpty(c)) {
                    board.setCell(c, side);
                    break;
                }
            }
        }
    }

    class MediumAI extends Player{

        public MediumAI(Side side) {
            super(side);
        }

        @Override
        void move() {

        }
    }

    class HardAI extends Player{

        public HardAI(Side side) {
            super(side);
        }

        @Override
        void move() {

        }
    }

    enum Side {
        X('x'),
        O('o');

        private char symbol;

        Side(char o) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }
}
