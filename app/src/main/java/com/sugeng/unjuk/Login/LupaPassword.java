package com.sugeng.unjuk.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.VerifyResponse;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                retrofitclient.getInstance().verifikasi(
                        inputemail.getText().toString(), "ForgotPass", "new"
                ).enqueue(new Callback<VerifyResponse>() {
                    @Override
                    public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {
                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                            startActivity(
                                    new Intent(LupaPassword.this, kodeotp.class)
                                            .putExtra(kodeotp.OTP, response.body().getData().getOtp())
                                            .putExtra(kodeotp.EMAIL, inputemail.getText().toString())
                            );
                        }else {
                            Toast.makeText(LupaPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
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