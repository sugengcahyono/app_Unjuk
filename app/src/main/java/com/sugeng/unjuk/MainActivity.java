package com.sugeng.unjuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity extends AppCompatActivity {

        private BottomNavigationView bottomNavigationView;
        private  String nama;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            bottomNavigationView = findViewById(R.id.bottom_navigation);
            Bundle extra = getIntent().getExtras();
            if (extra != null){
                nama = extra.getString("NAMA");
            }
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    if (menuItem.getItemId() == R.id.menu_dashboard_item) {
                        selectedFragment = new dashboard();
                    } else if (menuItem.getItemId() == R.id.menu_upload_item) {
                        selectedFragment = new Upload();
                    } else if (menuItem.getItemId() == R.id.menu_notifikasi_item) {
                        selectedFragment = new Notifikasi();
                    } else if (menuItem.getItemId() == R.id.menu_profile_item) {
                        selectedFragment = new Profile();
                    }

                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, selectedFragment)
                                .commit();
                    } else {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new dashboard())
                                .commit();
                    }

                    return true;
                }

            });

            // Set the initial fragment to display
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new dashboard())
                    .commit();
        }
    }