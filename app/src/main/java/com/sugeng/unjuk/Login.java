package com.sugeng.unjuk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    GoogleUsers googleUsers;
    Button btngoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        googleUsers = new GoogleUsers(this);
        btngoogle = findViewById(R.id.btngoogle);

        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        btngoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Munculkan pilihan akun Google
                startActivityForResult(googleUsers.getIntent(), GoogleUsers.REQUEST_CODE);
            }
        });


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

        // Menggunakan intent
        Button login = findViewById(R.id.BtnLogin);
        EditText txtEmail = findViewById(R.id.txtemail);
        editText =
                findViewById(R.id.pass);

        // Menambahkan onClickListener untuk tombol login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofitclient.getConnection().create(RetrofitEndPoint.class)
                        .login(txtEmail.getText().toString(), editText.getText().toString())
                        .enqueue(new Callback<userrespons>() {
                            @Override
                            public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                    usermodel user = response.body().getData();
                                    editor.putString("id_akun", user.getIdakun());
                                    editor.putString("email", user.getEmail());
                                    editor.putString("nama_user", user.getNama_user());
                                    editor.putString("alamat", user.getAlamat_user());
                                    editor.putString("no_telp",user.getNotelp_user());
                                    editor.putString("user_foto",user.getUserfoto());
                                    editor.putString("id_umkm", user.getIdumkm());
                                    editor.apply();

                                    // Membuat Intent untuk membuka aktivitas berikutnya
                                    String nama = txtEmail.getText().toString();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.putExtra("NAMA", nama);
                                    startActivity(intent);
                                } else {
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

        // Toggle lihat password
        textInputLayout = findViewById(R.id.textinputlayout);
        editText = findViewById(R.id.pass);

        editText.addTextChangedListener(new TextWatcher() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GoogleUsers.REQUEST_CODE) {
            // Proses hasil dari pemilihan akun Google
            googleUsers.onActivityResult(requestCode, resultCode, data);

            if (googleUsers.isAccountSelected()) {
                // Jika akun Google telah dipilih
                String googleEmail = googleUsers.getEmail();

                if (googleEmail != null && !googleEmail.isEmpty()) {
                    // Lakukan pemeriksaan di database
                    retrofitclient.getConnection().create(RetrofitEndPoint.class)
                            .logingoogle(googleEmail)
                            .enqueue(new Callback<userrespons>() {
                                @Override
                                public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {

                                        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();

                                        usermodel user = response.body().getData();
                                        editor.putString("id_akun", user.getIdakun());
                                        editor.putString("email", user.getEmail());
                                        editor.putString("nama_user", user.getNama_user());
                                        editor.putString("alamat", user.getAlamat_user());
                                        editor.putString("no_telp",user.getNotelp_user());
                                        editor.putString("user_foto",user.getUserfoto());
                                        editor.putString("id_umkm", user.getIdumkm());
                                        editor.apply();
                                        // Login berhasil, lanjutkan ke aktivitas berikutnya
                                        Intent intent = new Intent(Login.this, MainActivity.class);
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
                } else {
                    // Email dari akun Google kosong atau tidak tersedia
                    Toast.makeText(getApplicationContext(), "Email tidak tersedia", Toast.LENGTH_SHORT).show();
                }
            }


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        googleUsers.onActivityResult(requestCode, resultCode, data);
//    }
}


}
