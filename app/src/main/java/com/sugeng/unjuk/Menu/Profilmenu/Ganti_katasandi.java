package com.sugeng.unjuk.Menu.Profilmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.sugeng.unjuk.Login.Register;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.userrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ganti_katasandi extends AppCompatActivity {

    EditText passbaru, passlama, konfrpass;
    Button simpan;
    TextInputLayout inputlayoutsandilama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_katasandi);

        ImageButton backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//helpertext kata sandi baru
        passbaru = findViewById(R.id.text_katasandibaru);
        passlama = findViewById(R.id.text_katasandilama);
        konfrpass = findViewById(R.id.text_konfirmasikatasandibaru);
        simpan = findViewById(R.id.Btnsimpanpass);

        inputlayoutsandilama = findViewById(R.id.textinputlayoutkatasandibaru);

//button passbaru
        passbaru.addTextChangedListener(new TextWatcher() {
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
                        inputlayoutsandilama.setHelperText("Password Anda Kuat");
                        inputlayoutsandilama.setError("");
                    } else {
                        inputlayoutsandilama.setError("Campur huruf (huruf besar dan kecil), angka, dan simbol");
                    }
                } else {
                    inputlayoutsandilama.setHelperText("Kata sandi harus setidaknya 8 karakter");
                    inputlayoutsandilama.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//button Siimpan
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                retrofitclient.getInstance().ubahkatasandi_profil(
                        sharedPreferences.getString("email", ""),
                        passlama.getText().toString(),
                        passbaru.getText().toString()
                ).enqueue(new Callback<userrespons>() {
                    @Override
                    public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                        if (passbaru.getText().toString().equalsIgnoreCase(konfrpass.getText().toString())){
                        if (response.body() != null) {
                            if (response.body().getStatus().equalsIgnoreCase("success")) {
                                passlama.setText("");
                                passbaru.setText("");
                                konfrpass.setText("");
                                Toast.makeText(Ganti_katasandi.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                // Penanganan kondisi jika status bukan "success"
                                Toast.makeText(Ganti_katasandi.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Penanganan kondisi jika respons body null
                            Toast.makeText(Ganti_katasandi.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(Ganti_katasandi.this, "Maaf konfirmasi kata sandi tidak sama", Toast.LENGTH_SHORT).show();
                    }
                    }

                    @Override
                    public void onFailure(Call<userrespons> call, Throwable t) {
                        // Penanganan kondisi jika terjadi kegagalan (failure)
                        Toast.makeText(Ganti_katasandi.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }


}