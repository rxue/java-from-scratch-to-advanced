package rx.book.javapersistencewithhibernate1revised.ch9;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import rx.AbstractITConfigTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class MergeItemIT extends AbstractITConfigTemplate {
    @Test
    public void merge_itemExisted() {
        //Arrange
        Item item;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            item = new Item();
            item.setDescription("original desc");
            em.persist(item);
            transaction.commit();
        }
        //
        item.setDescription("update");// Detached entity instance
        //
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Item mergedItem = em.merge(item);
            transaction.commit();
            assertFalse(mergedItem == item);
            assertEquals(item.getId(), mergedItem.getId());
        }
    }
    @Test
    public void merge_newItemWithoutId() {
        Item item;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            item = new Item();

            item.setDescription("original desc");
            assertNull(item.getId());
            Item mergedItem = em.merge(item);
            transaction.commit();
            assertNotNull(mergedItem.getId());
            assertNotNull(item.getId());
            assertNotEquals(item, mergedItem);
        }
    }

    @Test
    public void merge_newItemWithId() {
        Item item;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            item = new Item();
            item.setId(1000L);
            item.setDescription("original desc");
            Item mergedItem = em.merge(item);
            transaction.commit();
            assertNotEquals(1000L, item.getId());
            assertTrue(mergedItem.getId() == item.getId());
            assertNotEquals(item, mergedItem);
        }
    }

}
