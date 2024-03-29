package com.gravity.oncepayment.model.repository.dataSource.localDataSourse;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.gravity.oncepayment.model.daos.PaymentDao;
import com.gravity.oncepayment.model.daos.PaymentTransactionDao;
import com.gravity.oncepayment.model.daos.PaymentTransactionGroupDao;
import com.gravity.oncepayment.model.daos.WalletDao;
import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.pojos.PaymentTransaction;
import com.gravity.oncepayment.model.pojos.PaymentTransactionGroup;
import com.gravity.oncepayment.model.pojos.Wallet;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LocalDataSource {

    private WalletDao walletDao;
    private PaymentDao paymentDao;
    private PaymentTransactionDao paymentTransactionDao;
    private PaymentTransactionGroupDao paymentTransactionGroupDao;

    public LocalDataSource() {
        walletDao = DatabaseHelper.getInstance().walletDao();
        paymentDao = DatabaseHelper.getInstance().paymentDao();
        paymentTransactionDao = DatabaseHelper.getInstance().paymentTransactionDao();
        paymentTransactionGroupDao = DatabaseHelper.getInstance().paymentTransactionGroupDao();
    }

    public void update(Wallet wallet) {
        UpdateWalletAsyncTask updateWalletAsyncTask = new UpdateWalletAsyncTask();
        updateWalletAsyncTask.execute(wallet);
    }

    public void update(Payment payment) {
        UpdatePaymentAsyncTask updatePaymentAsyncTask = new UpdatePaymentAsyncTask();
        updatePaymentAsyncTask.execute(payment);
    }

    public void update(PaymentTransaction paymentTransaction) {
        UpdatePaymentTransactionAsyncTask updatePaymentTransactionAsyncTask = new UpdatePaymentTransactionAsyncTask();
        updatePaymentTransactionAsyncTask.execute(paymentTransaction);
    }

    public void delete(Wallet wallet) {
        DeleteWalletAsyncTask deleteWalletAsyncTask = new DeleteWalletAsyncTask();
        deleteWalletAsyncTask.execute(wallet);
    }

    public void delete(Payment payment) {
        DeletePaymentAsyncTask deletePaymentAsyncTask = new DeletePaymentAsyncTask();
        deletePaymentAsyncTask.execute(payment);
    }

    public void delete(PaymentTransaction paymentTransaction) {
        DeletePaymentTransactionAsyncTask deletePaymentTransactionAsyncTask = new DeletePaymentTransactionAsyncTask();
        deletePaymentTransactionAsyncTask.execute(paymentTransaction);
    }

    public void insert(Wallet wallet) {
        InsertWalletAsyncTask insertWalletAsyncTask = new InsertWalletAsyncTask();
        insertWalletAsyncTask.execute(wallet);
    }


    public void insert(Payment payment) {
        InsertPaymentAsyncTask insertPaymentAsyncTask = new InsertPaymentAsyncTask();
        insertPaymentAsyncTask.execute(payment);
    }


    public void insert(PaymentTransaction paymentTransaction) {
        InsertPaymentTransActionAsyncTask insertPaymentTransActionAsyncTask = new InsertPaymentTransActionAsyncTask();
        insertPaymentTransActionAsyncTask.execute(paymentTransaction);
    }

    public LiveData<List<PaymentTransactionGroup>> getAllDownsByDateRange(String startDate, int count) {
        return paymentTransactionGroupDao.getAllDownsByDateRange(startDate, count);
    }

    public LiveData<List<PaymentTransactionGroup>> getAllUpsByDateRange(String startDate, int count) {
        return paymentTransactionGroupDao.getAllUpsByDateRange(startDate, count);
    }

    public LiveData<List<Wallet>> getAllWallets() {
        return walletDao.getAll();
    }

    public LiveData<List<PaymentTransaction>> getAllTransactionsByParentId(int parentId) {
        return paymentTransactionDao.getAll(parentId);
    }

    public LiveData<List<Payment>> getAllPaymentsByWalletId(int walletId) {
        return paymentDao.getAllByWalletId(walletId);
    }

    public Wallet getWallet(int id) {
        GetWalletAsyncTask getWalletAsyncTask = new GetWalletAsyncTask();
        try {
            return getWalletAsyncTask.execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Payment getPayment(int id) {
        GetPaymentAsyncTask getPaymentAsyncTask = new GetPaymentAsyncTask();
        try {
            return getPaymentAsyncTask.execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public PaymentTransaction gePaymentTransaction(int id) {
        GetPaymentTransactionAsyncTask getPaymentTransactionAsyncTask = new GetPaymentTransactionAsyncTask();
        try {
            return getPaymentTransactionAsyncTask.execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    class UpdateWalletAsyncTask extends AsyncTask<Wallet, Void, Void> {

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.update(wallets[0]);
            return null;
        }
    }

    class UpdatePaymentAsyncTask extends AsyncTask<Payment, Void, Void> {

        @Override
        protected Void doInBackground(Payment... payments) {
            paymentDao.update(payments[0]);
            return null;
        }
    }

    class UpdatePaymentTransactionAsyncTask extends AsyncTask<PaymentTransaction, Void, Void> {

        @Override
        protected Void doInBackground(PaymentTransaction... paymentTransactions) {
            paymentTransactionDao.update(paymentTransactions[0]);
            return null;
        }
    }

    class DeleteWalletAsyncTask extends AsyncTask<Wallet, Void, Void> {

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.delete(wallets[0]);
            return null;
        }
    }

    class DeletePaymentAsyncTask extends AsyncTask<Payment, Void, Void> {

        @Override
        protected Void doInBackground(Payment... payments) {
            paymentDao.delete(payments[0]);
            return null;
        }
    }

    class DeletePaymentTransactionAsyncTask extends AsyncTask<PaymentTransaction, Void, Void> {

        @Override
        protected Void doInBackground(PaymentTransaction... paymentTransactions) {
            paymentTransactionDao.delete(paymentTransactions[0]);
            return null;
        }
    }

    class InsertWalletAsyncTask extends AsyncTask<Wallet, Void, Void> {

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.insert(wallets[0]);
            return null;
        }
    }

    class InsertPaymentAsyncTask extends AsyncTask<Payment, Void, Void> {

        @Override
        protected Void doInBackground(Payment... payments) {
            paymentDao.insert(payments[0]);
            return null;
        }
    }

    class InsertPaymentTransActionAsyncTask extends AsyncTask<PaymentTransaction, Void, Void> {

        @Override
        protected Void doInBackground(PaymentTransaction... paymentTransactions) {
            paymentTransactionDao.insert(paymentTransactions[0]);
            return null;
        }
    }

    class GetWalletAsyncTask extends AsyncTask<Integer, Void, Wallet> {

        @Override
        protected Wallet doInBackground(Integer... integers) {
            return walletDao.getById(integers[0]);
        }
    }

    class GetPaymentAsyncTask extends AsyncTask<Integer, Void, Payment> {

        @Override
        protected Payment doInBackground(Integer... integers) {
            return paymentDao.getById(integers[0]);
        }
    }

    class GetPaymentTransactionAsyncTask extends AsyncTask<Integer, Void, PaymentTransaction> {

        @Override
        protected PaymentTransaction doInBackground(Integer... integers) {
            return paymentTransactionDao.getById(integers[0]);
        }
    }

}
