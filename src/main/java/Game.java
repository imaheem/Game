import java.util.Random;
public class Game {

    private int gridSize;
    private int playerX, playerY;
    private int treasureX, treasureY;
    private int[][] monsters;
    private boolean isGameOver = false;

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

// Let's add monsters (for simplicity, just one type of monster)
        monsters = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if ((i == playerX && j == playerY) || (i == treasureX && j == treasureY)) {
                    continue;  // Skip adding a monster at the player's or treasure's position
                }
                if (random.nextDouble() < 0.1) { // 10% chance to place a monster
                    monsters[i][j] = 1;
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
            System.out.println("You found the treasure! You win!");
            isGameOver = true;
        } else if (monsters[playerX][playerY] == 1) {
            System.out.println("A monster caught you! You lose!");
            isGameOver = true;
        } else {
            int distance = Math.abs(playerX - treasureX) + Math.abs(playerY - treasureY);
            System.out.println("You are " + distance + " steps away from the treasure.");
        }

    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void displayGrid() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (playerX == x && playerY == y) {
                    if (playerX == treasureX && playerY == treasureY) {
                        System.out.print("T ");  // T for Treasure when player is on it
                    } else {
                        System.out.print("P ");  // P for Player
                    }
                } else if (monsters[x][y] == 1) {
                    System.out.print("M ");  // M for Monster
                } else {
                    System.out.print(". ");  // . for unexplored/empty space
                }
            }
            System.out.println();
        }

    }
}
