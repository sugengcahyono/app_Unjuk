package com.sugeng.unjuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sugeng.unjuk.Menu.Notifikasi;
import com.sugeng.unjuk.Menu.Profile;
import com.sugeng.unjuk.Menu.Upload;
import com.sugeng.unjuk.Menu.dashboard;

public class MainActivity extends AppCompatActivity {

        private BottomNavigationView bottomNavigationView;
        private  String nama;

        public static String FRAGMENT = "frag", PROFILE = "prof";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Bundle extra = getIntent().getExtras();

            if (extra != null){
                nama = extra.getString("NAMA");

                if (extra != null && extra.getString(FRAGMENT) != null && extra.getString(FRAGMENT).equalsIgnoreCase(PROFILE)) {
                    // Set the initial fragment to display
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new Profile())
                            .commit();
                }

            }

            bottomNavigationView = findViewById(R.id.bottom_navigation);
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

    public void onBackPressed() {
        // Tampilkan peringatan
        new AlertDialog.Builder(this)
                .setTitle("Keluar dari aplikasi ")
                .setMessage("Apakah Anda yakin ingin Keluar dari aplikasi?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Keluar dari aplikasi
                        finish();
                        System.exit(0); //keluar dari aplikasi
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Batal keluar
                        dialog.dismiss();
                    }
                })
                .show();
    }

    }