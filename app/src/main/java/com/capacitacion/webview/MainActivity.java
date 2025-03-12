package com.capacitacion.webview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        WebView wv = (WebView) findViewById(R.id.web_view_user);
        wv.setWebViewClient(new WebViewClient());

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        /* String information = "<html><body><h1>Welcome Android</h1><p>Párrafo :b</p></body></html>";
        wv.loadData(information, "text/html", "UTF-8"); */
        wv.loadUrl(getResources().getString(R.string.urlGitCapacitacion));

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true)
        {
            @Override
            public void handleOnBackPressed()
            {
                if(wv.canGoBack())
                {
                    wv.goBack();
                }
                else
                {
                    finish();
                }
            }
        });
    }
}