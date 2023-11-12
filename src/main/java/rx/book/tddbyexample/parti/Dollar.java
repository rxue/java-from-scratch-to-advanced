package rx.book.tddbyexample.parti;

public class Dollar {
    int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public void times(int i) {
        amount *= i;
    }
}
