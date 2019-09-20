package com.gravity.oncepayment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gravity.oncepayment.model.pojos.Payment;
import com.gravity.oncepayment.model.repository.dataSource.localDataSourse.DatabaseHelper;
import com.gravity.oncepayment.ui.fragment.BagFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseHelper.getInstance().paymentDao().insert(new Payment(1, 12, "hi", "wallet1", 1));
                return null;
            }
        }.execute();


        init();

        bottomNavigationView.inflateMenu(R.menu.main_buttom_navigation);


        Fragment fragment = new BagFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer
                , fragment).commit();
    }

    public void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.Payments:
                break;

            case R.id.Report:
                break;

            case R.id.Wallets:
                break;
        }


        return false;
    }
}
