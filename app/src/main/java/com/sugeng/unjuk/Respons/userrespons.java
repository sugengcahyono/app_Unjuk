package com.sugeng.unjuk.Respons;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sugeng.unjuk.Model.usermodel;

public class userrespons {

    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("data")
    private usermodel data;

    @Expose
    @SerializedName("id_umkm")
    private String idUmkm;

    public userrespons(String status, String message, usermodel data, String idUmkm) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.idUmkm = idUmkm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public usermodel getData() {
        return data;
    }

    public void setData(usermodel data) {
        this.data = data;
    }

    public String getIdUmkm() {
        return idUmkm;
    }

    public void setIdUmkm(String idUmkm) {
        this.idUmkm = idUmkm;
    }
}


