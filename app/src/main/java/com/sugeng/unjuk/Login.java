package com.sugeng.unjuk;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputLayout textInputLayout;
    EditText editText;
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
    // menggunakan intent
        Button login = findViewById(R.id.BtnLogin);
        EditText txtEmail = (EditText) findViewById(R.id.txtemail);
        // Menambahkan onClickListener untuk tombol
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retrofitclient.getConnection().create(RetrofitEndPoint.class)
                        .login(txtEmail.getText().toString(), editText.getText().toString())
                        .enqueue(new Callback<userrespons>() {
                            @Override
                            public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                                if(response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                                    // Membuat Intent untuk membuka aktivitas berikutnya
                                    String nama = txtEmail.getText().toString();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.putExtra("NAMA",nama);
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


        // toggle lihat password
        textInputLayout = findViewById(R.id.textinputlayout);
        editText = findViewById(R.id.pass);

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


    }
}