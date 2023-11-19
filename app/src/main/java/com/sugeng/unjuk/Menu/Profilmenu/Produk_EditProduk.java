package com.sugeng.unjuk.Menu.Profilmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import com.bumptech.glide.Glide;
import com.sugeng.unjuk.Model.produkmodel;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.dataprodukrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Produk_EditProduk extends AppCompatActivity {
    EditText namaproduk,hargaproduk,deskripsiproduk,pirtproduk,bpomproduk,idhalalproduk;
    Button kategoriButton;

    private ImageView uri;

    private Uri uriImage1, uriImage2, uriImage3;

    private ImageView Image1, Image2, Image3;

    private Button btnkategori, simpanproduk;
    private String[] pilihankategori = {"Makanan", "Minuman", "Kerajinan", "Jasa"};

    private ImageView uploadfoto1, uploadfoto2, uploadfoto3;
    private ImageView selectedImageView;
    private int uploadedImageCount = 0;
    private static final int PICK_IMAGE = 1;

    private int handler = 0;

    private Bitmap bitImage1, bitImage2, bitImage3;

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
        simpanproduk = findViewById(R.id.btn_simpaneditproduk);




//button simpan produk
        simpanproduk.setOnClickListener(new View.OnClickListener() {
            Intent intent = getIntent();
            String id_produk = intent.getStringExtra("id_produk");

            @Override
            public void onClick(View view) {
                String data1, data2, data3;
                if (bitImage1 != null) {
                    data1 = ImageUtil.bitmapToBase64String(bitImage1, 100);
                } else {
                    // Ambil foto dari data yang sudah diambil sebelumnya
                    data1 = ImageUtil.bitmapToBase64String(((BitmapDrawable) uploadfoto1.getDrawable()).getBitmap(), 100);
                }

                if (bitImage2 != null) {
                    data2 = ImageUtil.bitmapToBase64String(bitImage2, 100);
                } else {
                    // Ambil foto dari data yang sudah diambil sebelumnya
                    data2 = ImageUtil.bitmapToBase64String(((BitmapDrawable) uploadfoto2.getDrawable()).getBitmap(), 100);
                }

                if (bitImage3 != null) {
                    data3 = ImageUtil.bitmapToBase64String(bitImage3, 100);
                } else {
                    // Ambil foto dari data yang sudah diambil sebelumnya
                    data3 = ImageUtil.bitmapToBase64String(((BitmapDrawable) uploadfoto3.getDrawable()).getBitmap(), 100);
                }

                retrofitclient.getConnection().create(RetrofitEndPoint.class)
                        .Btn_simpaneditproduk(
                                id_produk, namaproduk.getText().toString(), hargaproduk.getText().toString(),
                                kategoriButton.getText().toString(), deskripsiproduk.getText().toString(), pirtproduk.getText().toString(),
                                bpomproduk.getText().toString(), idhalalproduk.getText().toString(),
                                data1, data2, data3
                        ).enqueue(new Callback<dataprodukrespons>() {
                            @Override
                            public void onResponse(Call<dataprodukrespons> call, Response<dataprodukrespons> response) {
                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                    Toast.makeText(Produk_EditProduk.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Produk_EditProduk.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<dataprodukrespons> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
            }
        });




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

                     Glide.with(Produk_EditProduk.this)
                             .load(retrofitclient.PRODUK_PHOTO_URL + produk.getGambarproduk1())
                             .centerCrop()
                             .placeholder(R.drawable.ic_launcher_foreground)
                             .into(uploadfoto1);

                     Glide.with(Produk_EditProduk.this)
                             .load(retrofitclient.PRODUK_PHOTO_URL + produk.getGambarproduk2())
                             .centerCrop()
                             .placeholder(R.drawable.ic_launcher_foreground)
                             .into(uploadfoto2);

                     Glide.with(Produk_EditProduk.this)
                             .load(retrofitclient.PRODUK_PHOTO_URL + produk.getGambarproduk3())
                             .centerCrop()
                             .placeholder(R.drawable.ic_launcher_foreground)
                             .into(uploadfoto3);

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
//        uploadfoto1 = findViewById(R.id.uploadfoto1);
//        uploadfoto2 = findViewById(R.id.uploadfoto2);
//        uploadfoto3 = findViewById(R.id.uploadfoto3);

        setImageViewClickListeners();
    }

    private void setImageViewClickListeners() {
        uploadfoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageViewClick(uploadfoto1);
                handler = 1;
            }
        });

        uploadfoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageViewClick(uploadfoto2);
                handler = 2;
            }
        });

        uploadfoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageViewClick(uploadfoto3);
                handler = 3;
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

                switch (handler) {
                    case 1: {
                        uriImage1 = data.getData();
                        break;
                    }
                    case 2: {
                        uriImage2 = data.getData();
                        break;
                    }
                    case 3: {
                        uriImage3 = data.getData();
                        break;
                    }
                }


                try {

                    switch (handler) {
                        case 1: {
                            bitImage1 = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage1);
                            break;
                        }
                        case 2: {
                            bitImage2 = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage2);
                            break;
                        }
                        case 3: {
                            bitImage3 = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage3);
                            break;
                        }
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}



