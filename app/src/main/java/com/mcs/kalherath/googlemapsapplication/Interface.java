package com.mcs.kalherath.googlemapsapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Interface {
    @GET("search")
    Call<List<Item>> load(@Query("lat") String lat, @Query("lng") String lng);
}

