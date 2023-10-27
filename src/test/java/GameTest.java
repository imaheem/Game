import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        int gridSize = 5;
        game = new Game(gridSize);
        // Set known initial positions for testing.
        game.setPlayerPosition(0, 0);
        game.setTreasurePosition(1, 1);
    }

    @Test
    public void testInitialization() {
        assertNotNull(game);
    }

    @Test
    public void testPlayerStartsAtValidLocation() {
        assertEquals(0, game.getPlayerX());
        assertEquals(0, game.getPlayerY());
    }

    @Test
    public void testTreasureAtValidLocation() {
        assertEquals(1, game.getTreasureX());
        assertEquals(1, game.getTreasureY());
    }

    @Test
    public void testPlayerAndTreasureDoNotStartAtSameLocation() {
        assertNotEquals(game.getPlayerX(), game.getTreasureX());
        assertNotEquals(game.getPlayerY(), game.getTreasureY());
    }

    @Test
    public void testMonstersInitialization() {
        for (int i = 0; i < game.getGridSize(); i++) {
            for (int j = 0; j < game.getGridSize(); j++) {
                if ((i == game.getPlayerX() && j == game.getPlayerY()) ||
                        (i == game.getTreasureX() && j == game.getTreasureY())) {
                    assertNull(game.getMonsterAtPosition(i, j), "Monster overlaps with player or treasure at (" + i + "," + j + ")");
                }
            }
        }
    }

    @Test
    public void testMoveUp() {
        game.setPlayerPosition(2, 2); // Set player's initial position
        game.setTreasurePosition(4, 4); // Set treasure's position

        int initialPlayerY = game.getPlayerY();

        game.move("up");
        int newPlayerY = game.getPlayerY();
        int deltaY = Math.abs(initialPlayerY - newPlayerY);

        assertTrue(deltaY <= 1, "Player's Y position has changed incorrectly after move: up");
    }

    @Test
    public void testMoveDown() {
        int initialPlayerY = game.getPlayerY();
        game.move("down");
        assertEquals(initialPlayerY + 1, game.getPlayerY());
    }

    @Test
    public void testMoveLeft() {
        game.setPlayerPosition(2, 2); // Set player's initial position
        game.setTreasurePosition(4, 4); // Set treasure's position

        int initialPlayerX = game.getPlayerX();

        game.move("left");
        int newPlayerX = game.getPlayerX();
        int deltaX = Math.abs(initialPlayerX - newPlayerX);

        assertTrue(deltaX <= 1, "Player's X position has changed incorrectly after move: left");
    }

    @Test
    public void testBoundaryUp() {
        while (game.getPlayerY() > 0) {
            game.move("up");
        }
        int yPos = game.getPlayerY();
        game.move("up");
        assertEquals(yPos, game.getPlayerY(), "Player moved out of bounds!");
    }

    @Test
    public void testBoundaryDown() {
        while (game.getPlayerY() < game.getGridSize() - 1) {
            game.move("down");
        }
        int yPos = game.getPlayerY();
        game.move("down");
        assertEquals(yPos, game.getPlayerY(), "Player moved out of bounds!");
    }

    @Test
    public void testBoundaryLeft() {
        while (game.getPlayerX() > 0) {
            game.move("left");
        }
        int xPos = game.getPlayerX();
        game.move("left");
        assertEquals(xPos, game.getPlayerX(), "Player moved out of bounds!");
    }

    @Test
    public void testBoundaryRight() {
        while (game.getPlayerX() < game.getGridSize() - 1) {
            game.move("right");
        }
        int xPos = game.getPlayerX();
        game.move("right");
        assertEquals(xPos, game.getPlayerX(), "Player moved out of bounds!");
    }

    @Test
    public void testFindTreasure() {
        while (!game.isGameOver()) {
            if (game.getPlayerX() < game.getTreasureX()) {
                game.move("right");
            } else if (game.getPlayerX() > game.getTreasureX()) {
                game.move("left");
            } else if (game.getPlayerY() < game.getTreasureY()) {
                game.move("down");
            } else if (game.getPlayerY() > game.getTreasureY()) {
                game.move("up");
            }
        }
        assertTrue(game.isGameOver(), "Game should end when player finds the treasure.");
    }

    @Test
    public void testMonsterCatch() {
        Monster mockMonster = mock(Monster.class);
        game.setMonsterAtPosition(game.getPlayerX(), game.getPlayerY() + 1, mockMonster);
        game.move("down");
        assertTrue(game.isGameOver(), "Game should end when a monster catches the player.");
    }

    @Test
    public void testCalculateDistanceToTreasure() {
        // Set the player's position
        game.setPlayerPosition(2, 2);

        // Set the treasure's position
        game.setTreasurePosition(4, 4);

        // Calculate the distance to the treasure
        int distance = game.calculateDistanceToTreasure();

        // The expected distance is 4 (2 steps right, 2 steps down)
        assertEquals(4, distance);
    }

    @Test
    public void testMonsterGreeting() {
        int newY = game.getPlayerY() + 1;
        if (newY >= game.getGridSize()) {
            newY = game.getPlayerY() - 1;
        }

        Monster mockMonster = mock(Monster.class);
        game.setMonsterAtPosition(game.getPlayerX(), newY, mockMonster);

        if (newY < game.getPlayerY()) {
            game.move("up");
        } else {
            game.move("down");
        }

        assertTrue(game.isGameOver(), "Expected a greeting when the player moves onto a monster's location.");
    }
}
