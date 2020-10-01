package com.nested.etutor.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nested.etutor.G_1_books;
import com.nested.etutor.G_2_books;
import com.nested.etutor.G_3_books;
import com.nested.etutor.G_4_books;
import com.nested.etutor.G_5_books;
import com.nested.etutor.G_6_books;
import com.nested.etutor.G_7_books;
import com.nested.etutor.G_8_books;
import com.nested.etutor.G_9_books;
import com.nested.etutor.Homepage_activity;
import com.nested.etutor.Play_videos;
import com.nested.etutor.R;

public class Books_f extends Fragment implements View.OnClickListener {

    //books
    private CardView g_one, g_two, g_three, g_four, g_five, g_six, g_seven, g_eight, g_nine;

    //tutorials
    private CardView hsc, one, two, three, four, five, six, seven, eight, nine;
    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

View view=inflater.inflate(R.layout.fragment_books_f, container, false);

        //books
        g_one = view.findViewById(R.id.classoneid);
        g_two =view.findViewById(R.id.classtwoid);
        g_three = view.findViewById(R.id.classthreeid);
        g_four = view.findViewById(R.id.classfourid);
        g_five = view.findViewById(R.id.classfiveid);
        g_six = view.findViewById(R.id.classsixid);
        g_seven = view.findViewById(R.id.classsevenid);
        g_eight = view.findViewById(R.id.classeightid);
        g_nine = view.findViewById(R.id.classnineid);

        g_one.setOnClickListener(this);
        g_two.setOnClickListener(this);
        g_three.setOnClickListener(this);
        g_four.setOnClickListener(this);
        g_five.setOnClickListener(this);
        g_six.setOnClickListener(this);
        g_seven.setOnClickListener(this);
        g_eight.setOnClickListener(this);
        g_nine.setOnClickListener(this);

        //end books

        //tutorials
        one = view.findViewById(R.id.voneid);
        two = view.findViewById(R.id.vtwoid);
        three = view.findViewById(R.id.vthreeid);
        four = view.findViewById(R.id.vfourid);
        five = view.findViewById(R.id.vfiveid);
        six = view.findViewById(R.id.vsixid);
        seven = view.findViewById(R.id.vsevenid);
        eight = view.findViewById(R.id.veightid);
        nine = view.findViewById(R.id.vnineid);
        hsc = view.findViewById(R.id.vhscid);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        hsc.setOnClickListener(this);
        //end tutorials

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        return view;
    }

    @Override
    public void onClick(View v) {

        //books ....
        if (v.getId() == R.id.classoneid) {
            Intent intent = new Intent(getContext(), G_1_books.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.classtwoid) {
            Intent intent = new Intent(getContext(), G_2_books.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.classthreeid) {
            Intent intent = new Intent(getContext(), G_3_books.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.classfourid) {
            Intent intent = new Intent(getContext(), G_4_books.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.classfiveid) {
            Intent intent = new Intent(getContext(), G_5_books.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.classsixid) {
            Intent intent = new Intent(getContext(), G_6_books.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.classsevenid) {
            Intent intent = new Intent(getContext(), G_7_books.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.classeightid) {
            Intent intent = new Intent(getContext(), G_8_books.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.classnineid) {
            Intent intent = new Intent(getContext(), G_9_books.class);
            startActivity(intent);
        }

        //end books ...

        //tutorials
        if (v.getId() == R.id.voneid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "one");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

        }

        if (v.getId() == R.id.vtwoid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "two");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

        }

        if (v.getId() == R.id.vthreeid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "three");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

        if (v.getId() == R.id.vfourid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "four");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

        if (v.getId() == R.id.vfiveid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "five");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

        if (v.getId() == R.id.vsixid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "six");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

        if (v.getId() == R.id.vsevenid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "seven");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

        if (v.getId() == R.id.veightid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "eight");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

        if (v.getId() == R.id.vnineid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "nine");
            startActivity(intent);

            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

        if (v.getId() == R.id.vhscid) {
            Intent intent = new Intent(getContext(), Play_videos.class);
            intent.putExtra("jaman", "hsc");
            startActivity(intent);
            //for ads...
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }
//end tutorials
    }
}
