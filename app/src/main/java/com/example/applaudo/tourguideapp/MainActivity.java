package com.example.applaudo.tourguideapp;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the ViewPager that allows the user to swap
        ViewPager viewPager = findViewById(R.id.viewpager);

        PlacesFragmentPagerAdapter adapter = new PlacesFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    //ViewPager adapter
    private class PlacesFragmentPagerAdapter extends FragmentPagerAdapter {

        //Stores the id of the tab titles resource
        private int[] mTabTitles = {(R.string.tab_str_1),(R.string.tab_str_2),(R.string.tab_str_3),(R.string.tab_str_4)};

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getString(mTabTitles[position]);
        }

        public PlacesFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Places1Fragment();
                case 1:
                    return new Places2Fragment();
                case 2:
                    return new Places3Fragment();
                default:
                    return new Places4Fragment();

            }

        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
