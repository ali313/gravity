package com.gravity.oncepayment.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface PaymentDao {

    @Update
    void update(Payment payment);

    @Delete
    void delete(Payment payment);

    @Insert
    void insert(Payment payment);


}
