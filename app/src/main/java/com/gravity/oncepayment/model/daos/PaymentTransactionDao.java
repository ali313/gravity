package com.gravity.oncepayment.model.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.pojos.PaymentTransaction;

import java.util.List;

@Dao
public interface PaymentTransactionDao {

    @Update
    void update(PaymentTransaction paymentTransaction);

    @Delete
    void delete(PaymentTransaction paymentTransaction);

    @Insert
    void insert(PaymentTransaction paymentTransaction);

    @Query("SELECT * FROM payment_transaction WHERE parentId = :parentId")
    LiveData<List<PaymentTransaction>> getAll(int parentId);

    @Query("SELECT * FROM payment_transaction WHERE id = :id")
    PaymentTransaction getById(int id);
}
