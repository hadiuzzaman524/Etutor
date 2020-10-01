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

public class G_3_books extends AppCompatActivity {

    String name[];
    private InterstitialAd mInterstitialAd;
    ListView listView;
    int flags[]={R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Class three books");
        setContentView(R.layout.activity_g_3_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name=getResources().getStringArray(R.array.g_classthreebooks_name);
        listView=findViewById(R.id.g_3_bookslistviewid);


        //for ads..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end ads


       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_layout_formate,R.id.textviewidforlistview,name);
        BookViewAdapter adapter=new BookViewAdapter(this,name,flags);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value=name[position];

                if (value.equals("আমার বাংলা বই"))
                {
                    Intent intent=new Intent(G_3_books.this,Pdf_view.class);
                    intent.putExtra("name","bangla3");
                    startActivity(intent);
                }
               else if (value.equals("প্রাথমিক গণিত"))
                {
                    Intent intent=new Intent(G_3_books.this,Pdf_view.class);
                    intent.putExtra("name","math3");
                    startActivity(intent);
                }
                else if (value.equals("প্রাথমিক বিজ্ঞান"))
                {
                    Intent intent=new Intent(G_3_books.this,Pdf_view.class);
                    intent.putExtra("name","biggan3");
                    startActivity(intent);
                }
                else if (value.equals("English for Today"))
                {
                    Intent intent=new Intent(G_3_books.this,Pdf_view.class);
                    intent.putExtra("name","english3");
                    startActivity(intent);
                }
                else if (value.equals("ইসলাম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_3_books.this,Pdf_view.class);
                    intent.putExtra("name","islam3");
                    startActivity(intent);
                }
                else if (value.equals("হিন্দুধর্ম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_3_books.this,Pdf_view.class);
                    intent.putExtra("name","hindu3");
                    startActivity(intent);
                }
                else if (value.equals("বৌদ্ধধর্ম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_3_books.this,Pdf_view.class);
                    intent.putExtra("name","ethical3");
                    startActivity(intent);
                }
                else if (value.equals("বাংলাদেশ ও বিশ্বপরিচয়"))
                {
                    Intent intent=new Intent(G_3_books.this,Pdf_view.class);
                    intent.putExtra("name","somaj3");
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

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
