package com.sugeng.unjuk.Menu.Profilmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sugeng.unjuk.Model.produkmodel;
import com.sugeng.unjuk.R;

import java.util.ArrayList;
import java.util.List;

public class Recyclerview_produk extends RecyclerView.Adapter<Recyclerview_produk.RecyclerviewHolder> {
List<produkmodel> data;
    private SharedPreferences sharedPreferences;

    public Recyclerview_produk(ArrayList<produkmodel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Recyclerview_produk.RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview_produk, parent, false);
        return new  RecyclerviewHolder(itemview);
//        sharedPreferences = itemview.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();


    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerview_produk.RecyclerviewHolder holder, int position) {
        produkmodel item = data.get(position);
        holder.namaproduk.setText(item.getNamaproduk());
        holder.hargaproduk.setText(item.getHargaproduk());
//        holder.fotoproduk.setImageURI(item.getGambarproduk1());

    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder {

        private TextView namaproduk, hargaproduk;
        ImageView fotoproduk;
        ImageButton btnhapus;
        //vysor


        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            namaproduk =itemView.findViewById(R.id.nama_produk);
            hargaproduk = itemView.findViewById(R.id.Harga_produk);
            fotoproduk = itemView.findViewById(R.id.image_Produk);
            btnhapus = itemView.findViewById(R.id.btn_hapus);

        }
    }
}