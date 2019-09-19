package com.gravity.oncepayment.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PaymentTransactionDao {

    @Update
    void update(PaymentTransaction paymentTransaction);

    @Delete
    void delete(PaymentTransaction paymentTransaction);

    @Insert
    void insert(PaymentTransaction paymentTransaction);

    @Query("SELECT * FROM payment_transaction WHERE :parentId IN payment_table")
    LiveData<List<PaymentTransaction>> getAll(int parentId);
}
