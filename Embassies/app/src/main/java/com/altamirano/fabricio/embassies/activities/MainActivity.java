package com.altamirano.fabricio.embassies.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.altamirano.fabricio.embassies.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment fragmentall = new FragmentAllData();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_content, fragmentall).commit();
                    return true;
                case R.id.navigation_dashboard:
                    Fragment fragmentSearch = new FragmentSearch();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_content, fragmentSearch).commit();
                    return true;
                case R.id.navigation_notifications:
                    Fragment fragment = new FragmentHistory();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_content, fragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fragmentManager = getSupportFragmentManager();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);


    }
}
