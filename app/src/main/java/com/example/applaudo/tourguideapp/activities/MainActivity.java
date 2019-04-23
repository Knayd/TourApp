package com.example.applaudo.tourguideapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.applaudo.tourguideapp.fragments.PlaceFragment;
import com.example.applaudo.tourguideapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the ViewPager that allows the user to swap
        ViewPager viewPager = findViewById(R.id.viewpager);
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);

        PlacesFragmentPagerAdapter adapter = new PlacesFragmentPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(mainToolbar);

    }

    //ViewPager adapter
    private class PlacesFragmentPagerAdapter extends FragmentStatePagerAdapter {

        //Stores the id of the tab titles resource
        private int[] mTabTitles = {(R.string.tab_str_1), (R.string.tab_str_2), (R.string.tab_str_3), (R.string.tab_str_4)};

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getString(mTabTitles[position]);
        }

        PlacesFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //This is where I store the type based on the tab number
            Bundle tabData = new Bundle();
            tabData.putInt("tabnumber", position);

            PlaceFragment frag = new PlaceFragment();
            frag.setArguments(tabData);

            return frag;

        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_visit_later:
                openVisitLater();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openVisitLater(){
        Intent intent = new Intent(getApplicationContext(), VisitLaterActivity.class);
        startActivity(intent);
    }
}
