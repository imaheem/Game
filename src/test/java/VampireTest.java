import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VampireTest {
    Vampire vampire;

    @BeforeEach
    public void setUp(){
        vampire = new Vampire();
    }

    @Test
    public void testVampireGreet() {
        String expectedGreeting = "The Vampire hisses!";
        String actualGreeting = vampire.greet();
        assertEquals(expectedGreeting, actualGreeting);
    }
}