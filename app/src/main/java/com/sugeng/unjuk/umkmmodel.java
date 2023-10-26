package com.sugeng.unjuk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class umkmmodel {


    @Expose
    @SerializedName("id_umkm")
    private String idumkm;
    @Expose
    @SerializedName("nama_umkm")
    private String namaumkm;
    @Expose
    @SerializedName("Jenis_usahaumkm")
    private String jenisusahaumkm;
    @Expose
    @SerializedName("Nib_umkm")
    private String nib;
    @Expose
    @SerializedName("notelp_umkm")
    private String notelpumkm;
    @SerializedName("kecamatan_umkm")
    private String kecamatanumkm;
    @Expose
    @SerializedName("alamat_umkm")
    private String alamatumkm;

    public String getIdumkm() {
        return idumkm;
    }


    public void setIdumkm(String idumkm) {
        this.idumkm = idumkm;
    }

    public String getNamaumkm() {
        return namaumkm;
    }

    public void setNamaumkm(String namaumkm) {
        this.namaumkm = namaumkm;
    }

    public String getJenisusahaumkm() {
        return jenisusahaumkm;
    }

    public void setJenisusahaumkm(String jenisusahaumkm) {
        this.jenisusahaumkm = jenisusahaumkm;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public String getNotelpumkm() {
        return notelpumkm;
    }

    public void setNotelpumkm(String notelpumkm) {
        this.notelpumkm = notelpumkm;
    }

    public String getKecamatanumkm() {
        return kecamatanumkm;
    }

    public String getAlamatumkm() {
        return alamatumkm;
    }

    public void setAlamatumkm(String alamatumkm) {
        this.alamatumkm = alamatumkm;
    }

    public String getUmkmfoto() {
        return umkmfoto;
    }

    public void setUmkmfoto(String umkmfoto) {
        this.umkmfoto = umkmfoto;
    }

    public String getIdakun() {
        return idakun;
    }

    public void setKecamatanumkm(String kecamatanumkm) {
        this.kecamatanumkm = kecamatanumkm;
    }

    public void setIdakun(String idakun) {
        this.idakun = idakun;
    }

    @Expose
    @SerializedName("umkm_foto")
    private String umkmfoto;
    @Expose
    @SerializedName("id_akun")
    private String idakun;

    public umkmmodel(String idumkm, String namaumkm, String jenisuasahumkm,
                     String nib, String notelpumkm, String kecamatanumkm,
                     String alamatumkm, String umkmfoto, String idakun) {
        this.idumkm = idumkm;
        this.namaumkm = namaumkm;
        this.jenisusahaumkm = jenisuasahumkm;
        this.nib = nib;
        this.notelpumkm = notelpumkm;
        this.kecamatanumkm = kecamatanumkm;
        this.alamatumkm = alamatumkm;
        this.umkmfoto = umkmfoto;
        this.idakun = idakun;
    }

}
