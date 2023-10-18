package com.sugeng.unjuk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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