package com.gravity.oncepayment.model.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME)
public class PaymentTransaction {

    public static final String PAYMENT_TRANSACTION_TABLE_NAME = "payment_transaction";

    public static final String ID_KEY = "id";
    public static final String PARENT_ID_KEY = "parentId";
    public static final String PAYMENT_DATE_KEY = "paymentDate";
    public static final String PAYED_DATE_KET = "payedDate";
    public static final String PAYED_KEY = "payed";

    @ColumnInfo(name = ID_KEY)
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = PARENT_ID_KEY)
    private int parentId;
    @ColumnInfo(name = PAYMENT_DATE_KEY)
    private String paymentDate;
    @ColumnInfo(name = PAYED_DATE_KET)
    private String payedDate;
    @ColumnInfo(name = PAYED_KEY)
    private boolean payed;

    public PaymentTransaction(int parentId, String paymentDate, String payedDate, boolean payed) {
        this.parentId = parentId;
        this.paymentDate = paymentDate;
        this.payedDate = payedDate;
        this.payed = payed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(String payedDate) {
        this.payedDate = payedDate;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
