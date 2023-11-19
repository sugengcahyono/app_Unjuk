package com.sugeng.unjuk.Menu.Profilmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

public class Recyclerview_produk extends RecyclerView.Adapter<Recyclerview_produk.RecyclerviewHolder> {
List<produkmodel> data;
public static View.OnClickListener clicklistener;

    private Context context;

    private SharedPreferences sharedPreferences;

    public Recyclerview_produk(ArrayList<produkmodel> data, Context context ) {
        this.context = context;
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
        holder.setImage(context, retrofitclient.PRODUK_PHOTO_URL + item.getGambarproduk1());
//        holder.fotoproduk.setImageURI(item.getGambarproduk1());
//        holder.idproduk.setText(item.getIdproduk());

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
//
                        editor.putString("gambar_produk1", clickedItem.getGambarproduk1());
//
                        editor.apply();

                       clicklistener.onClick(view);

                    }
                }
            }
        });

        // Hapus produk
        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog(item.getIdproduk());
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
//            idproduk = itemView.findViewById(R.id.id_produk);

        }

        private void setImage(Context context, String url){
            System.out.println(url);
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(fotoproduk);
        }
    }

    private void showDeleteConfirmationDialog(final String idProduk) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Apakah Anda yakin ingin menghapus produk ini?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Tindakan jika pengguna memilih "Ya" (misalnya, hapus produk)
                        // Panggil metode untuk menghapus produk dengan ID tertentu
                         deleteProduct(idProduk);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Tindakan jika pengguna memilih "Tidak" atau keluar dari dialog
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteProduct(String idProduk) {
        // Lakukan panggilan Retrofit untuk menghapus produk berdasarkan idProduk
        RetrofitEndPoint delete = retrofitclient.getConnection().create(RetrofitEndPoint.class);
        Call<dataprodukrespons> call = delete.Hapusproduksaya(idProduk);

        call.enqueue(new Callback<dataprodukrespons>() {

            @Override
            public void onResponse(Call<dataprodukrespons> call, Response<dataprodukrespons> response) {
                if (response.isSuccessful()) {
                    // Produk berhasil dihapus dari database, Anda dapat memperbarui daftar produk di RecyclerView.
                    // Misalnya, Anda dapat memanggil metode yang memperbarui RecyclerView setelah penghapusan berhasil.
//                    updateRecyclerViewAfterDeletion();

                    // Tampilkan pesan Toast sukses
                    Toast.makeText(context, "Produk berhasil dihapus", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle kesalahan jika penghapusan produk gagal.
                    // Tampilkan pesan Toast gagal
                    Toast.makeText(context, "Gagal menghapus produk", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dataprodukrespons> call, Throwable t) {
                // Handle kesalahan jaringan atau lainnya jika terjadi kesalahan dalam permintaan.
                // Tampilkan pesan Toast kesalahan
                Toast.makeText(context, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}