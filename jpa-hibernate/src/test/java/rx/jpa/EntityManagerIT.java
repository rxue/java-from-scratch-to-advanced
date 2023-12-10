package rx.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntityManagerIT {
    private static MariaDBContainer<?> db;
    @BeforeAll
    public static void initDatabase() {
        db = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.5.8"));
        db.setPortBindings(Collections.singletonList("3307:3306"));
        db.start();

    }
    @Test
    public void test() {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-mariadb");
            EntityManager em = emf.createEntityManager()) {
            Account account = new Account();
            account.setName("test");
            em.persist(account);
            Account account2 = new Account();
            account2.setName("test2");
            assertEquals(0, account2.getId());
            em.persist(account2);
            assertEquals(1, account2.getId());
        }
    }
    @AfterAll
    public static void stopDatabase() {
        db.stop();
    }
}
