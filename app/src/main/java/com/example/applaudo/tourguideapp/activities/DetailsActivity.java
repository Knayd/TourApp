package com.example.applaudo.tourguideapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applaudo.tourguideapp.GlideApp;
import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.TourApp;
import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.util.DetailActions;
import com.example.applaudo.tourguideapp.viewmodel.DetailViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    public final static String EXTRA_PLACE = "extra_place";
    public final static String EXTRA_ACTION = "extra_action";
    public final static String EXTRA_PLACE_ID = "extra_place_id";

    private Place place;
    private String action;
    private String placeId;
    private DetailViewModel viewModel;
    private GoogleMap googleMap;
    private TextView mDetailsDescription, mDetailsLocation;
    private ImageView mDetailsImage;
    private View mTelButton, mWebsiteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar detailToolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        //Gets the views
        mDetailsDescription = findViewById(R.id.details_description);
        mDetailsImage = findViewById(R.id.details_img);
        mDetailsLocation = findViewById(R.id.details_location);
        mTelButton = findViewById(R.id.view_tel_container);
        mWebsiteButton = findViewById(R.id.view_website_container);

        mTelButton.setOnClickListener(this);
        mWebsiteButton.setOnClickListener(this);

        action = getToolBarAction();

        place = getIntent().getExtras().getParcelable(EXTRA_PLACE);
        //Sets the views
        if (place != null) {
            setPlace(place);
        } else {
            placeId = getIntent().getExtras().getString(EXTRA_PLACE_ID);
            getSinglePlaceById(placeId);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_tel_container:
                launchDialer(place.getTel());
                break;
            case R.id.view_website_container:
                openWebView(place.getWebsite());
                break;
        }
    }

    private void setPlace(Place place) {
        setTitle(place.getName());
        mDetailsDescription.setText(place.getDescription());
        GlideApp.with(getApplicationContext()).load(place.getImgSrc()).into(mDetailsImage);
        mDetailsLocation.setText(place.getLocation());
        this.place = place;
        prepareMap();
    }

    private void getSinglePlaceById(String placeId) {
        TourApp.getTourApi().getSinglePlace(placeId).enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    setPlace(response.body().get(0));
                } else {
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                onBackPressed();
            }
        });
    }


    private String getToolBarAction() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String action = bundle.getString(EXTRA_ACTION);
            if (action != null) {
                return action;
            }
        }

        return DetailActions.ACTION_ADD;

    }


    //Method to do the dialing
    private void launchDialer(String number) {
        if (!number.equals("-")) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        }
    }

    private void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void openWebView(String url) {
        if (!url.equals("-")) {
            Intent intent = PlaceWebView.getInstance(getApplicationContext(), url);
            startActivity(intent);
        }
    }

    private void prepareMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void addPlaceToVisitLater(Place place) {
        viewModel.addPlace(place);
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.added_complete_message, place.getName()), Snackbar.LENGTH_LONG).show();
    }

    private void deletePlaceFromVisitLater(Place place) {
        viewModel.deletePlace(place);
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.deleted_complete_message, place.getName()), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (action) {
            case DetailActions.ACTION_ADD:
                getMenuInflater().inflate(R.menu.detail_add_menu, menu);
                break;
            case DetailActions.ACTION_DELETE:
                getMenuInflater().inflate(R.menu.detail_delete_menu, menu);
                break;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_add_place:
                addPlaceToVisitLater(place);
                break;
            case R.id.action_delete_place:
                deletePlaceFromVisitLater(place);

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        setMapPosition();
    }

    private void setMapPosition() {
        LatLng position = new LatLng(Double.parseDouble(place.getLatitude()), Double.parseDouble(place.getLongitude()));
        googleMap.addMarker(new MarkerOptions().position(position)
                .title("Ubicación"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 15.0f));
    }
}
