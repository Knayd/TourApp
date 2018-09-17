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

    private final static String EXT_DETAILS_NAME = "EXT_DETAILS_NAME";
    private final static String EXT_DETAILS_DESC = "EXT_DETAILS_DESC";
    private final static String EXT_DETAILS_TEL = "EXT_DETAILS_TEL";
    private final static String EXT_DETAILS_WEB = "EXT_DETAILS_WEB";
    private final static String EXT_DETAILS_IMG = "EXT_DETAILS_IMG";

    //Factory method
    public static Intent getInstance(Context context, String name, String description, String website, String tel, int img) {
        Intent intent = new Intent(context, DetailsActivity.class);

        intent.putExtra(EXT_DETAILS_NAME, name);
        intent.putExtra(EXT_DETAILS_DESC, description);
        intent.putExtra(EXT_DETAILS_TEL, tel);
        intent.putExtra(EXT_DETAILS_WEB, website);
        intent.putExtra(EXT_DETAILS_IMG, img);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle data = getIntent().getExtras();

        //Gets the views
        TextView mDetailsName = findViewById(R.id.details_name);
        TextView mDetailsDescription = findViewById(R.id.details_description);
        final TextView mDetailsWebsite = findViewById(R.id.details_website);
        final TextView mDetailsTel = findViewById(R.id.details_tel);
        ImageView mDetailsImage = findViewById(R.id.details_img);

        //Sets the views
        if (data != null) {
            mDetailsName.setText(data.getString(EXT_DETAILS_NAME));
            mDetailsDescription.setText(data.getString(EXT_DETAILS_DESC));
            mDetailsWebsite.setText(data.getString(EXT_DETAILS_WEB));
            mDetailsTel.setText(data.getString(EXT_DETAILS_TEL));
            mDetailsImage.setImageResource(data.getInt(EXT_DETAILS_IMG));
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
    }

    //Method to do the dialing
    private void launchDialer(String number) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));

        startActivity(intent);
    }
}
