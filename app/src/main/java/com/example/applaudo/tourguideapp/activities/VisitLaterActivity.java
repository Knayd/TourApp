package com.example.applaudo.tourguideapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.adapter.PlacesAdapter;
import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.util.ActivityThemeHelper;
import com.example.applaudo.tourguideapp.util.DetailActions;
import com.example.applaudo.tourguideapp.viewmodel.VisitLaterViewModel;

import java.util.ArrayList;
import java.util.List;

public class VisitLaterActivity extends ActivityThemeHelper implements PlacesAdapter.OnItemClicked {

    private ArrayList<Place> placesToVisit;
    private PlacesAdapter placesToVisitAdapter;
    private RecyclerView visitLaterRecyclerView;
    private Toolbar visitLaterToolbar;
    private VisitLaterViewModel viewModel;
    private TextView emptyListText;
    private ImageView emptyListImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_later);

        emptyListImage = findViewById(R.id.iv_empty_places);
        emptyListText = findViewById(R.id.tv_empty_places);

        visitLaterToolbar = findViewById(R.id.visit_later_toolbar);
        setSupportActionBar(visitLaterToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        visitLaterRecyclerView = findViewById(R.id.visit_later_recyclerview);

        viewModel = ViewModelProviders.of(this).get(VisitLaterViewModel.class);

        prepareRecycler();
        init();
    }

    private void prepareRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        visitLaterRecyclerView.setLayoutManager(layoutManager);
    }

    private void init() {
        viewModel.getPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(@Nullable List<Place> places) {
                displayEmptyViews(isAnEmptyList(places));
                setPlacesToVisit(places);
            }
        });
    }

    private Boolean isAnEmptyList(List<Place> places) {
        return places == null || places.size() == 0;
    }

    private void displayEmptyViews(Boolean shouldDisplay) {
        if (shouldDisplay) {
            emptyListImage.setVisibility(View.VISIBLE);
            emptyListText.setVisibility(View.VISIBLE);
        } else {
            emptyListImage.setVisibility(View.GONE);
            emptyListText.setVisibility(View.GONE);
        }

    }

    private void setPlacesToVisit(List<Place> places) {
        if (placesToVisitAdapter == null) {
            placesToVisitAdapter = new PlacesAdapter(places, this);
            visitLaterRecyclerView.setAdapter(placesToVisitAdapter);
        } else {
            placesToVisitAdapter.updatePlaces(places);
        }
    }

    private void openPlaceDetail(Place place) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_PLACE, place);
        intent.putExtra(DetailsActivity.EXTRA_ACTION, DetailActions.ACTION_DELETE);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(Place place) {
        openPlaceDetail(place);
    }
}
