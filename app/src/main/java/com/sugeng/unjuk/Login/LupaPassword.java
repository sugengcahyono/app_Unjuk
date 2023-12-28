package com.sugeng.unjuk.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.VerifyResponse;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LupaPassword extends AppCompatActivity {

    ProgressBar progressBar;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        // Menghubungkan tombol dengan kode Java
        Button disini = findViewById(R.id.Btnlanjut);
        EditText inputemail = findViewById(R.id.text_emailsandi);
        progressBar = findViewById(R.id.Progresbar);

        progressBar.setVisibility(View.GONE);

// Menambahkan onClickListener untuk tombol kirim otp dan menuju ke form otp
        disini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Menampilkan ProgressBar saat tombol diklik
                progressBar.setVisibility(View.VISIBLE);
                retrofitclient.getInstance().verifikasi(
                        inputemail.getText().toString(), "ForgotPass", "new"
                ).enqueue(new Callback<VerifyResponse>() {
                    @Override
                    public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {

                        // Sembunyikan ProgressBar setelah mendapatkan respons
                        progressBar.setVisibility(View.GONE);
                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                            startActivity(
                                    new Intent(LupaPassword.this, kodeotp.class)
                                            .putExtra(kodeotp.OTP, response.body().getData().getOtp())
                                            .putExtra(kodeotp.EMAIL, inputemail.getText().toString())

                            );
                            Toast.makeText(LupaPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
                // Gantilah "YourDestinationActivity.class" dengan kelas aktivitas tujuan Anda
                Intent intent = new Intent(LupaPassword.this, Login.class);
                startActivity(intent);
                finish(); // Opsional: menutup aktivitas saat ini jika diperlukan
            }
        });

    }

    @Override
    public void onBackPressed() {
        // Uncomment baris di bawah ini untuk menjalankan perilaku default tombol kembali
        // super.onBackPressed();

        // Redirect ke activity lain untuk memasukkan alamat email
        // Gantilah "YourOtherActivity.class" dengan kelas activity tujuan
        Intent intent = new Intent(LupaPassword.this, Login.class);
        startActivity(intent);
        // Optional: Tambahkan finish() jika ingin menutup aktivitas saat ini
        // finish();
    }

}