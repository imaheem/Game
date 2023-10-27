import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the grid (e.g. 5 for 5x5): ");
        int size = scanner.nextInt();
        Game game = new Game(size);

        game.displayGrid();  // Display the initial state

        while (!game.isGameOver()) {
            System.out.println("Enter your move (up, down, left, right) or 'exit' to quit: ");
            String move = scanner.next();

            if (move.equals("exit")) {
                break;
            }

            game.move(move);
            if (!game.isGameOver()) {  // Check if game is still ongoing
                game.displayGrid();
            }
        }

        scanner.close();
    }
}
