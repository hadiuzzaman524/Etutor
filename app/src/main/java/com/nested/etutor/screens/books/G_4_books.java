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

public class G_4_books extends AppCompatActivity {
ListView listView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Class four books");
        setContentView(R.layout.activity_g_4_books);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name=getResources().getStringArray(R.array.g_classfourbooks_name);
        listView=findViewById(R.id.g_4_bookslistviewid);

        //for add..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end add

       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_layout_formate,R.id.textviewidforlistview,name);
        BookViewAdapter adapter=new BookViewAdapter(this,name,flags);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=name[position];

                if (value.equals("আমার বাংলা বই"))
                {
                    Intent intent=new Intent(G_4_books.this, PDFViewer.class);
                    intent.putExtra("name","bangla4");
                    startActivity(intent);
                }
                else if (value.equals("প্রাথমিক গণিত"))
                {
                    Intent intent=new Intent(G_4_books.this, PDFViewer.class);
                    intent.putExtra("name","math4");
                    startActivity(intent);
                }
                else if (value.equals("প্রাথমিক বিজ্ঞান"))
                {
                    Intent intent=new Intent(G_4_books.this, PDFViewer.class);
                    intent.putExtra("name","biggan4");
                    startActivity(intent);
                }
                else if (value.equals("English for Today"))
                {
                    Intent intent=new Intent(G_4_books.this, PDFViewer.class);
                    intent.putExtra("name","english4");
                    startActivity(intent);
                }
                else if (value.equals("ইসলাম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_4_books.this, PDFViewer.class);
                    intent.putExtra("name","islam4");
                    startActivity(intent);
                }
                else if (value.equals("হিন্দুধর্ম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_4_books.this, PDFViewer.class);
                    intent.putExtra("name","hindu4");
                    startActivity(intent);
                }
                else if (value.equals("খ্রিষ্টধর্ম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_4_books.this, PDFViewer.class);
                    intent.putExtra("name","ethical4");
                    startActivity(intent);
                }
                else if (value.equals("বাংলাদেশ ও বিশ্বপরিচয়"))
                {
                    Intent intent=new Intent(G_4_books.this, PDFViewer.class);
                    intent.putExtra("name","somaj4");
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
