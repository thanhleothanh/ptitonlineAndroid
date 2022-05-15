package com.example.ptitonline.api;

import com.example.ptitonline.models.Cauhoi;
import com.example.ptitonline.models.Nguoidung;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    //nhớ dùng ip mạng máy
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.2:8080/ptitonline/webapi/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("users/login")
    Call<Nguoidung> login(@Body Nguoidung user);

    @GET("users")
    Call<List<Nguoidung>> getUsers();

    @GET("questions/luyentap/monhoc/{monhocId}")
    Call<List<Cauhoi>> getQuestions(@Path("monhocId") int monhocId);

    @GET("users/students")
    Call<List<Nguoidung>> getScoreboard();

    @PUT("users/{id}")
    Call<Nguoidung> updateUser(@Path("id") int id, @Body Nguoidung user);

}
