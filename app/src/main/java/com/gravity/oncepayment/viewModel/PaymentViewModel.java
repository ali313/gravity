package com.gravity.oncepayment.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.repository.Repository;

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
}
