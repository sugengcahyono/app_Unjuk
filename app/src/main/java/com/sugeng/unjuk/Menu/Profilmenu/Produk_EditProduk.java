package com.sugeng.unjuk.Menu.Profilmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.sugeng.unjuk.Model.produkmodel;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.dataprodukrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Produk_EditProduk extends AppCompatActivity {
    EditText namaproduk,hargaproduk,deskripsiproduk,pirtproduk,bpomproduk,idhalalproduk;
    Button kategoriButton;

    private ImageView Image1, Image2, Image3;

    private Button btnkategori;
    private String[] pilihankategori = {"Makanan", "Minuman", "Kerajinan", "Jasa"};

    private ImageView uploadfoto1, uploadfoto2, uploadfoto3;
    private ImageView selectedImageView;
    private int uploadedImageCount = 0;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_edit_produk);

        namaproduk = findViewById(R.id.input_namaproduk);
        hargaproduk = findViewById(R.id.input_hargaproduk);
        deskripsiproduk = findViewById(R.id.input_deskripsiproduk);
        kategoriButton = findViewById(R.id.btnkategori);
        pirtproduk = findViewById(R.id.input_pirtproduk);
        bpomproduk = findViewById(R.id.input_bpomproduk);
        idhalalproduk = findViewById(R.id.input_idhalalproduk);


        Intent intent =getIntent();
//        String nama = intent.getStringExtra("nama_produk");
        String id_produk = intent.getStringExtra("id_produk");




         retrofitclient.getConnection().create(RetrofitEndPoint.class)
                 .ambildataproduk(id_produk).enqueue(new Callback<dataprodukrespons>() {
             @Override
             public void onResponse(Call<dataprodukrespons> call, Response<dataprodukrespons> response) {
                 if (response.body() != null && response.body().getStatus().equals("success")) {
                     produkmodel produk = (produkmodel) response.body().getData().get(0);
                     Log.e("muncul", produk.getNamaproduk());

                     namaproduk.setText(produk.getNamaproduk());
                     hargaproduk.setText(produk.getHargaproduk());
                     kategoriButton.setText(produk.getKategoriproduk());
                     deskripsiproduk.setText(produk.getDeskripsiproduk());
                     pirtproduk.setText(produk.getPirtproduk());
                     bpomproduk.setText(produk.getBpomproduk());
                     idhalalproduk.setText(produk.getIdhalalproduk());
                 }else {
                     Toast.makeText(Produk_EditProduk.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                 }

             }
             @Override
             public void onFailure(Call<dataprodukrespons> call, Throwable t) {
                 Toast.makeText(Produk_EditProduk.this, t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });


        ImageButton backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //button kategori
        kategoriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.widget.PopupMenu popupMenu = new android.widget.PopupMenu(Produk_EditProduk.this, btnkategori);
                Menu menu = popupMenu.getMenu();
                for (String kecamatan : pilihankategori) {
                    menu.add(kecamatan);
                }

                popupMenu.setOnMenuItemClickListener(new android.widget.PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String pilihanKecamatan = item.getTitle().toString();
                        kategoriButton.setText(pilihanKecamatan); // Mengisi Button dengan pilihan yang dipilih
                        return true;
                    }
                });

                popupMenu.show();
            }
        });


        kategoriButton = findViewById(R.id.btnkategori);
        btnkategori = findViewById(R.id.btnkategori);
        uploadfoto1 = findViewById(R.id.uploadfoto1);
        uploadfoto2 = findViewById(R.id.uploadfoto2);
        uploadfoto3 = findViewById(R.id.uploadfoto3);

//        Button update = findViewById(R.id.Btnupload);
//        update.setOnClickListener();


//upload produk foto
        uploadfoto1 = findViewById(R.id.uploadfoto1);
        uploadfoto2 = findViewById(R.id.uploadfoto2);
        uploadfoto3 = findViewById(R.id.uploadfoto3);

        setImageViewClickListeners();
    }

    private void setImageViewClickListeners() {
        uploadfoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageViewClick(uploadfoto1);
            }
        });

        uploadfoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageViewClick(uploadfoto2);
            }
        });

        uploadfoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageViewClick(uploadfoto3);
            }
        });
    }

    private void handleImageViewClick(ImageView imageView) {
        selectedImageView = imageView;
        openGallery();
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            if (selectedImageView != null) {
                Uri imageUri = data.getData();
                selectedImageView.setImageURI(imageUri);
            }
        }
    }


}



