package rx.jpa;

import jakarta.persistence.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateWithEntityManagerIT {
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
    public void update() {
        saveAccount();
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            //
            Account account2 = new Account();
            account2.setId(1L);
            account2.setName("test2");
            transaction.begin();
            Account mergedAccount = em.merge(account2);
            transaction.commit();
            assertEquals(1L, mergedAccount.getId());
        }

    }

    private void saveAccount() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            Account account = new Account();
            account.setName("test");
            EntityTransaction transaction = em.getTransaction();
            //
            transaction.begin();
            em.persist(account);
            transaction.commit();
        }
    }

    @AfterEach
    public void destroy() {
        entityManagerFactory.close();
        db.stop();
    }
}
