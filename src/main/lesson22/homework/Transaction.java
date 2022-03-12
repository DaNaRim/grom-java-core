package main.lesson22.homework;

import java.util.Date;
import java.util.Objects;

public class Transaction {

    private long id;
    private String city;
    private int amount;
    private String description;
    private TransactionType type;
    private Date dateCreated;

    public Transaction(long id, String city, int amount, String description, TransactionType type, Date dateCreated) {
        this.id = id;
        this.city = city;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getType() {
        return type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                amount == that.amount &&
                Objects.equals(city, that.city) &&
                Objects.equals(description, that.description) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, amount, description, type);
    }
}
