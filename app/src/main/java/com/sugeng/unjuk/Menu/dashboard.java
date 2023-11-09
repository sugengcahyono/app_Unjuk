package com.sugeng.unjuk.Menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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
    ImageButton btnhapus;
    private ArrayList<produkmodel> data = new ArrayList<>();
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_dashboard, container, false);
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


        btnhapus = (ImageButton) view.findViewById(R.id.btn_hapus);
        // Inflate the layout for this fragment
        return view;}


}