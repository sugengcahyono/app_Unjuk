package com.sugeng.unjuk.Menu.Profilmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.sugeng.unjuk.Model.umkmmodel;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.dataumkmrespons;
import com.sugeng.unjuk.Respons.userrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataUmkm extends AppCompatActivity {

    private Button kecamatanButton;
    private Button btnKecamatan; // Button untuk menampilkan pilihan kecamatan
    private Button jenisusahaButton;
    private Button btnjenisusaha; // Button untuk menampilkan jenis usaha
    private EditText inputnamaumkm, inputnibumkm, inputnohpumkm, inputalamatumkm;

    private Button btnsimpandataumkm;




    private ImageView fotoProfil;
    private static final int PICK_IMAGE = 1;
    private TextView namaumkm;
    private Uri uri;

    private EditText namauser, notelpuser, alamatuser;
    String umkmFotoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_umkm);

        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String idakun = sharedPreferences.getString("idakun", "");
        String email = sharedPreferences.getString("email", "");
        String nama = sharedPreferences.getString("nama_user", "");
        String alamat = sharedPreferences.getString("alamat", "");
        String notelp = sharedPreferences.getString("no_telp", "");
        String userfoto = sharedPreferences.getString("user_foto", "");
        String idUmkm = sharedPreferences.getString("id_umkm", "");
        String umkmfoto = sharedPreferences.getString("umkm_foto", "");

        inputnamaumkm = findViewById(R.id.input_namaumkm);
        inputnibumkm = findViewById(R.id.input_nibumkm);
        inputnohpumkm = findViewById(R.id.input_nohpumkm);
        inputalamatumkm = findViewById(R.id.input_alamatumkm);

        String userFotoBase64 = sharedPreferences.getString("umkm_foto", ""); // Gantilah ini sesuai dengan cara Anda menyimpan Base64 gambar profil

        if (!userFotoBase64.isEmpty()) {
            byte[] imageBytes = Base64.decode(userFotoBase64, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            fotoProfil.setImageBitmap(bitmap);
        } else {
            // Handle jika Base64 gambar profil kosong atau tidak valid
        }


        retrofitclient.getConnection().create(RetrofitEndPoint.class).Data_umkm(sharedPreferences.getString("id_akun", "")).enqueue(new Callback<dataumkmrespons>() {
            @Override
            public void onResponse(Call<dataumkmrespons> call, Response<dataumkmrespons> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                    umkmmodel user = response.body().getData();

                    inputnamaumkm.setText(user.getNamaumkm());
                    jenisusahaButton.setText(user.getJenisusahaumkm());
                    inputnibumkm.setText(user.getNib());
                    inputnohpumkm.setText(user.getNotelpumkm());
                    kecamatanButton.setText(user.getKecamatanumkm());
                    inputalamatumkm.setText(user.getAlamatumkm());

                    umkmFotoURL = user.getUmkmfoto(); // Gantilah ini sesuai dengan cara Anda mengambil URL gambar profil dari respons server

                    if (!umkmFotoURL.isEmpty()) {
                        Picasso.get().load(umkmFotoURL).into(fotoProfil);
                    } else {
                        // Handle jika URL gambar profil kosong atau tidak valid
                    }

                    // Tambahkan kode Glide untuk memuat gambar profil
                    Glide.with(DataUmkm.this)
                            .load(retrofitclient.UMKM_PHOTO_URL + user.getUmkmfoto())
                            .into(fotoProfil);

                }else{
                    Toast.makeText(DataUmkm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<dataumkmrespons> call, Throwable t) {

            }
        });
//    Button dimpan data umkm
        btnsimpandataumkm = findViewById(R.id.Btn_Simpandataumkm);

        btnsimpandataumkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofitclient.getConnection().create(RetrofitEndPoint.class)
                        .Btn_simpanumkm(
                                "",
                                inputnamaumkm.getText().toString(),
                                jenisusahaButton.getText().toString(),
                                inputnibumkm.getText().toString(),
                                inputnohpumkm.getText().toString(),
                                kecamatanButton.getText().toString(),
                                inputalamatumkm.getText().toString(),
                                umkmFotoURL
                                , sharedPreferences.getString("id_akun", "")
                        ).enqueue(new Callback<dataumkmrespons>() {
                            @Override
                            public void onResponse(Call<dataumkmrespons> call, Response<dataumkmrespons> response) {
                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                                    umkmmodel user = response.body().getData();
                                    if (user != null) {
                                        inputnamaumkm.setText(user.getNamaumkm());
                                        jenisusahaButton.setText(user.getJenisusahaumkm());
                                        inputnibumkm.setText(user.getNib());
                                        inputnohpumkm.setText(user.getNotelpumkm());
                                        kecamatanButton.setText(user.getKecamatanumkm());
                                        inputalamatumkm.setText(user.getAlamatumkm());

                                        // Mengambil foto profil dan menampilkannya dengan Picasso
                                        umkmFotoURL = user.getUmkmfoto();
                                        if (umkmFotoURL != null && !umkmFotoURL.isEmpty()) {
                                            Picasso.get().load(umkmFotoURL).into(fotoProfil);
                                        }
                                    }
                                    Toast.makeText(DataUmkm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DataUmkm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<dataumkmrespons> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });

                if (uri != null) {
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(DataUmkm.this.getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String encoded = ImageUtil.bitmapToBase64String(bitmap, 100);
                    uploadPhoto(encoded);
                } else {

                }

            }
        });

        // Mengatur pilihan jenis usaha
        jenisusahaButton = findViewById(R.id.btn_jenisusaha);
        btnjenisusaha = findViewById(R.id.btn_jenisusaha);

        String[] pilihanjenisusaha = {"Makanan", "Minuman", "Kerajinan", "Jasa"};

        btnjenisusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(DataUmkm.this, btnjenisusaha);
                Menu menu = popupMenu.getMenu();
                for (String jenisusaha : pilihanjenisusaha) {
                    menu.add(jenisusaha);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String pilihanjenisusaha = item.getTitle().toString();
                        jenisusahaButton.setText(pilihanjenisusaha); // Mengisi Button dengan pilihan yang dipilih
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        // Mengatur button kembali
        ImageButton backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Mengatur button pilihan kecamatan
        kecamatanButton = findViewById(R.id.btnKecamatan);
        btnKecamatan = findViewById(R.id.btnKecamatan);
        String[] pilihanKecamatan = {"Bagor", "Baron", "Berbek", "Gondang", "Jatikalen", "Kertosono",
                "Lengkong", "Loceret", "Nganjuk", "Ngetos", "Ngluyu", "Ngronggot", "Pace", "Patianrowo",
                "Prambon", "Rejoso", "Sawahan", "Sukomoro", "Tanjunganom", "Wilangan"};

        // Mengatur onClickListener pada Button untuk menampilkan PopupMenu
        btnKecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(DataUmkm.this, btnKecamatan);
                Menu menu = popupMenu.getMenu();
                for (String kecamatan : pilihanKecamatan) {
                    menu.add(kecamatan);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String pilihanKecamatan = item.getTitle().toString();
                        kecamatanButton.setText(pilihanKecamatan); // Mengisi Button dengan pilihan yang dipilih
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

// Menambahkan foto umkm
        TextView ubahFotoumkm = findViewById(R.id.btn_ubahfotoumkm);
        fotoProfil = findViewById(R.id.foto_umkm);

        ubahFotoumkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(DataUmkm.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != 0) {
                    ActivityCompat.requestPermissions(DataUmkm.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE);
                } else {
                    openGallery();
                }
            }
        });


    }
    private void uploadPhoto(String imgBase64) {
        retrofitclient.getConnection().create(RetrofitEndPoint.class)
                .Update_photoumkm(inputnamaumkm.getText().toString(), imgBase64)
                .enqueue(new Callback<dataumkmrespons>() {
                    @Override
                    public void onResponse(Call<dataumkmrespons> call, Response<dataumkmrespons> response) {
                        if (response.body() != null && response.body().getMessage().equalsIgnoreCase("success")) {
                            Glide.with(DataUmkm.this)
                                    .load(retrofitclient.UMKM_PHOTO_URL+ response.body().getData().getUmkmfoto())
                                    .into(fotoProfil);
                        } else {
                            Toast.makeText(DataUmkm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<dataumkmrespons> call, Throwable t) {
                        Toast.makeText(DataUmkm.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            uri = data.getData();
            fotoProfil.setImageURI(uri);
        }
    }
}
