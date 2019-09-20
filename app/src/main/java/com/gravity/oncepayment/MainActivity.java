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
import com.gravity.oncepayment.ui.fragment.TimeLineFragment;

public class MainActivity extends AppCompatActivity

    implements BottomNavigationView.OnNavigationItemSelectedListener
{

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

        loadFragment(new BagFragment());
    }

    public void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.Payments:
                loadFragment(new TimeLineFragment());
                break;

            case R.id.Report:
                loadFragment(new BagFragment());
                break;

            case R.id.Wallets:
                loadFragment(new BagFragment());
                break;
        }


        return false;
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}