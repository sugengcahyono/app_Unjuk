package com.sugeng.unjuk.Menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.sugeng.unjuk.Menu.Profilmenu.Edit_profil;
import com.sugeng.unjuk.Menu.Profilmenu.ImageUtil;
import com.sugeng.unjuk.R;
import com.sugeng.unjuk.Respons.dataprodukrespons;
import com.sugeng.unjuk.Respons.dataumkmrespons;
import com.sugeng.unjuk.Retrofit.RetrofitEndPoint;
import com.sugeng.unjuk.Model.produkmodel;
import com.sugeng.unjuk.Retrofit.retrofitclient;
import com.sugeng.unjuk.Model.umkmmodel;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload extends Fragment {

    private Button jenisusahaButton;
    private Button btnjenisusaha;

    private Uri uriImage1, uriImage2, uriImage3;

    private int handler = 0;

    private Bitmap bitImage1, bitImage2, bitImage3;

    private String[] pilihanjenisusaha = {"Makanan", "Minuman", "Kerajinan", "Jasa"};
    private EditText namauser, notelpuser, alamatuser;
    private Button kecamatanButton;
    private Button Btnkecamatan;
    private String[] pilihanKecamatan = {"Bagor", "Baron", "Berbek", "Gondang", "Jatikalen",
            "Kertosono", "Lengkong", "Loceret", "Nganjuk", "Ngetos", "Ngluyu", "Ngronggot",
            "Pace", "Patianrowo", "Prambon", "Rejoso", "Sawahan", "Sukomoro", "Tanjunganom", "Wilangan"};

    private Button kategoriButton;
    private Button btnkategori;
    private EditText inputnamaumkm, inputnibumkm, inputnohpumkm, inputalamatumkm;

    private Button btnsimpandataumkm;

    private ImageView uploadfoto1, uploadfoto2, uploadfoto3;
    private static final int PICK_IMAGE = 1;
    private int uploadedImageCount = 0;
    private String[] pilihankategori = {"Makanan", "Minuman", "Jasa", "Kerajinan"};
    private ImageView uri; // Menyimpan ImageView yang diklik
    private Button uploadproduk;
    private ImageView Image1, Image2, Image3;
    private EditText namaproduk, hargaproduk, deskripsiproduk, pirtproduk, bpomproduk, idhalalproduk;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    public Upload() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        sharedPreferences = getContext().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Image1 = view.findViewById(R.id.uploadfoto1);
        Image2 = view.findViewById(R.id.uploadfoto2);
        Image3 = view.findViewById(R.id.uploadfoto3);
        namaproduk = view.findViewById((R.id.input_namaproduk));
        hargaproduk = view.findViewById(R.id.input_hargaproduk);
        btnkategori = view.findViewById(R.id.btnkategori);
        deskripsiproduk = view.findViewById(R.id.input_deskripsiproduk);
        pirtproduk = view.findViewById(R.id.input_pirtproduk);
        bpomproduk = view.findViewById(R.id.input_bpomproduk);
        idhalalproduk = view.findViewById(R.id.input_idhalalproduk);

        retrofitclient.getConnection().create(RetrofitEndPoint.class).Produk_umkm("3").enqueue(new Callback<dataprodukrespons>() {
            @Override
            public void onResponse(Call<dataprodukrespons> call, Response<dataprodukrespons> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("succes")) {
                    produkmodel produk = (produkmodel) response.body().getData().get(0);

                    namaproduk.setText(produk.getNamaproduk());
                    hargaproduk.setText(produk.getHargaproduk());
                    kategoriButton.setText(produk.getKategoriproduk());
                    deskripsiproduk.setText(produk.getDeskripsiproduk());
                    pirtproduk.setText(produk.getPirtproduk());
                    bpomproduk.setText(produk.getBpomproduk());
                    idhalalproduk.setText(produk.getIdhalalproduk());
//                    Image1 = produk.getGambarproduk1();
//                    Image1.setImageURI(produk.getGambarproduk1());
//                    Image2.setImageURI(produk.getGambarproduk2());
//                    Image3.setImageURI(produk.getGambarproduk3());

                } else {

                }

            }

            @Override
            public void onFailure(Call<dataprodukrespons> call, Throwable t) {

            }
        });

        uploadproduk = view.findViewById(R.id.Btnupload);
        uploadproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data1, data2, data3;

                if (bitImage1 != null && bitImage2 != null && bitImage3 != null) {
                    data1 = ImageUtil.bitmapToBase64String(bitImage1, 100);
                    data2 = ImageUtil.bitmapToBase64String(bitImage2, 100);
                    data3 = ImageUtil.bitmapToBase64String(bitImage3, 100);

                    retrofitclient.getConnection().create(RetrofitEndPoint.class)
                            .btn_uploadproduk(
                                    "", namaproduk.getText().toString(), hargaproduk.getText().toString(),
                                    kategoriButton.getText().toString(), deskripsiproduk.getText().toString(),
                                    pirtproduk.getText().toString(), bpomproduk.getText().toString(),
                                    idhalalproduk.getText().toString(),
                                    data1, data2, data3,
                                    Upload.this.sharedPreferences.getString("id_umkm", "")
                            ).enqueue(new Callback<dataprodukrespons>() {
                                @Override
                                public void onResponse(Call<dataprodukrespons> call, Response<dataprodukrespons> response) {
                                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                        Toast.makeText(requireContext(), "Data Berhasil diupload", Toast.LENGTH_SHORT).show();
                                        namaproduk.setText("");
                                        hargaproduk.setText("");
                                        jenisusahaButton.setText("");
                                        pirtproduk.setText("");
                                        kategoriButton.setText("");
                                        deskripsiproduk.setText("");
                                        bpomproduk.setText("");
                                        idhalalproduk.setText("");
                                        uploadfoto1.setImageDrawable(null);
                                        uploadfoto2.setImageDrawable(null);
                                        uploadfoto3.setImageDrawable(null);
                                    } else {
                                        Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<dataprodukrespons> call, Throwable t) {

                                }
                            });

                } else {
                    Toast.makeText(requireContext(), "Data Gambar Kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });


        inputnamaumkm = view.findViewById(R.id.input_namaumkm);
        inputnibumkm = view.findViewById(R.id.input_nibumkm);
        inputnohpumkm = view.findViewById(R.id.input_nohpumkm);
        inputalamatumkm = view.findViewById(R.id.input_alamatumkm);

        retrofitclient.getConnection().create(RetrofitEndPoint.class).Data_umkm(sharedPreferences.getString("id_akun", "")).enqueue(new Callback<dataumkmrespons>() {
            @Override
            public void onResponse(Call<dataumkmrespons> call, Response<dataumkmrespons> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                    umkmmodel user = response.body().getData();

                    inputnamaumkm.setText(user.getNamaumkm());
                    jenisusahaButton.setText(user.getJenisusahaumkm());
                    inputnibumkm.setText(user.getNib());
                    inputnohpumkm.setText(user.getNotelpumkm());
                    kecamatanButton.setText(user.getKecamatanumkm());
                    inputalamatumkm.setText(user.getAlamatumkm());


                } else {

                }

            }

            @Override
            public void onFailure(Call<dataumkmrespons> call, Throwable t) {

            }
        });
        //    Button dimpan data umkm
        btnsimpandataumkm = view.findViewById(R.id.Btn_Simpandataumkm);

        btnsimpandataumkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retrofitclient.getConnection().create(RetrofitEndPoint.class)
                        .Btn_uploadumkm(
                                "", inputnamaumkm.getText().toString(), jenisusahaButton.getText().toString(),
                                inputnibumkm.getText().toString(), inputnohpumkm.getText().toString(), kecamatanButton.getText().toString(),
                                inputalamatumkm.getText().toString(), "", sharedPreferences.getString("id_akun", "")
                        ).enqueue(new Callback<dataumkmrespons>() {
                            @Override
                            public void onResponse(Call<dataumkmrespons> call, Response<dataumkmrespons> response) {
                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                    Toast.makeText(requireContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();

                                    editor.putString("id_umkm", response.body().getIdUmkm());
                                    editor.apply();

                                } else {
                                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<dataumkmrespons> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
            }
        });


        kategoriButton = view.findViewById(R.id.btnkategori);
        btnkategori = view.findViewById(R.id.btnkategori);
        uploadfoto1 = view.findViewById(R.id.uploadfoto1);
        uploadfoto2 = view.findViewById(R.id.uploadfoto2);
        uploadfoto3 = view.findViewById(R.id.uploadfoto3);

//mengatur button jenis usaha data umkm
        btnjenisusaha = view.findViewById(R.id.btn_jenisusaha);
        jenisusahaButton = view.findViewById(R.id.btn_jenisusaha);

        btnjenisusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJenisUsahaPopupMenu();
            }
        });

