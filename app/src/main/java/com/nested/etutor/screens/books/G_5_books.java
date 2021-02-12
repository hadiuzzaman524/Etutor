package com.nested.etutor.screens.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nested.etutor.Adapter.BookViewAdapter;
import com.nested.etutor.R;
import com.nested.etutor.screens.PDFViewer;

public class G_5_books extends AppCompatActivity {

    String name[];
    private InterstitialAd mInterstitialAd;
    int flags[]={R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list};

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Class five books");
        setContentView(R.layout.activity_g_5_books);

        //for add..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end add

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name=getResources().getStringArray(R.array.g_classfivebooks_name);
        listView=findViewById(R.id.g_5_bookslistviewid);
        BookViewAdapter adapter=new BookViewAdapter(this,name,flags);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_layout_formate,R.id.textviewidforlistview,name);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=name[position];

                if (value.equals("আমার বাংলা বই"))
                {
                    Intent intent=new Intent(G_5_books.this, PDFViewer.class);
                    intent.putExtra("name","bangla5");
                    startActivity(intent);
                }
                else if (value.equals("প্রাথমিক গণিত"))
                {
                    Intent intent=new Intent(G_5_books.this, PDFViewer.class);
                    intent.putExtra("name","math5");
                    startActivity(intent);
                }
                else if (value.equals("English for Today"))
                {
                    Intent intent=new Intent(G_5_books.this, PDFViewer.class);
                    intent.putExtra("name","english5");
                    startActivity(intent);
                }
                else if (value.equals("ইসলাম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_5_books.this, PDFViewer.class);
                    intent.putExtra("name","islam5");
                    startActivity(intent);
                }
                else if (value.equals("প্রাথমিক বিজ্ঞান"))
                {
                    Intent intent=new Intent(G_5_books.this, PDFViewer.class);
                    intent.putExtra("name","science5");
                    startActivity(intent);
                }
                else if (value.equals("বাংলাদেশ ও বিশ্বপরিচয়"))
                {
                    Intent intent=new Intent(G_5_books.this, PDFViewer.class);
                    intent.putExtra("name","somaj5");
                    startActivity(intent);
                }
                else if (value.equals("হিন্দুধর্ম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_5_books.this, PDFViewer.class);
                    intent.putExtra("name","hindu5");
                    startActivity(intent);
                }
                else if (value.equals("খ্রিষ্টধর্ম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_5_books.this, PDFViewer.class);
                    intent.putExtra("name","christan5");
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
