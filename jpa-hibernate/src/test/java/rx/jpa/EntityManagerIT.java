package rx.jpa;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerIT {
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
    public void removeAndRevertByMerge() {
        save2Accounts();
        remove2ndAccount();
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            //
            Account account2 = new Account();
            final long preSetId = 100;
            account2.setId(preSetId);
            account2.setName("test2");
            transaction.begin();
            Account mergedAccount = em.merge(account2);
            transaction.commit();
            assertNotEquals(preSetId, mergedAccount.getId());
            assertNull(em.find(Account.class, 2));
            System.out.println("Merge ");
        }

    }
    @Test
    public void removeAndRevertByPersistWouldFail() {
        save2Accounts();
        remove2ndAccount();
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            //
            Account account2 = new Account();
            account2.setId(2L);
            account2.setName("test2");
            transaction.begin();
            assertThrows(EntityExistsException.class, () -> em.persist(account2));
            transaction.commit();
        }

    }

    private void remove2ndAccount() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            //
            transaction.begin();
            Account accountToRemove = em.find(Account.class, 2);
            em.remove(accountToRemove);
            transaction.commit();
            Account found = em.find(Account.class, 2);
            assertNull(found);
        }
    }

    private void save2Accounts() {
        try(EntityManager em = entityManagerFactory.createEntityManager()) {
            Account account = new Account();
            account.setName("test");
            EntityTransaction transaction = em.getTransaction();
            //
            transaction.begin();
            em.persist(account);
            Account account2 = new Account();
            account2.setName("test2");
            assertEquals(null, account2.getId());
            em.persist(account2);
            transaction.commit();
        }
    }

    @AfterEach
    public void destroy() {
        entityManagerFactory.close();
        db.stop();
    }
}
