package com.nested.etutor.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nested.etutor.R;

public class Result extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        try {
            setContentView(R.layout.activity_result);


            //for add..
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
            AdRequest adRequest2 = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest2);
            //end add

            webView = findViewById(R.id.resultview);
            progressBar = findViewById(R.id.progress_result_id);

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                                         @Override
                                         public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                             super.onPageStarted(view, url, favicon);
                                             progressBar.setVisibility(View.VISIBLE);
                                             // setTitle("Loading...");
                                         }

                                         @Override
                                         public void onPageFinished(WebView view, String url) {
                                             super.onPageFinished(view, url);
                                             progressBar.setVisibility(View.GONE);
                                             // setTitle(view.getTitle());
                                         }
                                     }
            );

//new
            if (savedInstanceState == null) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {

                        if (isNetworkAvailable()) {
                            loadwebsite();//old

                        } else {
                            progressBar.setVisibility(View.GONE);
                            show();
                            // Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Can't load your phone", Toast.LENGTH_SHORT).show();
        }
    }

    public void show() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialogbox);
        Button button = dialog.findViewById(R.id.bid);
        dialog.setCancelable(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                //Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {

        try {

            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                super.onBackPressed();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void loadwebsite() {

        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {
            String value = bundle.getString("result");

            if (value.equals("psc")) {
                webView.loadUrl("http://dperesult.teletalk.com.bd/dpe.php");
            }
            if (value.equals("jsc")) {
                webView.loadUrl("http://www.educationboardresults.gov.bd/");
            }
            if (value.equals("eleven")) {
                webView.loadUrl("http://xiclassadmission.gov.bd/");
            }
            if (value.equals("poli")) {
                webView.loadUrl("http://www.btebadmission.gov.bd/");
            }
            if (value.equals("nu")) {
                webView.loadUrl("http://www.nubd.info/");
            }
            if (value.equals("masters")) {
                webView.loadUrl("http://www.nu.ac.bd/results/?fbclid=IwAR3C5uOqYlh6QY3DcLw63t9wv2_ng-1x5mfXRML-9ZJuLhJFYxF-PEpz7ms");
            }
            if (value.equals("degree")) {
                webView.loadUrl("http://www.nu.ac.bd/results/?fbclid=IwAR3C5uOqYlh6QY3DcLw63t9wv2_ng-1x5mfXRML-9ZJuLhJFYxF-PEpz7ms");
            }
            if (value.equals("honers")) {
                webView.loadUrl("http://www.nu.ac.bd/results/?fbclid=IwAR3C5uOqYlh6QY3DcLw63t9wv2_ng-1x5mfXRML-9ZJuLhJFYxF-PEpz7ms");
            }


        }
    }
}