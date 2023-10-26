package com.sugeng.unjuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Konfirmasi_password_baru extends AppCompatActivity {

    public static String EMAIL;
    Button Kirim;
    EditText katasandi;
    EditText Konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_password_baru);

        katasandi = findViewById(R.id.text_katasandibaru);
        Konfirmasi = findViewById(R.id.Konfirmasi_sandi);

        Kirim = findViewById(R.id.Btnlanjut);

        Kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (katasandi.getText().toString().equalsIgnoreCase(Konfirmasi.getText().toString())){
                    retrofitclient.getConnection().create(RetrofitEndPoint.class)
                            .katasandi_baru(getIntent().getStringExtra(EMAIL), katasandi.getText().toString())
                            .enqueue(new Callback<userrespons>() {
                                @Override
                                public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                        // Login berhasil, lanjutkan ke aktivitas berikutnya
                                        Intent intent = new Intent(Konfirmasi_password_baru.this, Login.class);
                                        startActivity(intent);
                                    } else {
                                        // Email tidak ditemukan di database, beri tahu pengguna
                                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<userrespons> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }else {
                    Toast.makeText(Konfirmasi_password_baru.this, "Maaf kata sandi tidak sama", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}