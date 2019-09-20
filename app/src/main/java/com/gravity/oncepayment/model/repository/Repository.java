package com.gravity.oncepayment.model.repository;

import androidx.lifecycle.LiveData;

import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.pojos.PaymentTransaction;
import com.gravity.oncepayment.model.pojos.PaymentTransactionGroup;
import com.gravity.oncepayment.model.pojos.Wallet;
import com.gravity.oncepayment.model.repository.dataSource.localDataSourse.LocalDataSource;

import java.util.List;

public class Repository {

    private LocalDataSource localDataSource;

    private static Repository repository;

    private Repository() {
        localDataSource = new LocalDataSource();
    }

    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }

    public void update(Wallet wallet) {
        localDataSource.update(wallet);
    }

    public void update(Payment payment) {
        localDataSource.update(payment);
    }

    public void update(PaymentTransaction paymentTransaction) {
        localDataSource.update(paymentTransaction);
    }

    public void insert(Wallet wallet) {
        localDataSource.insert(wallet);
    }

    public void insert(Payment payment) {
        localDataSource.insert(payment);
    }

    public void insert(PaymentTransaction paymentTransaction) {
        localDataSource.insert(paymentTransaction);
    }

    public void delete(Wallet wallet) {
        localDataSource.delete(wallet);
    }

    public void delete(Payment payment) {
        localDataSource.delete(payment);
    }

    public void delete(PaymentTransaction paymentTransaction) {
        localDataSource.delete(paymentTransaction);
    }

    public LiveData<List<PaymentTransactionGroup>> getAllDownsByDateRange(String startDate, int count) {
        return localDataSource.getAllDownsByDateRange(startDate, count);
    }

    public LiveData<List<PaymentTransactionGroup>> getAllUpsByDateRange(String startDate, int count) {
        return localDataSource.getAllUpsByDateRange(startDate, count);
    }

    public LiveData<List<Wallet>> getAllWallets() {
        return localDataSource.getAllWallets();
    }

    public LiveData<List<PaymentTransaction>> getAllTransactionsByParentId(int parentId) {
        return localDataSource.getAllTransactionsByParentId(parentId);
    }
}
