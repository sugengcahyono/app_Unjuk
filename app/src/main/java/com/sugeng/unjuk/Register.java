package com.sugeng.unjuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    TextInputLayout textInputLayout;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton backButton = findViewById(R.id.Btnback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // toggle lihat password
        textInputLayout = findViewById(R.id.textinputlayout);
        editText = findViewById(R.id.edittext_pass);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String passwordInput = charSequence.toString();
                if (passwordInput.length()>=8)  {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(passwordInput);
                    boolean passwordsMatch = matcher.find();
                    if (passwordsMatch) {
                        textInputLayout.setHelperText("Password Anda Kuat");
                        textInputLayout.setError("");
                    } else {
                        textInputLayout.setError("campur huruf (huruf besar dan kecil), angka dan simbol");
                    }
                } else {
                    textInputLayout.setHelperText("kata sandi harus sepanjang 8 karakter");
                    textInputLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // menggunakan intent
        Button Daftar = findViewById(R.id.btn_Daftar);
        EditText namauser= (EditText) findViewById(R.id.edittext_namauser);
        EditText email = (EditText) findViewById(R.id.edittext_emailuser);
        EditText password = (EditText) findViewById(R.id.edittext_pass);
        EditText nohpuser = (EditText) findViewById(R.id.edittext_nohpuser);
        EditText alamatuser = (EditText) findViewById(R.id.edittext_alamatuser);

        // Menambahkan onClickListener untuk tombol
        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retrofitclient.getConnection().create(RetrofitEndPoint.class)
                        .buatakun(namauser.getText().toString(), email.getText().toString(), password.getText().toString()
                                ,nohpuser.getText().toString(),alamatuser.getText().toString(), "Mobile")
                        .enqueue(new Callback<userrespons>() {
                            @Override
                            public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                                if(response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){


                                    Intent intent = new Intent(Register.this, Login.class);
                                    Toast.makeText(getApplicationContext(), "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<userrespons> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });


    }
}