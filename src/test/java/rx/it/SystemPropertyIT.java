package rx.it;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemPropertyIT {
    @Test
    public void testFailSafeSystemProperty() {
        assertEquals("test2152", System.getProperty("propertyInFailSafe"));
    }
    @Test
    public void testFailSafeSystemProperty2() {
        assertEquals("test12070753", System.getProperty("propertyInFailSafe2"));
    }

    @Test
    public void testSystemPropertyInExecution() {
        assertEquals("test12070745", System.getProperty("propertyInFailSafeITExecution"));
    }
}
