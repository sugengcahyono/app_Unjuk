package com.sugeng.unjuk;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Profile extends Fragment {

    // ... (kode yang sudah ada)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView nama = (TextView) view.findViewById(R.id.text);
        Button editprofil = (Button) view.findViewById(R.id.btn_EditProfil); // Menghubungkan tombol dengan kode Java
        Button Dataumkm = (Button) view.findViewById(R.id.btn_Dataumkm);
        // Menambahkan aksi ketika tombol diklik
        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tambahkan kode untuk berpindah ke EditProfile ketika tombol diklik
                Intent intent = new Intent(getActivity(), Edit_profil.class); // Ganti EditProfile dengan nama Activity atau Fragment yang sesuai
                startActivity(intent);
            }
        });

        Dataumkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataUmkm.class); // Ganti EditProfile dengan nama Activity atau Fragment yang sesuai
                startActivity(intent);
            }
        });


        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null){
            String nama1 = extras.getString("NAMA");
            nama.setText(nama1);
        }

        // Inflate the layout for this fragment
        return view;
    }
}