//mengatur buttton kecamatan data umkm
        Btnkecamatan = view.findViewById(R.id.btnKecamatan);
        kecamatanButton = view.findViewById(R.id.btnKecamatan);

        Btnkecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKecamatanPopupMenu();
            }
        });


//mengatur button kategori upload produk
        btnkategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCategoryPopupMenu();
            }
        });

        setImageViewClickListeners();

        return view;
    }

    private void showCategoryPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(requireContext(), btnkategori);
        for (String kategori : pilihankategori) {
            popupMenu.getMenu().add(kategori);
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String pilihanKategori = item.getTitle().toString();
                kategoriButton.setText(pilihanKategori);
                return true;
            }
        });

        popupMenu.show();
    }

    private void showJenisUsahaPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(requireContext(), btnjenisusaha);
        for (String jenisusaha : pilihanjenisusaha) {
            popupMenu.getMenu().add(jenisusaha);
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String pilihanjenisusaha = item.getTitle().toString();
                jenisusahaButton.setText(pilihanjenisusaha);
                return true;
            }
        });

        popupMenu.show();
    }

    private void showKecamatanPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(requireContext(), Btnkecamatan);
        for (String kecamatan : pilihanKecamatan) {
            popupMenu.getMenu().add(kecamatan);
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String pilihanKecamatan = item.getTitle().toString();
                kecamatanButton.setText(pilihanKecamatan);
                return true;
            }
        });

        popupMenu.show();
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
        // Simpan ImageView yang diklik
        uri = imageView;
        openGallery();
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            if (uri != null) {
                Uri imageUri = data.getData();
                uri.setImageURI(imageUri);

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
                            bitImage1 = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uriImage1);
                            break;
                        }
                        case 2: {
                            bitImage2 = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uriImage2);
                            break;
                        }
                        case 3: {
                            bitImage3 = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uriImage3);
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
