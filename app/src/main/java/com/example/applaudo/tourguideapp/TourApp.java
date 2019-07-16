package com.example.applaudo.tourguideapp;

import android.app.Application;
import android.content.Context;

import com.dynatrace.android.agent.Dynatrace;
import com.dynatrace.android.agent.conf.DynatraceConfigurationBuilder;
import com.example.applaudo.tourguideapp.network.TourApi;
import com.example.applaudo.tourguideapp.util.UserPreferences;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TourApp extends Application {

    private static Context context;
    private static TourApi tourApi;
    private static UserPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        preferences = new UserPreferences(this);

        if (BuildConfig.BUILD_TYPE.toLowerCase().contains("debug")) {
            Dynatrace.startup(this,
                    new DynatraceConfigurationBuilder(getResources().getString(R.string.dynatrace_app_id), getResources().getString(R.string.dynatrace_beacon_url))
                            .loadDefaultProperties(this)
                            .buildConfiguration());
        }


    }

    public static Context getContext() {
        return context;
    }

    public static TourApi getTourApi() {

        if (tourApi == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(TourApi.BASE_URL)
                    .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            tourApi = retrofit.create(TourApi.class);
        }

        return tourApi;
    }

    public static UserPreferences getPreferences() {
        return preferences;
    }
}
