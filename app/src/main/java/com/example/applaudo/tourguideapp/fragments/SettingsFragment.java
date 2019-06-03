package com.example.applaudo.tourguideapp.fragments;


import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.TourApp;

public class SettingsFragment extends PreferenceFragmentCompat {

    public SettingsFragment() {
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String root) {
        getPreferenceManager().setSharedPreferencesName(TourApp.getContext().getString(R.string.user_preferences));
        setPreferencesFromResource(R.xml.preferences, root);

        Preference logoutButton = findPreference(getString(R.string.pref_logout));
        logoutButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(TourApp.getContext(), "Log out", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_settings, container, false);
//    }

}
