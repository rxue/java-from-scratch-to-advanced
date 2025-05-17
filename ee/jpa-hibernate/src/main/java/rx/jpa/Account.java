package rx.jpa;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class Account {

    private Long id;
    private String name;

    @SequenceGenerator(name="account_sequence_generator", sequenceName = "account_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence_generator")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
