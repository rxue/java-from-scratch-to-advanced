package rx.jpa;

import jakarta.persistence.*;


@Entity
public class Account {

    private long id;
    private String name;

    @SequenceGenerator(name="account_sequence_generator", sequenceName = "account_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence_generator")
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
