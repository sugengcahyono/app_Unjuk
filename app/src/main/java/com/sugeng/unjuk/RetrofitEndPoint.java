package com.sugeng.unjuk;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitEndPoint {
    @FormUrlEncoded
    @POST("Login.php")
    Call<userrespons> login (
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("BuatAkun.php")
    Call<userrespons> buatakun (
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
}
