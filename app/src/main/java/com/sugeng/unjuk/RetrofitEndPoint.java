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
}
