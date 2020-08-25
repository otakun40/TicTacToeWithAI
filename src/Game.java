import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    boolean run;
    Board board;
    Player player1;
    Player player2;


    public Game() {
        run  = true;
        board = new Board();
        initiatePlayers();
    }

    void play(Board board) {
        while (run) {
            player1.move();

            if (board.currentGameResult().equals("Draw") && board.getMovesCounter() == 9) {
                System.out.println("Draw");
                board = new Board();
            }

        }
    }

    void initiatePlayers() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: ");
            String[] input = scanner.nextLine().trim().split(" ");

            if (input.length == 1 && "exit".equals(input[0])) {
            }

            if (input.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }   else if ((!"human".equals(input[1]) && !"easy".equals(input[1]) && !"medium".equals(input[1]) &&
                        !"hard".equals(input[1])) || (!"human".equals(input[2]) && !"easy".equals(input[2]) &&
                        !"medium".equals(input[2]) && !"hard".equals(input[2]))) {
                System.out.println("Bad parameters!");
            }   else {
                switch (input[1]) {
                    case "user":
                        player1 = new Human(board, Side.X);
                        break;
                    case "easy":
                        player1 = new EasyAI(board, Side.X);
                        break;
                    case "medium":
                        player1 = new MediumAI(board, Side.X);
                        break;
                    case "hard":
                        player1 = new HardAI(board, Side.X);
                    default:break;
                }
                switch (input[2]) {
                    case "user":
                        player2 = new Human(board, Side.O);
                        break;
                    case "easy":
                        player2 = new EasyAI(board, Side.O);
                        break;
                    case "medium":
                        player2 = new MediumAI(board, Side.O);
                        break;
                    case "hard":
                        player2 = new HardAI(board, Side.O);
                    default:break;
                }
            }
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }


}
