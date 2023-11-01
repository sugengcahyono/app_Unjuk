package com.sugeng.unjuk.Respons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sugeng.unjuk.Model.produkmodel;

import java.util.ArrayList;
import java.util.List;

public class dataprodukrespons {


    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("data")
    private ArrayList<produkmodel> data;

    public dataprodukrespons(String status, String message, ArrayList<produkmodel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public ArrayList<produkmodel> getData() {
        return data;
    }

    public void setData(ArrayList<produkmodel> data) {
        this.data = data;
    }
}
