package com.sugeng.unjuk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataumkmrespons {



    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("data")
    private umkmmodel data;

    public dataumkmrespons(String status, String message, umkmmodel data){
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

    public umkmmodel getData() {
        return data;
    }

    public void setData(umkmmodel data) {
        this.data = data;
    }


}
