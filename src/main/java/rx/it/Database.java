package rx.it;

import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

public class Database {
    public static MariaDBContainer<?> db;
    static void start() {
        db = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.5.8"));
        db.setPortBindings(Collections.singletonList("3307:3306"));
        System.out.println("going to start db");
        db.start();
    }
    static void stop() {
        db.stop();
    }

    public static void main(String[] args) {
        final String command = args[0];
        if ("start".equals(command)) {
            start();
        } else if ("stop".equals(command)) {
            stop();
        }
    }
}
