package com.sugeng.unjuk.Menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sugeng.unjuk.Login.Login;
import com.sugeng.unjuk.Login.SesionManager;
import com.sugeng.unjuk.Menu.Profilmenu.DataUmkm;
import com.sugeng.unjuk.Menu.Profilmenu.Edit_profil;
import com.sugeng.unjuk.Menu.Profilmenu.Ganti_katasandi;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.userrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;
import com.sugeng.unjuk.Model.usermodel;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class Profile extends Fragment {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private CircleImageView fotoProfil;
    SesionManager sesionManager;
    Button Keluar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView email = (TextView) view.findViewById(R.id.text_email);
        TextView namauser = (TextView) view.findViewById(R.id.text_namauser);
        Button editprofil = (Button) view.findViewById(R.id.btn_EditProfil); // Menghubungkan tombol dengan kode Java
        Button Dataumkm = (Button) view.findViewById(R.id.btn_Dataumkm);
        Button Gantisandi = (Button) view.findViewById(R.id.btn_Gantikatasandi);
        CircleImageView fotoProfil = (CircleImageView) view.findViewById(R.id.foto_profiluser);
        Keluar = view.findViewById(R.id.Btn_keluar);

        sharedPreferences = getContext().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

//session Manager
        sesionManager = new SesionManager(requireContext());

        Keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutConfirmationDialog();
            }
        });



//menampilkan foto profil
        Glide.with(Profile.this)
                .load(retrofitclient.USER_PHOTO_URL+ sharedPreferences.getString("user_foto", ""))
                .into(fotoProfil);

        retrofitclient.getConnection().create(RetrofitEndPoint.class).Profil(sharedPreferences.getString("id_akun","")).enqueue(new Callback<userrespons>() {
            @Override
            public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                    usermodel profil = response.body().getData();

                    email.setText(profil.getEmail());
                    namauser.setText(profil.getNama_user());
                    Glide.with(Profile.this)
                            .load(retrofitclient.USER_PHOTO_URL + profil.getUserfoto())
                            .into(fotoProfil);

                }else{
//                    Toast.makeText(Profile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<userrespons> call, Throwable t) {

            }
        });




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
//klik ganti katasandi
        Gantisandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tambahkan kode untuk berpindah ke EditProfile ketika tombol diklik
                Intent intent = new Intent(getActivity(), Ganti_katasandi.class); // Ganti EditProfile dengan nama Activity atau Fragment yang sesuai
                startActivity(intent);
            }
        });


        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null){
            String nama1 = extras.getString("NAMA");
            namauser.setText(nama1);
        }

        // Inflate the layout for this fragment
        return view;
    }

// BUtton Keluar
    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Konfirmasi Keluar");
        builder.setMessage("Apakah Anda yakin ingin Keluar dari Akun ini?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lakukan logout jika pengguna memilih "Ya"
                sesionManager.logoutSession();
                moveToLogin();
            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Tidak melakukan apa-apa jika pengguna memilih "Tidak"
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void moveToLogin() {
        // Pindah ke halaman login
        Intent intent = new Intent(requireContext(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

}
