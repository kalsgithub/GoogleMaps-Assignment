package com.mcs.kalherath.googlemapsapplication;

import android.content.Context;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Item>> {

    private Context mContext;
    private String lat = "37.68821";
    private String lng = "-122.080796";

    static final String BASE_URL = "http://ridecellparking.herokuapp.com/api/v1/parkinglocations/";

    public void start(Context context) {
        mContext = context;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Interface itemInterface = retrofit.create(Interface.class);

        Call<List<Item>> call = itemInterface.load(lat,lng);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

        ((MapsActivity)mContext).setMarkers(response.body(),lat,lng);

    }

    @Override
    public void onFailure(Call<List<Item>> call, Throwable t) {
        t.printStackTrace();
    }
}