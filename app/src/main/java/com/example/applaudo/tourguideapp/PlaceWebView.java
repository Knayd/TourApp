package com.example.applaudo.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlaceWebView extends AppCompatActivity {

    private static final String EXT_URL = "EXT_URL";

    //Factory method
    public static Intent getInstance(Context context, String url) {
        Intent intent = new Intent(context, PlaceWebView.class);
        intent.putExtra(EXT_URL,url);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_web_view);

        //Gets the URL from the Details activity
        WebView browser = findViewById(R.id.web_view);
        Bundle urlData = getIntent().getExtras();

        if (urlData != null) {
            String url = urlData.getString(EXT_URL);

            browser.getSettings().setLoadsImagesAutomatically(true);
            browser.getSettings().setJavaScriptEnabled(true);
            browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            browser.setWebViewClient(new WebHelper());
            browser.loadUrl("http://" + url);
        }

    }

    private class WebHelper extends WebViewClient{
        @Override
        //This is so it doesn't open a browser like an intent
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    }
}
