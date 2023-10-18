package com.sugeng.unjuk;


import androidx.annotation.NonNull;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



import java.util.concurrent.TimeUnit;
public class retrofitclient {

    public static final String BASE_URL = "http://172.16.103.252/Web_Unjuk/"; // local

//    public static final String BASE_URL = "http://172.16.106.67/arenafinder-web/"; // wifi

    public static final String CONTROLLERS = BASE_URL + "mobile/";

    public static final String USER_PHOTO_URL = BASE_URL + "public/img/user-photo/";

    public static final String SUCCESSFUL_RESPONSE = "success";



    /**
     * connect to the rest server
     */
    public static Retrofit getConnection() {

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(CONTROLLERS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }


}
