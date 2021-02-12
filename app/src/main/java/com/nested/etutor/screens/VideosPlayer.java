package com.nested.etutor.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nested.etutor.R;

public class VideosPlayer extends AppCompatActivity {

   private WebView webView;
   private ProgressBar progressBar;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play_videos);


        //for add..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end add
        webView = findViewById(R.id.videosid);
        progressBar=findViewById(R.id.progress_video_id);

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
                                       //  setTitle(view.getTitle());
                                     }
                                 }
        );

        webView.setWebChromeClient(new MyChrome());

        if(savedInstanceState==null){
            webView.post(new Runnable() {
                @Override
                public void run() {

                    if(isNetworkAvailable())
                    {
                        loadWebsite();//old
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                        }
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        show();
                        // Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }

    public void show()
    {
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialogbox);
        Button button=dialog.findViewById(R.id.bid);
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }

   private void loadWebsite()
    {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String values = bundle.getString("jaman");


            if (values.equals("one")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=9");
            }
            if (values.equals("two")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=10");
            }
            if (values.equals("three")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=8");
            }
            if (values.equals("four")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=7");
            }
            if (values.equals("five")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=6");
            }
            if (values.equals("six")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=5");
            }
            if (values.equals("seven")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=4");
            }
            if (values.equals("eight")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=3");
            }
            if (values.equals("nine")) {
                webView.loadUrl("https://www.youtube.com/channel/UCnXmuphQV3fxs51znrr4eng/playlists?view=50&sort=dd&shelf_id=1");
            }
            if (values.equals("hsc")) {
                webView.loadUrl("https://www.youtube.com/user/OnnorokomPathshala/playlists");
            }
        }
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

    private class MyChrome extends WebChromeClient {

        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846);
        }
    }
}
