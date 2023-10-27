import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GhostTest {

    Ghost ghost;

    @BeforeEach
    public void setUp(){
        ghost = new Ghost();
    }

    @Test
    public void testGhostGreet() {
        Monster ghost = new Ghost();
        String expectedGreeting = "The Ghost wails!";
        String actualGreeting = ghost.greet();
        assertEquals(expectedGreeting, actualGreeting);
    }
}