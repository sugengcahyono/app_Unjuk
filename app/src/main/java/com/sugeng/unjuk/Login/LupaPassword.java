package com.sugeng.unjuk.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sugeng.unjuk.R;

public class LupaPassword extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        // Menghubungkan tombol dengan kode Java
        Button disini = findViewById(R.id.Btnlanjut);
        EditText inputemail = findViewById(R.id.text_emailsandi);

        // Menambahkan onClickListener untuk tombol
        disini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat Intent untuk membuka aktivitas berikutnya
                Intent intent = new Intent(LupaPassword.this, Konfirmasi_password_baru.class)
                        .putExtra(Konfirmasi_password_baru.EMAIL, inputemail.getText().toString());
                startActivity(intent);

            }
        });

        ImageButton backButton = findViewById(R.id.Btnback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}