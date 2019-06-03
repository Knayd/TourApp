package com.example.applaudo.tourguideapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.TourApp;

public class UserPreferences {

    private Context context;
    private SharedPreferences preferences;

    private static String USER_PREFERENCES = TourApp.getContext().getString(R.string.user_preferences);
    private static String PREF_KEEP_ME_LOGGED = TourApp.getContext().getString(R.string.pref_keep_me_logged);
    private static String PREF_SHOW_NOTIFICATIONS = TourApp.getContext().getString(R.string.pref_get_notifications);
    private static String PREF_DARK_THEME = TourApp.getContext().getString(R.string.pref_dark_theme);

    public UserPreferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    public Boolean shouldKeepUserLogged() {
        return preferences.getBoolean(PREF_KEEP_ME_LOGGED, false);
    }

    public void setShouldKeepUserLogged(Boolean value) {
        preferences.edit().putBoolean(PREF_KEEP_ME_LOGGED, value).apply();
    }

    public Boolean shouldDisplayNotifications() {
        return preferences.getBoolean(PREF_SHOW_NOTIFICATIONS, true);
    }

    public void setShouldDisplayNotifications(Boolean value) {
        preferences.edit().putBoolean(PREF_SHOW_NOTIFICATIONS, value).apply();
    }

}
