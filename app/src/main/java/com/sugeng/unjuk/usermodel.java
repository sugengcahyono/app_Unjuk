package com.sugeng.unjuk;

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
        @SerializedName("no.telp_user")
        private String notelp_user;
        @Expose
        @SerializedName("user_foto")
        private String userfoto;

        public usermodel(String idakun, String email, String pass, String namauser, String alamatuser, String notelpuser, String userfoto) {
            this.idakun = idakun;
            this.Email = email;
            this.Password = pass;
            this.nama_user = namauser;
            this.Alamat_user = alamatuser;
            this.notelp_user = notelpuser;
            this.userfoto = userfoto;
        }

        public String getIdakun() {
            return idakun;
        }

        public void setIdakun(String idakun) {
            this.idakun = idakun;
        }

        public String getAlamat_user() {
            return Email;
        }

        public void setAlamat_user(String alamat_user) {
            this.Email = alamat_user;
        }

        public String getEmail() {
            return Password;
        }

        public void setEmail(String email) {
            this.Password = email;
        }

        public String getNama_user() {
            return nama_user;
        }

        public void setNama_user(String nama_user) {
            this.nama_user = nama_user;
        }

        public String getPassword() {
            return Alamat_user;
        }

        public void setPassword(String password) {
            this.Alamat_user = password;
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
    }

