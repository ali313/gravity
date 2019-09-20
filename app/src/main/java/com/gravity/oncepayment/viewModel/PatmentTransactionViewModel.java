package com.gravity.oncepayment.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gravity.oncepayment.model.pojos.PaymentTransaction;
import com.gravity.oncepayment.model.repository.Repository;

import java.util.List;

public class PatmentTransactionViewModel extends AndroidViewModel {

    public PatmentTransactionViewModel(@NonNull Application application) {
        super(application);
    }

    public void update(PaymentTransaction paymentTransaction) {
        Repository.getInstance().update(paymentTransaction);
    }

    public void delete(PaymentTransaction paymentTransaction) {
        Repository.getInstance().delete(paymentTransaction);
    }

    public void insert(PaymentTransaction paymentTransaction) {
        Repository.getInstance().insert(paymentTransaction);
    }

    public LiveData<List<PaymentTransaction>> getAll(int parentId) {
        return Repository.getInstance().getAllTransactionsByParentId(parentId);
    }
}
