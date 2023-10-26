package com.sugeng.unjuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Produk_saya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_saya);

        ImageButton backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        Button makanan = findViewById(R.id.btn_makanan);
//        Button minuman = findViewById(R.id.btn_minuman);
//        Button kerajinan = findViewById(R.id.btn_kerajinan);
//        makanan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Membuat Intent untuk membuka aktivitas berikutnya
//                Intent intent = new Intent(Produk_saya.this, Produk_makanan.class);
//                startActivity(intent);
//            }
//        });

//        minuman.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Membuat Intent untuk membuka aktivitas berikutnya
//                Intent intent = new Intent(Produk_saya.this, Produk_minuman.class);
//                startActivity(intent);
//            }
//        });

//        kerajinan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Membuat Intent untuk membuka aktivitas berikutnya
//                Intent intent = new Intent(Produk_saya.this, Produk_Kerajinan.class);
//                startActivity(intent);
//            }
//        });


    }
}