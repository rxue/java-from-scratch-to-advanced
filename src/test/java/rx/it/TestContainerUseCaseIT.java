package rx.it;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestContainerUseCaseIT {
    private static MariaDBContainer<?> db;
    @BeforeAll
    public static void initializeDabase() {
        db = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.5.8"));
        db.setPortBindings(Collections.singletonList("3307:3306"));
        db.start();
    }
    @AfterAll
    public static void stopDatabase() {
       db.stop();
    }
    @Test
    public void test() {
        assertNotNull(db);
        System.out.println(db.getDatabaseName());
        System.out.println(db.getJdbcUrl());
        System.out.println("Mapped Port: " + db.getMappedPort(3306));
    }
}
