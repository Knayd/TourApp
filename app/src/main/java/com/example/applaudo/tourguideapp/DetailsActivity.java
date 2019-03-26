package com.example.applaudo.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        place = getIntent().getExtras().getParcelable(EXTRA_PLACE);

        //Gets the views
        TextView mDetailsName = findViewById(R.id.details_name);
        TextView mDetailsDescription = findViewById(R.id.details_description);
        final TextView mDetailsWebsite = findViewById(R.id.details_website);
        final TextView mDetailsTel = findViewById(R.id.details_tel);
        ImageView mDetailsImage = findViewById(R.id.details_img);
        TextView mDetailsLocation = findViewById(R.id.details_location);

        //Sets the views
        if (place != null) {
            mDetailsName.setText(place.getmName());
            mDetailsDescription.setText(place.getmDescription());
            mDetailsWebsite.setText(place.getmWebsite());
            mDetailsTel.setText(place.getmTel());
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
}
