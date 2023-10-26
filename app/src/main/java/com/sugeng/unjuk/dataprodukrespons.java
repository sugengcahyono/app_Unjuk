package com.sugeng.unjuk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataprodukrespons {


    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("data")
    private produkmodel data;

    public dataprodukrespons(String status, String message, produkmodel data){
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

    public produkmodel getData() {
        return data;
    }

    public void setData(produkmodel data) {
        this.data = data;
    }



}
