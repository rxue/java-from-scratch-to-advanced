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
        //ACT
        item.setDescription("updated");// Detached entity instance
        Item returnedMergedItem;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            returnedMergedItem = em.merge(item);
            transaction.commit();
        }
        //ASSERT
        assertFalse(returnedMergedItem == item, "merge method should return a new instance of the given entity");
        assertEquals(item.getId(), returnedMergedItem.getId());
        assertEquals("updated", returnedMergedItem.getDescription());
    }
    @Test
    public void merge_newItemWithoutId() {
        Item item;
        Item returnedMergedItem;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            item = new Item();

            item.setDescription("original desc");
            assertNull(item.getId());
            returnedMergedItem = em.merge(item);
            transaction.commit();
        }
        //ASSERT
        Long returnedId = returnedMergedItem.getId();
        assertNotNull(returnedId);
        assertTrue(returnedId == item.getId());
        assertNotEquals(item, returnedMergedItem);
    }

    @Test
    public void merge_newItemWithId() {
        Item item = new Item();
        final Long selfDefinedId = 1000L;
        item.setId(selfDefinedId);
        item.setDescription("original desc");
        Item returnMergedItem;
        //ACT
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            returnMergedItem = em.merge(item);
            transaction.commit();

        }
        //ASSERT
        assertNotEquals(selfDefinedId, item.getId(), "predefined ID is overridden by EntityManager during merge");
        assertTrue(returnMergedItem.getId() == item.getId());
        assertNotEquals(item, returnMergedItem);
    }

}
