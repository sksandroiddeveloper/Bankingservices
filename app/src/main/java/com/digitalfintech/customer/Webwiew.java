package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webwiew extends AppCompatActivity {
WebView webwiews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webwiew);
Intent intent = getIntent();
String s = intent.getStringExtra("web");

        webwiews = findViewById(R.id.webview);
        webwiews.setWebViewClient(new WebViewClient());
        webwiews.loadUrl(s);
        WebSettings webSettings = webwiews.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {

        if(webwiews.canGoBack())
        {
            webwiews.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
}