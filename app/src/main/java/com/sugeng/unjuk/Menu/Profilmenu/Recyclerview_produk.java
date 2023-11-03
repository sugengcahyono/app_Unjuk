package com.sugeng.unjuk.Menu.Profilmenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sugeng.unjuk.Model.produkmodel;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.dataprodukrespons;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class Recyclerview_produk extends RecyclerView.Adapter<Recyclerview_produk.RecyclerviewHolder> {
List<produkmodel> data;
public static View.OnClickListener clicklistener;
    private SharedPreferences sharedPreferences;

    public Recyclerview_produk(ArrayList<produkmodel> data, Context context ) {
        this.sharedPreferences = context.getSharedPreferences("prefProduk", Context.MODE_PRIVATE);
        this.data = data;

    }

    public static void setOnClickListener(View.OnClickListener listener){
        clicklistener = listener;
    }


    @NonNull
    @Override
    public Recyclerview_produk.RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview_produk, parent, false);
        return new  RecyclerviewHolder(itemview);
//
//        editor = sharedPreferences.edit();


    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerview_produk.RecyclerviewHolder holder, int position) {
        produkmodel item = data.get(position);
        holder.namaproduk.setText(item.getNamaproduk());
        holder.hargaproduk.setText(item.getHargaproduk());
//        holder.fotoproduk.setImageURI(item.getGambarproduk1());
        holder.idproduk.setText(item.getIdproduk());

        //klik produk
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicklistener != null) {
                    int position = holder.getPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        produkmodel clickedItem = data.get(position);

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id_produk", clickedItem.getIdproduk());
                        editor.putString("nama_produk", clickedItem.getNamaproduk());
                        editor.putString("harga_produk", clickedItem.getHargaproduk());
//                        editor.putString("kategori_produk", clickedItem.getKategoriproduk());
//                        editor.putString("deskripsi_produk", clickedItem.getDeskripsiproduk());
//                        editor.putString("pirt_produk", clickedItem.getPirtproduk());
//                        editor.putString("bpom_produk", clickedItem.getBpomproduk());
//                        editor.putString("id_halal", clickedItem.getIdhalalproduk());
//                        editor.putString("gambar_produk1", clickedItem.getGambarproduk1());
//                        editor.putString("gambar_produk2", clickedItem.getGambarproduk2());
//                        editor.putString("gambar_produk3", clickedItem.getGambarproduk3());
                        editor.apply();

                       clicklistener.onClick(view);

                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder {

        private TextView namaproduk, hargaproduk, idproduk;
        ImageView fotoproduk;
        ImageButton btnhapus;
        //vysor


        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);
            namaproduk =itemView.findViewById(R.id.nama_produk);
            hargaproduk = itemView.findViewById(R.id.Harga_produk);
            fotoproduk = itemView.findViewById(R.id.image_Produk);
            btnhapus = itemView.findViewById(R.id.btn_hapus);
            idproduk = itemView.findViewById(R.id.id_produk);

        }
    }
}