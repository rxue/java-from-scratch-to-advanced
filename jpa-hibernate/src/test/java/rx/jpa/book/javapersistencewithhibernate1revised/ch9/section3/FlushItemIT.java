package rx.jpa.book.javapersistencewithhibernate1revised.ch9.section3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import org.junit.jupiter.api.Test;
import rx.AbstractITConfigTemplate;
import rx.jpa.book.javapersistencewithhibernate1revised.ch9.Item;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class FlushItemIT extends AbstractITConfigTemplate {
    @Test
    public void persist_thenFlush() {
        //Arrange
        Item item;
        Long databaseIdentity;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            assertSame(FlushModeType.AUTO, em.getFlushMode());
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            item = new Item();
            item.setDescription("original desc");
            em.persist(item);
            assertNotNull(item.getId());
            em.flush();
            databaseIdentity = getIdInAnotherReadUncommittedSession();
            assertNotNull(databaseIdentity);
            databaseIdentity = item.getId();
            transaction.commit();
        }
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Item foundItem = em.find(Item.class, databaseIdentity);
            assertNotNull(foundItem);
        }

    }
    private Long getIdInAnotherReadUncommittedSession() {
        try(Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/test", "root", "test")) {
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Item");
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
