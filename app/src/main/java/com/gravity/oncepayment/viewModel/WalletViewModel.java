package com.gravity.oncepayment.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gravity.oncepayment.model.pojos.Wallet;
import com.gravity.oncepayment.model.repository.Repository;

import java.util.List;

public class WalletViewModel extends AndroidViewModel {

    public WalletViewModel(@NonNull Application application) {
        super(application);
    }

    public void update(Wallet wallet) {
        Repository.getInstance().update(wallet);
    }

    public void delete(Wallet wallet) {
        Repository.getInstance().delete(wallet);
    }

    public void insert(Wallet wallet) {
        Repository.getInstance().insert(wallet);
    }

    public LiveData<List<Wallet>> getAll() {
        return Repository.getInstance().getAllWallets();
    }
}
