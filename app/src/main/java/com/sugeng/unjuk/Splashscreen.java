package com.sugeng.unjuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sugeng.unjuk.Login.Login;
import com.sugeng.unjuk.Login.SesionManager;
import com.sugeng.unjuk.Menu.dashboard;

public class Splashscreen extends AppCompatActivity {

    SesionManager sesionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        SesionManager sesionManager = new SesionManager(this);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // jika pengguna sudah logiin
                if (sesionManager.isLoggedIn()) {
                    Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                    startActivity(intent);
                } else { // jika pengguna belum login
                    Intent intent = new Intent(Splashscreen.this, Login.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000); //3 detik
    }
}