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



        loadFragment(new BagFragment());
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer
//        ,fragment).commit();
    }

    public  void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

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
