package com.sugeng.unjuk.Login;

import static java.util.jar.Pack200.Packer.PASS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.otpview.OTPListener;
import com.otpview.OTPTextView;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.userrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class otp_register extends AppCompatActivity {
    private Button btnselesai;

    private String OTP = "otp";

    private OTPTextView otpTextView;

    public static String  USERNAME = "nama_user",EMAIL = "email",  KATASANDSI = "password", NOHP = "nohpuser", ALAMAT = "alamatuser", LEVEL = "leveluser";

    private String dataOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_register);

        btnselesai = findViewById(R.id.Btnlanjut);
        otpTextView = findViewById(R.id.votp_inp_otp);

        dataOtp = getIntent().getStringExtra(OTP);

        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(@NonNull String s) {

                if (dataOtp.equals(otpTextView.getOtp())) {
                    Toast.makeText(otp_register.this, "OTP Valid", Toast.LENGTH_SHORT).show();

                    retrofitclient.getConnection().create(RetrofitEndPoint.class).Unjuk(getIntent().getStringExtra(USERNAME), getIntent().getStringExtra(EMAIL), getIntent().getStringExtra(KATASANDSI),
                                    getIntent().getStringExtra(NOHP),getIntent().getStringExtra(ALAMAT),getIntent().getStringExtra(LEVEL))
                            .enqueue(new Callback<userrespons>() {
                                @Override
                                public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                                        Toast.makeText(otp_register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(otp_register.this, Login.class));
                                    }

                                }

                                @Override
                                public void onFailure(Call<userrespons> call, Throwable t) {

                                }
                            });
                }

            }
        });


       btnselesai.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {selesai();

           }
       });


    }
    public void selesai() {
        Intent intent = new Intent(otp_register.this, Login.class);
        startActivity(intent);
    }

    }
