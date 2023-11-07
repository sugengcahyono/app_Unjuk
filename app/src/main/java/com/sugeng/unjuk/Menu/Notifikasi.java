package com.sugeng.unjuk.Menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.sugeng.unjuk.R;


public class Notifikasi extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifikasi, container, false);

        MaterialButton buttonKlinikUMKM = view.findViewById(R.id.Btn_layanan);
        buttonKlinikUMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tautan yang akan dibuka
                String url = "https://forms.gle/x1r1v2UeVDaYXkpP7"; // Ganti dengan URL yang sesuai

                // Membuat intent untuk membuka tautan
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Memulai aktivitas untuk membuka tautan
                startActivity(intent);
            }
        });

        return view;
    }
    }
