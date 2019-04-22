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
import com.example.applaudo.tourguideapp.util.DetailActions;
import com.example.applaudo.tourguideapp.viewmodel.VisitLaterViewModel;

import java.util.ArrayList;
import java.util.List;

public class VisitLaterActivity extends AppCompatActivity implements PlacesAdapter.OnItemClicked {

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

    private ArrayList<Place> mockData() {

        ArrayList<Place> placesList = new ArrayList<>();

        placesList.add(new Place(R.drawable.la_bodega_2, "La Bodega Italiana", "La Gran Vía, Carretera Panamericana y Calle Chiltiupán. Antiguo Cuscatlán, La Libertad, Centro Comercial La Gran Vía, Local 401 - 402", "bodegaitaliana.com.sv", "2536 8888", getResources().getString(R.string.description_placeholder), "", ""));
        placesList.add(new Place(R.drawable.zanzibar_2, "Zanzibar", "Zanzibar, Colonia San Benito, San Salvador", "www.barzanzibar.com", "2511 4282", getResources().getString(R.string.description_placeholder), "", ""));
        placesList.add(new Place(R.drawable.al_pomodoro_2, "Al Pomodoro", "Ave. La Revolución y Calle Circunvalación No. 184, San Salvador CP 1101", "alpomodoro.com.sv", "2243 7388", getResources().getString(R.string.description_placeholder), "", ""));
        placesList.add(new Place(R.drawable.bravissimo_2, "Bravissimo", "Col. Escalón No. 127, Pje Istmania, San Salvador", "-", "2223 3986", getResources().getString(R.string.description_placeholder), "", ""));
        placesList.add(new Place(R.drawable.la_pizzeria_2, "La Pizzeria", "Plaza Los Castaños, Avenida Masferrer Norte, San Salvador", "www.lapizzeria.com", "2566 6574", getResources().getString(R.string.description_placeholder), "", ""));
        placesList.add(new Place(R.drawable.panino_2, "Panino's", "Playa el Tunco Plaza Tunco Town, El Tunco El Salvador", "www.facebook.com/pg/paninos.sv/about/?ref=page_internal", "7698 6999", getResources().getString(R.string.description_placeholder), "", ""));
        placesList.add(new Place(R.drawable.royal_2, "Royal", "87 Avenida Sur, San Salvado", "-", "2264 0051", getResources().getString(R.string.description_placeholder), "", ""));
        placesList.add(new Place(R.drawable.style68_2, "Style 68", "16 Avenida Nte, Santa Tecla", "www.facebook.com/Style68chinesecuisine", "2246 4875", getResources().getString(R.string.description_placeholder), "", ""));

        return placesList;
    }
}
