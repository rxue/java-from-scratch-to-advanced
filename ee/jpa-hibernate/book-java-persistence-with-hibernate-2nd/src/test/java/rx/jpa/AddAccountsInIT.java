package rx.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import rx.AbstractITConfigTemplate;

import java.util.stream.IntStream;

public class AddAccountsInIT extends AbstractITConfigTemplate {
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
