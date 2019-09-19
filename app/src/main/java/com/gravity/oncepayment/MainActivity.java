package com.gravity.oncepayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gravity.oncepayment.ui.fragment.BagFragment;

public class MainActivity extends AppCompatActivity
    implements BottomNavigationView.OnNavigationItemSelectedListener
{

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        bottomNavigationView.inflateMenu(R.menu.main_buttom_navigation);


        Fragment fragment = new BagFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer
        ,fragment).commit();
    }

    public  void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

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
