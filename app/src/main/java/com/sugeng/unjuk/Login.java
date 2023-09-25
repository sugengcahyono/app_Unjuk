package com.sugeng.unjuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Menghubungkan tombol dengan kode Java
        TextView disini = findViewById(R.id.Btndisini);

        // Menambahkan onClickListener untuk tombol
        disini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat Intent untuk membuka aktivitas berikutnya
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        // Menghubungkan tombol kedua dengan kode Java
        TextView lupapassword = findViewById(R.id.BtnLupapassword);

        // Menambahkan onClickListener untuk tombol kedua
        lupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat Intent untuk membuka aktivitas kedua
                Intent intent = new Intent(Login.this, LupaPassword.class);
                startActivity(intent);
            }
        });
        Button login = findViewById(R.id.BtnLogin);

        // Menambahkan onClickListener untuk tombol
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat Intent untuk membuka aktivitas berikutnya
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}