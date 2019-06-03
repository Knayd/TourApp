package com.example.applaudo.tourguideapp.fragments;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.applaudo.tourguideapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    public SettingsFragment() { }

    @Override
    public void onCreatePreferences(Bundle bundle, String root) {
        setPreferencesFromResource(R.xml.preferences, root);
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_settings, container, false);
//    }

}
