package com.gravity.oncepayment.model.pojos;

import androidx.room.ColumnInfo;

public class PaymentTransactionGroup {

    @ColumnInfo(name = PaymentTransaction.ID_KEY)
    private int id;
    @ColumnInfo(name = PaymentTransaction.PARENT_ID_KEY)
    private int parentId;
    @ColumnInfo(name = PaymentTransaction.PAYMENT_DATE_KEY)
    private String paymentDate;
    @ColumnInfo(name = PaymentTransaction.PAYED_DATE_KET)
    private String payedDate;
    @ColumnInfo(name = PaymentTransaction.PAYED_KEY)
    private boolean payed;
    @ColumnInfo(name = Payment.TITLE_KEY)
    private String title;
    @ColumnInfo(name = Payment.PRIORITY_KEY)
    private int priority;
    @ColumnInfo(name = Payment.WALLET_ID_KEY)
    private int walletId;
    @ColumnInfo(name = Wallet.COLOR_KEY)
    private int walletColor;
    @ColumnInfo(name = Payment.PRICE_KEY)
    private long price;

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

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public int getWalletColor() {
        return walletColor;
    }

    public void setWalletColor(int walletColor) {
        this.walletColor = walletColor;
    }
}
