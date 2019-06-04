package com.example.applaudo.tourguideapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.TourApp;
import com.example.applaudo.tourguideapp.fragments.PlaceFragment;
import com.example.applaudo.tourguideapp.model.Category;
import com.example.applaudo.tourguideapp.network.TourApi;
import com.example.applaudo.tourguideapp.util.FirebaseTopics;
import com.example.applaudo.tourguideapp.util.ActivityThemeHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends ActivityThemeHelper {

    private TourApi tourApi = TourApp.getTourApi();
    private ViewPager viewPager;
    private Toolbar mainToolbar;
    private TabLayout tabLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String TAG = "HomeActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Find the ViewPager that allows the user to swap
        viewPager = findViewById(R.id.viewpager);
        mainToolbar = findViewById(R.id.main_toolbar);
        tabLayout = findViewById(R.id.tabs);
        swipeRefreshLayout = findViewById(R.id.refresh_layout);

        setSupportActionBar(mainToolbar);

        init();
        setRefreshListener();
        registerForNotifications();

    }

    private void init() {
        tourApi.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                setViews(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Failure with viewpager", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setViews(List<Category> categories) {
        PlacesFragmentPagerAdapter adapter = new PlacesFragmentPagerAdapter(categories, getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    //ViewPager adapter
    private class PlacesFragmentPagerAdapter extends FragmentStatePagerAdapter {

        //Stores the id of the tab titles resource
        private List<Category> categories;

        PlacesFragmentPagerAdapter(List<Category> categories, FragmentManager manager) {
            super(manager);
            this.categories = categories;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return categories.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {

            //This is where I store the type based on the tab number
            Bundle arguments = new Bundle();
            arguments.putString(PlaceFragment.ARG_CATEGORY_ID, categories.get(position).getId());

            PlaceFragment frag = new PlaceFragment();
            frag.setArguments(arguments);

            return frag;
        }

        @Override
        public int getCount() {
            return categories.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_visit_later:
                openVisitLater();
                break;
            case R.id.action_settings:
                openSettings();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void openVisitLater() {
        Intent intent = new Intent(getApplicationContext(), VisitLaterActivity.class);
        startActivity(intent);
    }

    private void openSettings(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void registerForNotifications() {
        FirebaseMessaging.getInstance().subscribeToTopic(FirebaseTopics.ALL_TOPIC).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    Log.i(TAG, "Registration failed");
                } else {
                    Log.i(TAG, "Successfully registered");
                }
            }
        });
    }
}
