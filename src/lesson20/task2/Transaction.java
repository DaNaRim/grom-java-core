package lesson20.task2;

import java.util.Date;

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
}
