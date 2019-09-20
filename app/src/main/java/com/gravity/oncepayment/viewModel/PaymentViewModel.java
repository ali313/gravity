package com.gravity.oncepayment.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.pojos.PaymentTransaction;
import com.gravity.oncepayment.model.repository.Repository;

import java.util.List;

public class PaymentViewModel extends AndroidViewModel {

    public PaymentViewModel(@NonNull Application application) {
        super(application);
    }

    public void update(Payment payment) {
        Repository.getInstance().update(payment);
    }

    public void delete(Payment payment) {
        Repository.getInstance().delete(payment);
    }

    public void insert(Payment payment) {
        Repository.getInstance().insert(payment);
    }

    public LiveData<List<Payment>> getAll(int walletId) {
        return Repository.getInstance().getAllPaymentsByWalletId(walletId);
    }

    public Payment getPayment(int id) {
        return Repository.getInstance().getPayment(id);
    }

    public PaymentTransaction getPaymentTransaction(int id) {
        return Repository.getInstance().getPaymentTransaction(id);
    }
}
