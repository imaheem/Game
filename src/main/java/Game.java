import java.util.Map;
import java.util.Random;

public class Game {

    private int gridSize;
    private Monster[][] monsters;
    private int playerX;
    private int playerY;
    private int treasureX, treasureY;
    private boolean isGameOver;
    private Random random = new Random();

    public Game(int gridSize) {
        this.gridSize = gridSize;

        // Place player and treasure randomly
        setPlayerAndTreasurePositions();

        monsters = new Monster[gridSize][gridSize];
        initializeMonsters();
    }

    private void setPlayerAndTreasurePositions() {
        // Set treasure's location
        treasureX = random.nextInt(gridSize);
        treasureY = random.nextInt(gridSize);

        // Set player's initial position ensuring it's not on the treasure
        do {
            playerX = random.nextInt(gridSize);
            playerY = random.nextInt(gridSize);
        } while (playerX == treasureX && playerY == treasureY);
    }

    void initializeMonsters() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (i == playerX && j == playerY) {
                    continue; // Skip player's cell
                }
                if (i == treasureX && j == treasureY) {
                    continue; // Skip treasure's cell
                }
                if (monsters[i][j] != null) {
                    continue; // Skip cells that already have monsters
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
            System.out.println("You found the treasure! You win!");
            isGameOver = true;
        } else if (monsters[playerX][playerY] != null) {
            System.out.println(monsters[playerX][playerY].greet());
            System.out.println("A monster caught you! You lose!");
            isGameOver = true;
        } else {
            int distance = calculateDistanceToTreasure();
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
                    System.out.print("P ");
                } else if (monsters[x][y] != null) {
                    System.out.print(monsters[x][y].getSymbol() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getTreasureX() {
        return treasureX;
    }

    public int getTreasureY() {
        return treasureY;
    }

    public void setMonsterAtPosition(int x, int y, Monster monster) {
        if (isValidPosition(x, y)) {
            monsters[x][y] = monster;
        } else {
            throw new IllegalArgumentException("Invalid coordinates: (" + x + "," + y + ")");
        }
    }

    public Monster getMonsterAtPosition(int x, int y) {
        if (isValidPosition(x, y)) {
            return monsters[x][y];
        } else {
            throw new IllegalArgumentException("Invalid coordinates: (" + x + "," + y + ")");
        }
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setPlayerPosition(int x, int y) {
        if (isValidPosition(x, y)) {
            playerX = x;
            playerY = y;
        } else {
            throw new IllegalArgumentException("Invalid player position: (" + x + "," + y + ")");
        }
    }

    public void setTreasurePosition(int x, int y) {
        if (isValidPosition(x, y)) {
            treasureX = x;
            treasureY = y;
        } else {
            throw new IllegalArgumentException("Invalid treasure position: (" + x + "," + y + ")");
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }

    // Method to calculate the Manhattan distance to the treasure
    public int calculateDistanceToTreasure() {
        // Calculate the Manhattan distance
        int distanceX = Math.abs(playerX - treasureX);
        int distanceY = Math.abs(playerY - treasureY);
        return distanceX + distanceY;
    }
}