package com.sugeng.unjuk.Menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sugeng.unjuk.Login.Login;
import com.sugeng.unjuk.Login.SesionManager;
import com.sugeng.unjuk.Menu.Profilmenu.Produk_EditProduk;
import com.sugeng.unjuk.Menu.Profilmenu.Recyclerview_produk;
import com.sugeng.unjuk.Model.produkmodel;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.dataprodukrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dashboard extends Fragment {
    private View view;
    ImageButton btnhapus, pencarian;
    EditText cariproduksaya;
    private ArrayList<produkmodel> data = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    SesionManager sesionManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        pencarian = view.findViewById(R.id.btn_pencarian);
        cariproduksaya = view.findViewById(R.id.text_cariproduksaya);


        sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String id_umkm = sharedPreferences.getString("id_umkm","");


        RecyclerView recyclerView = view.findViewById(R.id.viewproduk);

        RetrofitEndPoint apiRequestData = retrofitclient.getConnection().create(RetrofitEndPoint.class);
        Call<dataprodukrespons> call = apiRequestData.ambilproduk(id_umkm);





        call.enqueue(new Callback<dataprodukrespons>() {

            @Override
            public void onResponse(Call<dataprodukrespons> call, Response<dataprodukrespons> response) {
                dataprodukrespons dataprodukrespons = response.body();
                if (dataprodukrespons != null){
                    ArrayList<produkmodel> list = (ArrayList<produkmodel>) dataprodukrespons.getData();
                    if(list != null) {

                        data.addAll(list);
                        Recyclerview_produk adapter = new Recyclerview_produk(response.body().getData(),getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<dataprodukrespons> call, Throwable t) {
                t.printStackTrace();
            }
        });



        Recyclerview_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 SharedPreferences prefproduk = getActivity().getSharedPreferences("prefProduk", Context.MODE_PRIVATE);
                String idproduk = prefproduk.getString("id_produk","");
                String namaproduk = prefproduk.getString("nama_produk", "kosong");
                String gambarproduk1 = prefproduk.getString("gambar_produk1", "");


                Intent buka = new Intent(getActivity(), Produk_EditProduk.class);

                buka.putExtra("id_produk", idproduk);
                buka.putExtra("nama_produk",namaproduk);
                buka.putExtra("gambar_produk1", gambarproduk1 );
                startActivity(buka);
            }


        });

//tombol pencarian
        pencarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = cariproduksaya.getText().toString();
                cariProdukDenganKeyword(keyword);
            }
        });

//tombol enter keyboard
        cariproduksaya.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Tombol Enter ditekan
                    String keyword = cariproduksaya.getText().toString();
                    cariProdukDenganKeyword(keyword);
                    return true;
                }
                return false;
            }
        });




        btnhapus =  view.findViewById(R.id.btn_hapus);
        // Inflate the layout for this fragment


       sesionManager = new SesionManager(requireContext());
       if (!sesionManager.isLoggedIn()) {
           moveToLogin();
       }

        return view;}

    private void moveToLogin() {
        Intent intent = new Intent(requireContext(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        requireActivity().finish();
    }

//method untuk pencarian produk saya
    private void cariProdukDenganKeyword(String keyword) {

        sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String id_umkm = sharedPreferences.getString("id_umkm","");
//         keyword = cariproduksaya.getText().toString();

        RetrofitEndPoint cariproduk = retrofitclient.getConnection().create(RetrofitEndPoint.class);
        Call<dataprodukrespons> cari = cariproduk.cariproduksaya(id_umkm, keyword);

        cari.enqueue(new Callback<dataprodukrespons>() {
            @Override
            public void onResponse(Call<dataprodukrespons> call, Response<dataprodukrespons> response) {
                dataprodukrespons dataprodukrespons = response.body();
                if (dataprodukrespons != null) {
                    ArrayList<produkmodel> list = (ArrayList<produkmodel>) dataprodukrespons.getData();
                    RecyclerView recyclerView = view.findViewById(R.id.viewproduk);

                    if (list != null) {
                        data.addAll(list);
                        Recyclerview_produk adapter = new Recyclerview_produk(response.body().getData(), getContext());
                        recyclerView.setAdapter(adapter);

                        // Menampilkan Toast dengan jumlah hasil pencarian
                        if (list.isEmpty()) {
                            showToast("Tidak ada produk ditemukan.");
                        } else {
                            showToast("Ditemukan " + list.size() + " produk.");
                        }
                    } else {
                        // Jika list null, berarti tidak ada produk ditemukan
                        showToast("Tidak ada produk ditemukan.");
                    }
                }

            }

            private void showToast(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<dataprodukrespons> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



}