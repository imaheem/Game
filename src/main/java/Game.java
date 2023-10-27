import java.util.Map;
import java.util.Random;
public class Game {

    private int gridSize;
    private Monster[][] monsters;
    private int playerX, playerY;
    private int treasureX, treasureY;
    private boolean isGameOver;
    private Random random = new Random();

    // Monster types and greetings
    private final int ZOMBIE = 2;
    private final int VAMPIRE = 3;
    private final int GHOST = 4;
    private final Map<Integer, String> monsterGreetings = Map.of(
            ZOMBIE, "The Zombie groans!",
            VAMPIRE, "The Vampire hisses!",
            GHOST, "The Ghost wails!"
    );

    public Game(int gridSize) {
        this.gridSize = gridSize;

        // Place player and treasure randomly
        Random random = new Random();
        // Set treasure's location
        treasureX = random.nextInt(gridSize);
        treasureY = random.nextInt(gridSize);

// Set player's initial position ensuring it's not on the treasure
        do {
            playerX = random.nextInt(gridSize);
            playerY = random.nextInt(gridSize);
        } while (playerX == treasureX && playerY == treasureY);

        monsters = new Monster[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if ((i == playerX && j == playerY) || (i == treasureX && j == treasureY)) {
                    continue;
                }
                if (random.nextDouble() < 0.1) {
                    int monsterType = random.nextInt(3);
                    switch (monsterType) {
                        case 0:
                            monsters[i][j] = new Zombie();
                            break;
                        case 1:
                            monsters[i][j] = new Vampire();
                            break;
                        case 2:
                            monsters[i][j] = new Ghost();
                            break;
                    }
                }
            }
        }
    }

    public void move(String direction) {
        switch (direction) {
            case "up":
                playerY = Math.max(0, playerY - 1);
                break;
            case "down":
                playerY = Math.min(gridSize - 1, playerY + 1);
                break;
            case "left":
                playerX = Math.max(0, playerX - 1);
                break;
            case "right":
                playerX = Math.min(gridSize - 1, playerX + 1);
                break;
        }
        if (playerX == treasureX && playerY == treasureY) {
            System.out.println("You found the treasure! You win!" );
            isGameOver = true;
        } else if (monsters[playerX][playerY] != null) {
            System.out.println(monsters[playerX][playerY].greet());
            System.out.println("A monster caught you! You lose!" );
            isGameOver = true;
        } else {
            int distance = Math.abs(playerX - treasureX) + Math.abs(playerY - treasureY);
            System.out.println("You are " + distance + " steps away from the treasure." );
        }

    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void displayGrid() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (playerX == x && playerY == y) {
                    System.out.print("P " );
                } else if (monsters[x][y] != null) {
                    System.out.print(monsters[x][y].getSymbol() + " " );
                } else {
                    System.out.print(". " );
                }
            }
            System.out.println();
        }
    }
}
