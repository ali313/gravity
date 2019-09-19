package com.gravity.oncepayment.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Payment.PAYMENT_TABLE_NAME)
public class Payment {

    public static final String PAYMENT_TABLE_NAME = "payment_table";

    public static final String ID_KEY = "id";
    public static final String BAG_ID_KEY = "bagId";
    public static final String PRICE_KEY = "price";
    public static final String DESCRIPTION_KEY = "description";
    public static final String TITLE_KEY = "title";
    public static final String PRIORITY_KEY = "priority";

    @ColumnInfo(name = ID_KEY)
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = BAG_ID_KEY)
    private int bagId;
    @ColumnInfo(name = PRICE_KEY)
    private long price;
    @ColumnInfo(name = DESCRIPTION_KEY)
    private String description;
    @ColumnInfo(name = TITLE_KEY)
    private String title;
    @ColumnInfo(name = PRIORITY_KEY)
    private int priority;

    public Payment(int bagId, long price, String description, String title, int priority) {
        this.bagId = bagId;
        this.price = price;
        this.description = description;
        this.title = title;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBagId() {
        return bagId;
    }

    public void setBagId(int bagId) {
        this.bagId = bagId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

