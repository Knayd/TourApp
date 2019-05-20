package com.example.applaudo.tourguideapp;

import android.app.Application;
import android.content.Context;

import com.example.applaudo.tourguideapp.network.TourApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TourApp extends Application {

    private static Context context;
    private static TourApi tourApi;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static TourApi getTourApi() {

        if (tourApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TourApi.BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            tourApi = retrofit.create(TourApi.class);
        }

        return tourApi;
    }
}
