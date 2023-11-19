package com.sugeng.unjuk.Retrofit;

import com.sugeng.unjuk.Respons.VerifyResponse;
import com.sugeng.unjuk.Respons.dataprodukrespons;
import com.sugeng.unjuk.Respons.dataumkmrespons;
import com.sugeng.unjuk.Respons.userrespons;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitEndPoint {
    @FormUrlEncoded
    @POST("Login.php")
    Call<userrespons> login (
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("BuatAkun.php")
    Call<userrespons> Unjuk (
            @Field("nama_user") String nama_user,
            @Field("email") String email,
            @Field("password") String password,
            @Field("notelp_user") String notelp_user,
            @Field("alamat_user") String alamat_user,
            @Field("Level") String Level
    );
    @FormUrlEncoded
    @POST("LoginGoogle.php")
    Call<userrespons> logingoogle (
                    @Field("email") String email
    );

    @FormUrlEncoded
    @POST("Katasandi_baru.php")
    Call<userrespons> katasandi_baru (
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("EditProfil.php")
    Call<userrespons> Edit_profil (
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("OTP/mail.php")
    Call<VerifyResponse> verifikasi(
            @Field("email") String email,
            @Field("type") String type,
            @Field("action") String action
    );

    @FormUrlEncoded
    @POST("Dataprofil.php")
    Call<userrespons> Profil (
            @Field("id_akun") String idakun

    );

    @FormUrlEncoded
    @POST("Dataumkm.php")
    Call<dataumkmrespons> Data_umkm (
            @Field("id_akun") String idakun
    );

    @FormUrlEncoded
    @POST("Buttonsimpandataumkm.php")
    Call<dataumkmrespons> Btn_simpanumkm (
            @Field("id_umkm") String idumkm,
            @Field("nama_umkm") String namaumkm,
            @Field("Jenis_usahaumkm") String jenisusahaumkm,
            @Field("Nib_umkm") String nibumkm,
            @Field("notelp_umkm") String notelpumkm,
            @Field("kecamatan_umkm") String kecamatanumkm,
            @Field("alamat_umkm") String alamatumkm,
            @Field("umkm_foto") String umkmfoto,
            @Field("id_akun") String idakun

    );
    @FormUrlEncoded
    @POST("Buttonuploaddataumkm.php")
    Call<dataumkmrespons> Btn_uploadumkm (
            @Field("id_umkm") String idumkm,
            @Field("nama_umkm") String namaumkm,
            @Field("Jenis_usahaumkm") String jenisusahaumkm,
            @Field("Nib_umkm") String nibumkm,
            @Field("notelp_umkm") String notelpumkm,
            @Field("kecamatan_umkm") String kecamatanumkm,
            @Field("alamat_umkm") String alamatumkm,
            @Field("umkm_foto") String umkmfoto,
            @Field("id_akun") String idakun

    );

    @FormUrlEncoded
    @POST("Buttonsimpaneditprofil.php")
    Call<userrespons> Btn_simpanprofil (
            @Field("id_akun") String idakun,
            @Field("email") String emailuser,
            @Field("password") String passworduser,
            @Field("nama_user") String namauser,
            @Field("alamat_user") String alamatuser,
            @Field("notelp_user") String notelpuser,
            @Field("user_foto") String userfoto,
            @Field("Kode_OTP") String kodeotp,
            @Field("Level") String level
    );

    @FormUrlEncoded
    @POST("Buttonsimpaneditproduk.php")
    Call<dataprodukrespons> Btn_simpaneditproduk (
            @Field("id_produk") String id_produk,
            @Field("nama_produk") String namaproduk,
            @Field("harga_produk") String hargaproduk,
            @Field("kategori_produk") String kategoriproduk,
            @Field("deskripsi_produk") String deskripsiproduk,
            @Field("pirt_produk") String pirtproduk,
            @Field("bpom_produk") String bpomproduk,
            @Field("idhalal_produk") String idhalalproduk,
            @Field("gambar_produk1") String gambarproduk1,
            @Field("gambar_produk2") String gambarproduk2,
            @Field("gambar_produk3") String gambarproduk3

    );

    @FormUrlEncoded
    @POST("Updatephotoprofil.php")
    Call<userrespons> Update_profil (

            @Field("email") String emailuser,
            @Field("photo") String userfoto

    );

    @FormUrlEncoded
    @POST("ubahkatasandi_profil.php")
    Call<userrespons> ubahkatasandi_profil (

            @Field("email") String emailuser,
            @Field("oldPassword") String oldPassword,
            @Field("newPassword") String newPassword

    );

    @FormUrlEncoded
    @POST("Updatephotoumkm.php")
    Call<dataumkmrespons> Update_photoumkm (

            @Field("nama_umkm") String namaumkm,
            @Field("photo") String umkmfoto

    );
    @FormUrlEncoded
    @POST("Produkumkm.php")
    Call<dataprodukrespons> Produk_umkm (
            @Field("id_umkm") String idumkm
    );

    @FormUrlEncoded
    @POST("upload_produk.php")
    Call<dataprodukrespons> btn_uploadproduk (
            @Field("id_produk") String id_produk,
            @Field("nama_produk") String namaproduk,
            @Field("harga_produk") String hargaproduk,
            @Field("kategori_produk") String kategoriproduk,
            @Field("deskripsi_produk") String deskripsiproduk,
            @Field("pirt_produk") String pirtproduk,
            @Field("bpom_produk") String bpomproduk,
            @Field("idhalal_produk") String idhalalproduk,
            @Field("gambar_produk1") String gambarproduk1,
            @Field("gambar_produk2") String gambarproduk2,
            @Field("gambar_produk3") String gambarproduk3,
            @Field("id_umkm") String idumkm

    );

    @FormUrlEncoded
    @POST("AmbilProduk.php")
    Call<dataprodukrespons> ambilproduk(
            @Field("id_umkm") String idumkm
    );

    @FormUrlEncoded
    @POST("Ambildataproduk.php")
    Call<dataprodukrespons> ambildataproduk(
            @Field("id_produk") String idproduk
    );

    @FormUrlEncoded
    @POST("Hapusproduksaya.php")
    Call<dataprodukrespons> Hapusproduksaya(
            @Field("id_produk") String idproduk
    );

    @FormUrlEncoded
    @POST("Cariproduksaya.php")
    Call<dataprodukrespons> cariproduksaya(
            @Field("id_umkm") String idumkm,
            @Field("search_keyword") String keywoardcari
    );

    @FormUrlEncoded
    @POST("update_pp.php")
    Call<userrespons> updatePP(
            @Field("email") String email,
            @Field("photo") String photo
    );
    @FormUrlEncoded
    @POST("mail.php")
    Call<VerifyResponse> sendEmail(
            @Field("email") String email,
            @Field("type") String type,
            @Field("action") String action
    );
}
