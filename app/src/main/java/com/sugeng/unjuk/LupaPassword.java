package com.sugeng.unjuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LupaPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        // Menghubungkan tombol dengan kode Java
        Button disini = findViewById(R.id.Btnlanjut);

        // Menambahkan onClickListener untuk tombol
        disini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat Intent untuk membuka aktivitas berikutnya
                Intent intent = new Intent(LupaPassword.this, kodeotp.class);
                startActivity(intent);

            }
        });

        Button backButton = findViewById(R.id.Btnback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}