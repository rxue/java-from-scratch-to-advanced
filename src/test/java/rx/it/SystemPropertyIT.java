package rx.it;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemPropertyIT {
    @Test
    public void testSystemProperty() {
        assertEquals("test", System.getProperty("x"));
    }
}
