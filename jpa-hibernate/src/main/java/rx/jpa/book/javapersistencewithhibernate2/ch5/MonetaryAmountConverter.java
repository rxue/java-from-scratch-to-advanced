package rx.jpa.book.javapersistencewithhibernate2.ch5;

import jakarta.persistence.AttributeConverter;

public class MonetaryAmountConverter implements AttributeConverter {
    @Override
    public Object convertToDatabaseColumn(Object o) {
        return null;
    }

    @Override
    public Object convertToEntityAttribute(Object o) {
        return null;
    }
}
