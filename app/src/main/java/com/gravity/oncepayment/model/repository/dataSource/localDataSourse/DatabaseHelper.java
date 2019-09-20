package com.gravity.oncepayment.model.repository.dataSource.localDataSourse;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gravity.oncepayment.base.App;
import com.gravity.oncepayment.model.daos.PaymentDao;
import com.gravity.oncepayment.model.daos.PaymentTransactionDao;
import com.gravity.oncepayment.model.daos.PaymentTransactionGroupDao;
import com.gravity.oncepayment.model.daos.WalletDao;
import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.pojos.PaymentTransaction;
import com.gravity.oncepayment.model.pojos.Wallet;

@Database(entities = {Payment.class, PaymentTransaction.class, Wallet.class}, version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {

    private static DatabaseHelper instance;

    public abstract PaymentDao paymentDao();

    public abstract PaymentTransactionDao paymentTransactionDao();

    public abstract WalletDao walletDao();

    public abstract PaymentTransactionGroupDao paymentTransactionGroupDao();

    public synchronized static DatabaseHelper getInstance() {
        if (instance == null) instance = Room
                .databaseBuilder(App.context, DatabaseHelper.class, "my_database")
                .fallbackToDestructiveMigration()
                .build();

        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    return null;
                }
            };

            task.execute();
            super.onCreate(db);
        }
    };
}
