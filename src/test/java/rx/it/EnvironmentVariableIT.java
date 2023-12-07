package rx.it;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnvironmentVariableIT {
    @Test
    public void testEnvrionmentVariable() {
        assertEquals("test", System.getenv("environmentVar"));
    }

    @Test
    public void testEnvrionmentVariableInExecution() {
        assertEquals("test", System.getenv("environmentVarInFailSafeITExecution"));
    }
}
