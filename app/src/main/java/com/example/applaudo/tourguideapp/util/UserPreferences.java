package com.example.applaudo.tourguideapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private Context context;
    private SharedPreferences preferences;

    private static String USER_PREFERENCES = "TOUR_APP_USER_PREFERENCES";
    private static String PREF_KEEP_ME_LOGGED = "PREF_KEEP_ME_LOGGED";

    public UserPreferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    public Boolean shouldKeepUserLogged(){
        return preferences.getBoolean(PREF_KEEP_ME_LOGGED, false);
    }

    public void setShouldKeepUserLogged(Boolean value){
        preferences.edit().putBoolean(PREF_KEEP_ME_LOGGED, value).apply();
    }

}
