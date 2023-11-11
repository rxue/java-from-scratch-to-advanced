package rx.it;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static rx.it.Database.db;

public class UseTestContainerIT {
    @BeforeAll
    public static void initializeDabase() {
    }
    @AfterAll
    public static void stopDatabase() {
       db.stop();
    }
    @Test
    public void test() {
        assertTrue(true);
        //assertNotNull(db);.lambda$null$0
        //System.out.println(db.getDatabaseName());
        //System.out.println(db.getJdbcUrl());
        //System.out.println("Mapped Port: " + db.getMappedPort(3306));
    }
}
