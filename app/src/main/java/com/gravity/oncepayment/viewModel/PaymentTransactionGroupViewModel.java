package com.gravity.oncepayment.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gravity.oncepayment.model.pojos.PaymentTransactionGroup;
import com.gravity.oncepayment.model.repository.Repository;

import java.util.List;

public class PaymentTransactionGroupViewModel extends AndroidViewModel {

    public PaymentTransactionGroupViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<PaymentTransactionGroup>> getAllDownsByDateRange(String startDate, int count) {
        return Repository.getInstance().getAllDownsByDateRange(startDate, count);
    }

    public LiveData<List<PaymentTransactionGroup>> getAllUpsByDateRange(String startDate, int count) {
        return Repository.getInstance().getAllUpsByDateRange(startDate, count);
    }
}
