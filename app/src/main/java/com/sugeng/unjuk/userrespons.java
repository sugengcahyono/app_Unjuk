package com.sugeng.unjuk;
import androidx.annotation.NonNull;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

        public userrespons(String status, String message, usermodel data){
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

        public usermodel getData() {
            return data;
        }

        public void setData(usermodel data) {
            this.data = data;
        }
    }


