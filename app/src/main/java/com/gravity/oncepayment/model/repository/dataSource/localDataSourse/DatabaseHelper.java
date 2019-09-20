package com.gravity.oncepayment.model.repository.dataSource.localDataSourse;

import android.graphics.Color;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gravity.oncepayment.Utilities.datetime.MCalendar;
import com.gravity.oncepayment.base.App;
import com.gravity.oncepayment.model.daos.PaymentDao;
import com.gravity.oncepayment.model.daos.PaymentTransactionDao;
import com.gravity.oncepayment.model.daos.PaymentTransactionGroupDao;
import com.gravity.oncepayment.model.daos.WalletDao;
import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.pojos.PaymentTransaction;
import com.gravity.oncepayment.model.pojos.Wallet;

import java.security.SecureRandom;

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
                .addCallback(callback)
                .build();

        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {

                    MCalendar cal = MCalendar.getToday();

                    getInstance().walletDao().insert(new Wallet("کیف اقساط", Color.YELLOW, 900000, ""));
                    getInstance().walletDao().insert(new Wallet("کیف خانواده", Color.RED, 700000, ""));
                    getInstance().walletDao().insert(new Wallet("کیف متفرقه", Color.GREEN, 600000, ""));

                    String s = "این بدهی برای شرکت ارامکو می باشد لطفا دز اسرع وقت نسبت به پرداخت آن اقدام شود. لطفا پس از پرداخت به آقای سلطانی اطلاع داده شود. سبحان احمدیان مقدم";
                    getInstance().paymentDao().insert(new Payment(1, 10000, s, "قسط بانک سامان", 1));
                    getInstance().paymentDao().insert(new Payment(1, 7000, s, "قسط قرعه کشی خانوادگی", 2));
                    getInstance().paymentDao().insert(new Payment(1, 32000, s, "قسط بیمه ی ماشین", 3));
                    getInstance().paymentDao().insert(new Payment(1, 98000, s, "قسط لوازم خانگی شکوفه", 1));
                    getInstance().paymentDao().insert(new Payment(2, 12000, s, "مجید", 1));
                    getInstance().paymentDao().insert(new Payment(2, 1500, s, "رضا", 2));
                    getInstance().paymentDao().insert(new Payment(3, 12500, s, "کمک به کودکان سرطانی", 1));
                    getInstance().paymentDao().insert(new Payment(3, 9000, s, "پس انداز", 4));


                    SecureRandom secureRandom = new SecureRandom();
                    for (int i = 0; i < 1600; i++) {
                        int l = secureRandom.nextInt(20) - 10;
                        cal.addDays(l);
                        getInstance().paymentTransactionDao().insert(new PaymentTransaction(i % 8, cal.getJalali().toString(), "", i % 2 == 0));
                    }

                    return null;
                }
            };

            task.execute();
            super.onCreate(db);
        }
    };
}
