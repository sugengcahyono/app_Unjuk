package com.sugeng.unjuk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class produkmodel {

    @Expose
    @SerializedName("id_produk")
    private String idproduk;
    @Expose
    @SerializedName("nama_produk")
    private String namaproduk;
    @Expose
    @SerializedName("harga_produk")
    private String hargaproduk;
    @Expose
    @SerializedName("kategori_produk")
    private String kategoriproduk;
    @Expose
    @SerializedName("deskripsi_produk")
    private String deskripsiproduk;
    @Expose
    @SerializedName("pirt_produk")
    private String pirtproduk;
    @Expose
    @SerializedName("bpom_produk")
    private String bpomproduk;
    @Expose
    @SerializedName("idhalal_produk")
    private String idhalalproduk;
    @Expose
    @SerializedName("gambar_produk1")
    private String gambarproduk1;
    @Expose
    @SerializedName("gambar_produk2")
    private String gambarproduk2;
    @Expose
    @SerializedName("gambar_produk3")
    private String gambarproduk3;
    @Expose
    @SerializedName("id_umkm")
    private String idumkm;
    @Expose
    @SerializedName("id_akun")
    private String idakun;

    public String getIdproduk() {
        return idproduk;
    }

    public void setIdproduk(String idproduk) {
        this.idproduk = idproduk;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public String getHargaproduk() {
        return hargaproduk;
    }

    public void setHargaproduk(String hargaproduk) {
        this.hargaproduk = hargaproduk;
    }

    public String getKategoriproduk() {
        return kategoriproduk;
    }

    public void setKategoriproduk(String kategoriproduk) {
        this.kategoriproduk = kategoriproduk;
    }

    public String getDeskripsiproduk() {
        return deskripsiproduk;
    }

    public void setDeskripsiproduk(String deskripsiproduk) {
        this.deskripsiproduk = deskripsiproduk;
    }

    public String getPirtproduk() {
        return pirtproduk;
    }

    public void setPirtproduk(String pirtproduk) {
        this.pirtproduk = pirtproduk;
    }

    public String getBpomproduk() {
        return bpomproduk;
    }

    public void setBpomproduk(String bpomproduk) {
        this.bpomproduk = bpomproduk;
    }

    public String getIdhalalproduk() {
        return idhalalproduk;
    }

    public void setIdhalalproduk(String idhalalproduk) {
        this.idhalalproduk = idhalalproduk;
    }

    public String getGambarproduk1() {
        return gambarproduk1;
    }

    public void setGambarproduk1(String gambarproduk1) {
        this.gambarproduk1 = gambarproduk1;
    }

    public String getGambarproduk2() {
        return gambarproduk2;
    }

    public void setGambarproduk2(String gambarproduk2) {
        this.gambarproduk2 = gambarproduk2;
    }

    public String getGambarproduk3() {
        return gambarproduk3;
    }

    public void setGambarproduk3(String gambarproduk3) {
        this.gambarproduk3 = gambarproduk3;
    }

    public String getIdumkm() {
        return idumkm;
    }

    public void setIdumkm(String idumkm) {
        this.idumkm = idumkm;
    }

    public String getIdakun() {
        return idakun;
    }

    public void setIdakun(String idakun) {
        this.idakun = idakun;
    }

    public produkmodel(String idproduk, String namaproduk, String hargaproduk,
                       String kategoriproduk, String deskripsiproduk, String pirtproduk,
                       String bpomproduk, String idhalalproduk, String gambarproduk1,
                       String gambarproduk2, String gambarproduk3, String idumkm, String idakun) {
        this.idproduk = idproduk;
        this.namaproduk = namaproduk;
        this.hargaproduk = hargaproduk;
        this.kategoriproduk = kategoriproduk;
        this.deskripsiproduk = deskripsiproduk;
        this.pirtproduk = pirtproduk;
        this.bpomproduk = bpomproduk;
        this.idhalalproduk = idhalalproduk;
        this.gambarproduk1 = gambarproduk1;
        this.gambarproduk2 = gambarproduk2;
        this.gambarproduk3 = gambarproduk3;
        this.idumkm = idumkm;
        this.idakun = idakun;
    }
}
