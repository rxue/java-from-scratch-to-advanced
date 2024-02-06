package rx.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import rx.AbstractITConfigTemplate;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class AddAccountsInIT extends AbstractITConfigTemplate {

    @Test
    public void persistAccountWithRandomId() {
        final Long randomId = 5001L;
        Account account = new Account();
        account.setId(randomId);
        account.setName("test");
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(account);
            transaction.commit();
        }
        assertNotEquals(randomId, account.getId());
    }

    @Test
    public void addAccountWithRandomId() {
        Account detachedAccount;
        Account mergedAccount;
        final Long randomId = 5000L;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                detachedAccount = new Account();
                detachedAccount.setId(randomId);
                detachedAccount.setName("test");
                mergedAccount = em.merge(detachedAccount);
                transaction.commit();
        }
        final Account detached = detachedAccount;
        final Account merged = mergedAccount;
        assertAll("both detached and merged entity instances have the same ID generated by database other than the assigned randome ID",
                () -> assertNotEquals(randomId, detached.getId()),
                () -> assertNotEquals(randomId, merged.getId())
        );
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
}
