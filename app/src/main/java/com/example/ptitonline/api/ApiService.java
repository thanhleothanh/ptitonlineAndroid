package com.example.ptitonline.api;

import com.example.ptitonline.models.Baithi;
import com.example.ptitonline.models.Cauhoi;
import com.example.ptitonline.models.Ketquabaithi;
import com.example.ptitonline.models.Nguoidung;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    //nhớ dùng ip mạng máy
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.4:8080/ptitonline/webapi/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //user related
    //user related
    @POST("users/login")
    Call<Nguoidung> login(@Body Nguoidung user);

    @GET("users")
    Call<List<Nguoidung>> getUsers();

    @GET("users/students")
    Call<List<Nguoidung>> getScoreboard();

    @PUT("users/{id}")
    Call<Nguoidung> updateUser(@Path("id") int id, @Body Nguoidung user);

    //question related
    //question related
    @GET("questions/luyentap/monhoc/{monhocId}")
    Call<List<Cauhoi>> getQuestions(@Path("monhocId") int monhocId);

    //test related
    //test related
    @GET("tests")
    Call<List<Baithi>> getTests(@Header("userID") String userID);

    @GET("tests/{baithiId}/questions")
    Call<List<Cauhoi>> getTestQuestions(@Path("baithiId") int baithiId);

    //test result related
    //test result related
    @GET("tests/{baithiId}/testresults/{userId}")
    Call<Ketquabaithi> getTestResult(@Path("baithiId") int baithiId, @Path("userId") int userId);

    @GET("users/{userId}/testresults")
    Call<List<Ketquabaithi>> getMyTestResults(@Path("userId") int userId);

    @POST("testresults")
    Call<Boolean> postTestResult(@Body Ketquabaithi ketquabaithi);
}
