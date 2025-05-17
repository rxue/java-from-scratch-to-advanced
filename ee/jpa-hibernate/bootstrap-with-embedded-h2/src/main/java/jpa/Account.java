package jpa;

import jakarta.persistence.*;


@Entity
public class Account {

    private Long id;

    //@SequenceGenerator(name="account_sequence_generator", sequenceName = "account_seq")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence_generator")
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
