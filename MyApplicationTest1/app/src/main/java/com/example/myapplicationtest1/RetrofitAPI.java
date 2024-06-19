package com.example.myapplicationtest1;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("location")
    Call<String> location(@Body DeviceLocation dataModal);

    @POST("device")
    Call<String> device(@Body DeviceRegister dataModal);
}
