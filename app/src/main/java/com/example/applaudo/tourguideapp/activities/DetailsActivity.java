package com.example.applaudo.tourguideapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applaudo.tourguideapp.util.DetailActions;
import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    public final static String EXTRA_PLACE = "extra_place";
    public final static String EXTRA_ACTION = "extra_action";

    private Place place;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        prepareMap();

        Toolbar detailToolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        place = getIntent().getExtras().getParcelable(EXTRA_PLACE);
        action = getIntent().getExtras().getString(EXTRA_ACTION);

        setTitle(place.getmName());


        //Gets the views
        TextView mDetailsDescription = findViewById(R.id.details_description);
        ImageView mDetailsImage = findViewById(R.id.details_img);
        TextView mDetailsLocation = findViewById(R.id.details_location);
        View mTelButton = findViewById(R.id.view_tel_container);
        View mWebsiteButton = findViewById(R.id.view_website_container);

        mTelButton.setOnClickListener(this);
        mWebsiteButton.setOnClickListener(this);

        //Sets the views
        if (place != null) {
            mDetailsDescription.setText(place.getmDescription());
            mDetailsImage.setImageResource(place.getmImgSrc());
            mDetailsLocation.setText(place.getmLocation());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_tel_container:
                launchDialer(place.getmTel());
                break;
            case R.id.view_website_container:
                openWebView(place.getmWebsite());
                break;
        }
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
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng place = new LatLng(13.701649, -89.224681);
        googleMap.addMarker(new MarkerOptions().position(place)
                .title("Ubicación"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 15.0f));
    }
}
