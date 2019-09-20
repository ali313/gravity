package com.gravity.oncepayment.model.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gravity.oncepayment.model.pojos.Wallet;

import java.util.List;

@Dao
public interface WalletDao {

    @Update
    void update(Wallet wallet);

    @Delete
    void delete(Wallet wallet);

    @Insert
    void insert(Wallet wallet);

    @Query("SELECT * FROM wallet_table ORDER BY createdDate")
    LiveData<List<Wallet>> getAll();

    @Query("SELECT * FROM wallet_table WHERE id = :id")
    Wallet getById(int id);
}
