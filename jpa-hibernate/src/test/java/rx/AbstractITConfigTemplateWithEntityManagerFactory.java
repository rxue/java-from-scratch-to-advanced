package rx;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

public abstract class AbstractITConfigTemplateWithEntityManagerFactory {
    protected EntityManagerFactory entityManagerFactory;
    private MariaDBContainer<?> db;

    @BeforeEach
    public void init() {
        db = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.5.8"));
        db.setPortBindings(Collections.singletonList("3307:3306"));
        db.start();
        entityManagerFactory = Persistence.createEntityManagerFactory("test-mariadb");
    }

    @AfterEach
    public void destroy() {
        entityManagerFactory.close();
        db.stop();
    }
}
