package com.sugeng.unjuk.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.otpview.OTPListener;
import com.otpview.OTPTextView;
import com.sugeng.unjuk.R;

import java.util.Objects;

public class kodeotp extends AppCompatActivity {

    public static String OTP = "otp", EMAIL = "email";

    private String kodeOtp;

    private OTPTextView otpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kodeotp);
        otpTextView = findViewById(R.id.votp_inp_otp);

        kodeOtp = getIntent().getStringExtra(OTP);


        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(@NonNull String s) {
                if (Objects.equals(otpTextView.getOtp(), kodeotp.this.kodeOtp)){
                    startActivity(
                            new Intent(kodeotp.this, Konfirmasi_password_baru.class)
                                    .putExtra(Konfirmasi_password_baru.EMAIL, getIntent().getStringExtra(EMAIL))
                    );
                }else {
                    Toast.makeText(kodeotp.this, "OTP SALAH", Toast.LENGTH_SHORT).show();
                }
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