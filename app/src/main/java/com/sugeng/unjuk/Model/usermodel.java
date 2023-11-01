package com.sugeng.unjuk.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class usermodel {

        @Expose
        @SerializedName("id_akun")
        private String idakun;
        @Expose
        @SerializedName("email")
        private String Email;
        @Expose
        @SerializedName("password")
        private String Password;
        @Expose
        @SerializedName("nama_user")
        private String nama_user;
        @Expose
        @SerializedName("alamat_user")
        private String Alamat_user;
        @Expose
        @SerializedName("notelp_user")
        private String notelp_user;
        @Expose
        @SerializedName("user_foto")
        private String userfoto;
    @Expose
    @SerializedName("id_umkm")
    private String idumkm;
    @Expose
    @SerializedName("id_produk")
    private String idproduk;

        public usermodel(String idakun, String email, String pass,
                         String namauser, String alamatuser,
                         String notelpuser, String userfoto,String idumkm, String idproduk) {
            this.idakun = idakun;
            this.Email = email;
            this.Password = pass;
            this.nama_user = namauser;
            this.Alamat_user = alamatuser;
            this.notelp_user = notelpuser;
            this.userfoto = userfoto;
            this.idumkm = idumkm;
            this.idproduk = idproduk;
        }

    public String getIdakun() {
        return idakun;
    }

    public void setIdakun(String idakun) {
        this.idakun = idakun;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getAlamat_user() {
        return Alamat_user;
    }

    public void setAlamat_user(String alamat_user) {
        Alamat_user = alamat_user;
    }

    public String getNotelp_user() {
        return notelp_user;
    }

    public void setNotelp_user(String notelp_user) {
        this.notelp_user = notelp_user;
    }

    public String getUserfoto() {
        return userfoto;
    }

    public void setUserfoto(String userfoto) {
        this.userfoto = userfoto;
    }

    public String getIdumkm() {
        return idumkm;
    }

    public void setIdumkm(String idumkm) {
        this.idumkm = idumkm;
    }

    public String getIdproduk() {
        return idproduk;
    }

    public void setIdproduk(String idproduk) {
        this.idproduk = idproduk;
    }
}

