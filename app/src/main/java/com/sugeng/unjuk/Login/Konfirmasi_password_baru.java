package com.sugeng.unjuk.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.userrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Konfirmasi_password_baru extends AppCompatActivity {

    public static String EMAIL;

    private String dataEmail;
    Button Kirim;
    TextInputLayout textInputLayout;
    EditText katasandi;
    EditText Konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_password_baru);

        dataEmail = getIntent().getStringExtra(EMAIL);

        textInputLayout = findViewById(R.id.textinputlayout);

        katasandi = findViewById(R.id.text_katasandibaru);
        Konfirmasi = findViewById(R.id.Konfirmasi_sandi);


        Kirim = findViewById(R.id.Btnlanjut);

        Kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (katasandi.getText().toString().equalsIgnoreCase(Konfirmasi.getText().toString())){
                    retrofitclient.getConnection().create(RetrofitEndPoint.class)
                            .katasandi_baru(dataEmail, katasandi.getText().toString())
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

        katasandi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String passwordInput = charSequence.toString();
                if (passwordInput.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(passwordInput);
                    boolean passwordsMatch = matcher.find();
                    if (passwordsMatch) {
                        textInputLayout.setHelperText("Password Anda Kuat");
                        textInputLayout.setError("");
                    } else {
                        textInputLayout.setError("Campur huruf (huruf besar dan kecil), angka, dan simbol");
                    }
                } else {
                    textInputLayout.setHelperText("Kata sandi harus setidaknya 8 karakter");
                    textInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}