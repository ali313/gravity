package com.gravity.oncepayment.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.gravity.oncepayment.model.pojos.Payment;

@Dao
public interface PaymentDao {

    @Update
    void update(Payment payment);

    @Delete
    void delete(Payment payment);

    @Insert
    void insert(Payment payment);
}
