package com.example.applaudo.tourguideapp.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.TourApp;

public class ActivityThemeHelper extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(TourApp.getPreferences().shouldDisplayDarkTheme()){
            setTheme(R.style.AppThemeDark);
        }
        super.onCreate(savedInstanceState);
    }
}
