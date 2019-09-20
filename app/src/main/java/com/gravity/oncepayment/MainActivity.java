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
<<<<<<< HEAD
        implements BottomNavigationView.OnNavigationItemSelectedListener {
=======

    implements BottomNavigationView.OnNavigationItemSelectedListener
{
>>>>>>> b06376a0ec21eae28afe933c4f2aafbe9a00b5d6

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


<<<<<<< HEAD
        Fragment fragment = new BagFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer
                , fragment).commit();
=======

        loadFragment(new BagFragment());
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer
//        ,fragment).commit();
>>>>>>> b06376a0ec21eae28afe933c4f2aafbe9a00b5d6
    }

    public void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.Payments:
                loadFragment(new BagFragment());
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