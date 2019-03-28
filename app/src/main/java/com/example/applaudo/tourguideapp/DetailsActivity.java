package com.example.applaudo.tourguideapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    final static String EXTRA_PLACE = "extra_place";

    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        prepareMap();

        Toolbar detailToolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        place = getIntent().getExtras().getParcelable(EXTRA_PLACE);

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

        //This is to handle the maps display
        mDetailsDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMaps();
            }
        });
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

    private void launchMaps() {
        String location = "37.7749,-122.4194";

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + location));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
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

    private void prepareMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
