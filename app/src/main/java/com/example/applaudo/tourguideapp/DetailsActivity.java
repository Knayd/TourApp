package com.example.applaudo.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private final static String EXT_DETAILS_NAME = "EXT_DETAILS_NAME";
    private final static String EXT_DETAILS_DESC = "EXT_DETAILS_DESC";
    private final static String EXT_DETAILS_TEL = "EXT_DETAILS_TEL";
    private final static String EXT_DETAILS_WEB = "EXT_DETAILS_WEB";
    private final static String EXT_DETAILS_IMG = "EXT_DETAILS_IMG";


    //Factory method
    public static Intent getInstance(Context context, String name, String description, String website, String tel) {
        Intent intent = new Intent(context, DetailsActivity.class);

        intent.putExtra(EXT_DETAILS_NAME,name);
        intent.putExtra(EXT_DETAILS_DESC,description);
        intent.putExtra(EXT_DETAILS_TEL,tel);
        intent.putExtra(EXT_DETAILS_WEB,website);
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
        TextView mDetailsWebsite = findViewById(R.id.details_website);
        TextView mDetailsTel = findViewById(R.id.details_tel);

        assert data != null; // ??
        //Sets the views
        mDetailsName.setText(data.getString(EXT_DETAILS_NAME));
        mDetailsDescription.setText(data.getString(EXT_DETAILS_DESC));
        mDetailsWebsite.setText(data.getString(EXT_DETAILS_WEB));
        mDetailsTel.setText(data.getString(EXT_DETAILS_TEL));

    }
}
