package com.gravity.oncepayment.model.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.pojos.PaymentTransaction;
import com.gravity.oncepayment.model.pojos.PaymentTransactionGroup;
import com.gravity.oncepayment.model.pojos.Wallet;

import java.util.List;

@Dao
public interface PaymentTransactionGroupDao {


    @Query("select " + PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME + ".*," +
            Payment.PAYMENT_TABLE_NAME + "." + Payment.WALLET_ID_KEY + ", " +
            Payment.PAYMENT_TABLE_NAME + "." + Payment.TITLE_KEY + ", " +
            Payment.PAYMENT_TABLE_NAME + "." + Payment.PRIORITY_KEY + ", " +
            Payment.PAYMENT_TABLE_NAME + "." + Payment.PRICE_KEY + ", " +
            Wallet.WALLET_TABLE_NAME + "." + Wallet.COLOR_KEY +
            " From " + PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME +
            " join " + Payment.PAYMENT_TABLE_NAME + " on "  + Payment.PAYMENT_TABLE_NAME + "." + Payment.ID_KEY +
                " = " + PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME + "." + PaymentTransaction.PARENT_ID_KEY +
            " join " + Wallet.WALLET_TABLE_NAME + " on " + Wallet.WALLET_TABLE_NAME + "." + Wallet.ID_KEY +
                " = " + Payment.PAYMENT_TABLE_NAME + "." + Payment.WALLET_ID_KEY +
            " where " + PaymentTransaction.PAYMENT_DATE_KEY +
            " in (select " + PaymentTransaction.PAYMENT_DATE_KEY +
            " from " + PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME +
            " where " + PaymentTransaction.PAYMENT_DATE_KEY +
            " > :startDate" +
            " group by " + PaymentTransaction.PAYMENT_DATE_KEY +
            " order by " + PaymentTransaction.PAYMENT_DATE_KEY + " asc limit :count)" +
            " order by " + PaymentTransaction.PAYMENT_DATE_KEY +
            ", " + Payment.PRIORITY_KEY)
    LiveData<List<PaymentTransactionGroup>> getAllDownsByDateRange(String startDate, int count);

    @Query("select " + PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME + ".*," +
            Payment.PAYMENT_TABLE_NAME + "." + Payment.WALLET_ID_KEY + ", " +
            Payment.PAYMENT_TABLE_NAME + "." + Payment.TITLE_KEY + ", " +
            Payment.PAYMENT_TABLE_NAME + "." + Payment.PRIORITY_KEY + ", " +
            Payment.PAYMENT_TABLE_NAME + "." + Payment.PRICE_KEY + ", " +
            Wallet.WALLET_TABLE_NAME + "." + Wallet.COLOR_KEY +
            " From " + PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME +
            " join " + Payment.PAYMENT_TABLE_NAME + " on "  + Payment.PAYMENT_TABLE_NAME + "." + Payment.ID_KEY +
            " = " + PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME + "." + PaymentTransaction.PARENT_ID_KEY +
            " join " + Wallet.WALLET_TABLE_NAME + " on " + Wallet.WALLET_TABLE_NAME + "." + Wallet.ID_KEY +
            " = " + Payment.PAYMENT_TABLE_NAME + "." + Payment.WALLET_ID_KEY +
            " where " + PaymentTransaction.PAYMENT_DATE_KEY +
            " in (select " + PaymentTransaction.PAYMENT_DATE_KEY +
            " from " + PaymentTransaction.PAYMENT_TRANSACTION_TABLE_NAME +
            " where " + PaymentTransaction.PAYMENT_DATE_KEY +
            " < :startDate" +
            " group by " + PaymentTransaction.PAYMENT_DATE_KEY +
            " order by " + PaymentTransaction.PAYMENT_DATE_KEY + " desc limit :count)" +
            " order by " + PaymentTransaction.PAYMENT_DATE_KEY +
            ", " + Payment.PRIORITY_KEY)
    LiveData<List<PaymentTransactionGroup>> getAllUpsByDateRange(String startDate, int count);

}
