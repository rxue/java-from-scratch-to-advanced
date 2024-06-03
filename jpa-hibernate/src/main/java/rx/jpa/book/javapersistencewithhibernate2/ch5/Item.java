package rx.jpa.book.javapersistencewithhibernate2.ch5;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;

@Entity
public class Item {
    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount amount;
}
