package com.gravity.oncepayment.model.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gravity.oncepayment.model.pojos.Payment;

import java.util.List;

@Dao
public interface PaymentDao {

    @Update
    void update(Payment payment);

    @Delete
    void delete(Payment payment);

    @Insert
    void insert(Payment payment);

    @Query("SELECT * FROM payment_table WHERE walletId = :walletId")
    LiveData<List<Payment>> getAllByWalletId(int walletId);
}
