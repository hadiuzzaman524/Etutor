package com.nested.etutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class G_9_books extends AppCompatActivity {

    String name[];
    ListView listView;
    private InterstitialAd mInterstitialAd;
    int flags[] = {
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Nine-Ten books");
        setContentView(R.layout.activity_g_9_books);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name = getResources().getStringArray(R.array.g_classninebooks_name);
        listView = findViewById(R.id.g_9_bookslistviewid);
        // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_layout_formate,R.id.textviewidforlistview,name);
        BookViewAdapter adapter = new BookViewAdapter(this, name, flags);
        listView.setAdapter(adapter);

        //for add..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end add

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = name[position];

                if (value.equals("বাংলা সহপাঠ")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Bangla Sohopath9");
                    startActivity(intent);
                } else if (value.equals("বাংলা সাহিত্য")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "sahitto9");
                    startActivity(intent);
                } else if (value.equals("বাংলা ভাষার ব্যাকরণ")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Bangla Grammer and Nirmity9");
                    startActivity(intent);
                } else if (value.equals("English for Toady")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "english9");
                    startActivity(intent);
                } else if (value.equals("Enlish Grammer and Composition")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "grammer9");
                    startActivity(intent);
                } else if (value.equals("গণিত")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "General Math9");
                    startActivity(intent);
                } else if (value.equals("উচ্চতর গণিত")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Higher Math9");
                    startActivity(intent);
                } else if (value.equals("চারু ও কারুকলা")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Arts and crafts9");
                    startActivity(intent);
                } else if (value.equals("বিজ্ঞান")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "biggan9");
                    startActivity(intent);
                } else if (value.equals("রচনাসম্ভার")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "rochona9");
                    startActivity(intent);
                } else if (value.equals("তথ্য ও যোগাযোগ প্রযুক্তি")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "ICT9");
                    startActivity(intent);
                } else if (value.equals("ক্যারিয়ার এডুকেশন")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Career Education9");
                    startActivity(intent);
                } else if (value.equals("রসায়ন")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Chemistry9");
                    startActivity(intent);
                } else if (value.equals("জীববিজ্ঞান")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Biology9");
                    startActivity(intent);
                } else if (value.equals("বাংলাদেশের ইতিহাস ও বিশ্বসভ্যতা")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "History and civilization9");
                    startActivity(intent);
                } else if (value.equals("ভূগোল ও পরিবেশ")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Geography and Environment9");
                    startActivity(intent);
                } else if (value.equals("অর্থনীতি")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Economics9");
                    startActivity(intent);
                } else if (value.equals("কৃষিশিক্ষা")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Agriculture9");
                    startActivity(intent);
                } else if (value.equals("গার্হস্থ্য বিজ্ঞান")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Domestic Science9");
                    startActivity(intent);
                } else if (value.equals("পৌরনীতি ও নাগরিকতা")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Civics and Citizenship9");
                    startActivity(intent);
                } else if (value.equals("পদার্থবিজ্ঞান")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "Physics9");
                    startActivity(intent);
                } else if (value.equals("হিসাববিজ্ঞান")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "hisab9");
                    startActivity(intent);
                } else if (value.equals("ফিন্যান্স ও ব্যাংকিং")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "financ9");
                    startActivity(intent);
                } else if (value.equals("ব্যবসায় উদ্যোগ")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "babosa9");
                    startActivity(intent);
                } else if (value.equals("ইসলাম ও নৈতিক শিক্ষা")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "islam9");
                    startActivity(intent);
                } else if (value.equals("হিন্দু ধর্ম ও নৈতিক শিক্ষা")) {
                    Intent intent = new Intent(G_9_books.this, Pdf_view.class);
                    intent.putExtra("name", "hindu9");
                    startActivity(intent);
                }
                //for ads...
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
