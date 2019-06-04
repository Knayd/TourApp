package com.example.applaudo.tourguideapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.TourApp;
import com.example.applaudo.tourguideapp.activities.LoginActivity;
import com.example.applaudo.tourguideapp.util.FirebaseTopics;
import com.google.firebase.messaging.FirebaseMessaging;

public class SettingsFragment extends PreferenceFragmentCompat {

    public SettingsFragment() {
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String root) {
        getPreferenceManager().setSharedPreferencesName(TourApp.getContext().getString(R.string.user_preferences));
        setPreferencesFromResource(R.xml.preferences, root);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Preference logoutButton = findPreference(getString(R.string.pref_logout));
        logoutButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                logOut();
                return true;
            }
        });

        Preference darkTheme = findPreference(getString(R.string.pref_dark_theme));
        darkTheme.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                reOpenApp();
                return true;
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void logOut() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(FirebaseTopics.ALL_TOPIC);
        TourApp.getPreferences().setShouldKeepUserLogged(false);

        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void reOpenApp(){
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
