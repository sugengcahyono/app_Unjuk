package com.sugeng.unjuk.Menu.Profilmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.sugeng.unjuk.Model.usermodel;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.userrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Retrofit.retrofitclient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_profil extends AppCompatActivity {


    private ImageView fotoProfil;
    private static final int PICK_IMAGE = 1;
    private Uri uri;

    private EditText namauser, notelpuser, alamatuser;
    private TextView emailuser;
    private ImageView Fotoprofil;
    private Button btnsimpanprofile;
    String userFotoURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String idakun = sharedPreferences.getString("idakun", "");
        String email = sharedPreferences.getString("email", "");
        String nama = sharedPreferences.getString("nama_user", "");
        String alamat = sharedPreferences.getString("alamat", "");
        String notelp = sharedPreferences.getString("no_telp", "");
        String userfoto = sharedPreferences.getString("user_foto", "");
        String idUmkm = sharedPreferences.getString("id_umkm", "");


        emailuser = findViewById(R.id.input_emailuser);
        namauser = findViewById(R.id.input_namauser);
        notelpuser = findViewById(R.id.input_nohandphoneuser);
        alamatuser = findViewById(R.id.input_alamatuser);
        Fotoprofil = findViewById(R.id.foto_profil);

        emailuser.setText(email);
        namauser.setText(nama);
        notelpuser.setText(notelp);
        alamatuser.setText(alamat);


        String userFotoBase64 = sharedPreferences.getString("user_foto", ""); // Gantilah ini sesuai dengan cara Anda menyimpan Base64 gambar profil

        if (!userFotoBase64.isEmpty()) {
            byte[] imageBytes = Base64.decode(userFotoBase64, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            Fotoprofil.setImageBitmap(bitmap);
        } else {
            // Handle jika Base64 gambar profil kosong atau tidak valid
        }


        retrofitclient.getConnection().create(RetrofitEndPoint.class).Profil(sharedPreferences.getString("id_akun", "")).enqueue(new Callback<userrespons>() {
            @Override
            public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    usermodel profil = response.body().getData();

                    emailuser.setText(profil.getEmail());
                    namauser.setText(profil.getNama_user());
                    notelpuser.setText(profil.getNotelp_user());
                    alamatuser.setText(profil.getAlamat_user());

                  userFotoURL = profil.getUserfoto(); // Gantilah ini sesuai dengan cara Anda mengambil URL gambar profil dari respons server

                    if (!userFotoURL.isEmpty()) {
                        Picasso.get().load(userFotoURL).into(Fotoprofil);
                    } else {
                        // Handle jika URL gambar profil kosong atau tidak valid
                    }

                    // Tambahkan kode Glide untuk memuat gambar profil
                    Glide.with(Edit_profil.this)
                            .load(retrofitclient.USER_PHOTO_URL + profil.getUserfoto())
                            .into(Fotoprofil);

                } else {
                    Toast.makeText(Edit_profil.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<userrespons> call, Throwable t) {

            }
        });


//Button simpan Profile
        btnsimpanprofile = findViewById(R.id.Btn_simpaneditprofile);

        btnsimpanprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofitclient.getConnection().create(RetrofitEndPoint.class)
                        .Btn_simpanprofil(
                                sharedPreferences.getString("id_akun", ""),
                                emailuser.getText().toString(),
                                "",
                                namauser.getText().toString(),
                                alamatuser.getText().toString(),
                                notelpuser.getText().toString(),
                                userFotoURL,
                                "",
                                ""


                        ).enqueue(new Callback<userrespons>() {


                            @Override
                            public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                    usermodel profil = response.body().getData();

                                    emailuser.setText(profil.getEmail());
                                    namauser.setText(profil.getNama_user());
                                    notelpuser.setText(profil.getNotelp_user());
                                    alamatuser.setText(profil.getAlamat_user());

                                    // Mengambil foto profil dan menampilkannya dengan Picasso
                                    userFotoURL = profil.getUserfoto();
                                    if (userFotoURL != null && !userFotoURL.isEmpty()) {
                                        Picasso.get().load(userFotoURL).into(Fotoprofil);
                                    }
                                    Toast.makeText(Edit_profil.this, "Data behasil diedit", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Edit_profil.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<userrespons> call, Throwable t) {
                                t.printStackTrace();
                            }


                        });

                if (uri != null) {
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(Edit_profil.this.getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String encoded = ImageUtil.bitmapToBase64String(bitmap, 100);
                    uploadPhoto(encoded);
                } else {

                }
            }
        });


        ImageButton backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        TextView ubahFoto = findViewById(R.id.btn_ubahfoto);
        fotoProfil = findViewById(R.id.foto_profil); // Inisialisasi ImageView dengan ID "foto_profil"

        ubahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Edit_profil.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != 0) {
                    ActivityCompat.requestPermissions(Edit_profil.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE);
                } else {
                    openGallery();
                }
            }
        });
    }

    private void uploadPhoto(String imgBase64) {
        retrofitclient.getConnection().create(RetrofitEndPoint.class)
                .Update_profil(emailuser.getText().toString(), imgBase64)
                .enqueue(new Callback<userrespons>() {
                    @Override
                    public void onResponse(Call<userrespons> call, Response<userrespons> response) {
                        if (response.body() != null && response.body().getMessage().equalsIgnoreCase("success")) {
                            Glide.with(Edit_profil.this)
                                    .load(retrofitclient.USER_PHOTO_URL+ response.body().getData().getUserfoto())
                                    .into(fotoProfil);
                        } else {
                            Toast.makeText(Edit_profil.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<userrespons> call, Throwable t) {
                        Toast.makeText(Edit_profil.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
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