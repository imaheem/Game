import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZombieTest {

    Zombie zombie;

    @BeforeEach
    public void setUp(){
        zombie = new Zombie();
    }

    @Test
    public void testZombieGreet() {
        String expectedGreeting = "The Zombie groans!";
        String actualGreeting = zombie.greet();
        assertEquals(expectedGreeting, actualGreeting);
    }
}