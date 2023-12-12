package rx.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.stream.IntStream;

public class AddAccountsInIT {
    private MariaDBContainer<?> db;
    private EntityManagerFactory entityManagerFactory;
    @BeforeEach
    public void init() {
        db = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.5.8"));
        db.setPortBindings(Collections.singletonList("3307:3306"));
        db.start();
        entityManagerFactory = Persistence.createEntityManagerFactory("test-mariadb");
    }
    @Test
    public void addBatch() {
        IntStream.range(0,150).forEach(i -> {
            try (EntityManager em = entityManagerFactory.createEntityManager()) {
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                Account account = new Account();
                account.setName("test" + i);
                em.persist(account);
                transaction.commit();
            }
        });

    }
    @AfterEach
    public void destroy() {
        entityManagerFactory.close();
        db.stop();
    }
}
