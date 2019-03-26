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

public class DetailsActivity extends AppCompatActivity {

    final static String EXTRA_PLACE = "extra_place";
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar detailToolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        place = getIntent().getExtras().getParcelable(EXTRA_PLACE);

        setTitle(place.getmName());

        //Gets the views
        TextView mDetailsDescription = findViewById(R.id.details_description);
        final TextView mDetailsWebsite = findViewById(R.id.details_website);
        final TextView mDetailsTel = findViewById(R.id.details_tel);
        ImageView mDetailsImage = findViewById(R.id.details_img);
        TextView mDetailsLocation = findViewById(R.id.details_location);
        View mTelButton = findViewById(R.id.view_tel_container);
        View mWebsiteButton = findViewById(R.id.view_website_container);


        //Sets the views
        if (place != null) {
            mDetailsDescription.setText(place.getmDescription());
            mDetailsImage.setImageResource(place.getmImgSrc());
            mDetailsLocation.setText(place.getmLocation());
        }

        //This is so it can handle the dial
        mDetailsTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks for a valid Tel
                if (!mDetailsTel.getText().equals("-")) {
                    launchDialer(mDetailsTel.getText().toString());
                }
            }
        });

        //This is to handle the Web display
        mDetailsWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks for a valid website
                if (!mDetailsWebsite.getText().equals("-")) {
                    Intent intent = PlaceWebView.getInstance(getApplicationContext(), mDetailsWebsite.getText().toString());
                    startActivity(intent);
                }
            }
        });

        //This is to handle the maps display
        mDetailsDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMaps();
            }
        });
    }

    //Method to do the dialing
    private void launchDialer(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
