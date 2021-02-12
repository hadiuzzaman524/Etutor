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

public class G_8_books extends AppCompatActivity {

    String name[];
    ListView listView;
    private InterstitialAd mInterstitialAd;
    int flags[]={
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list, R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Class eight books");
        setContentView(R.layout.activity_g_8_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name=getResources().getStringArray(R.array.g_classeightbooks_name);
        listView=findViewById(R.id.g_8_bookslistviewid);
        BookViewAdapter adapter=new BookViewAdapter(this,name,flags);
        // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_layout_formate,R.id.textviewidforlistview,name);
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
               String value=name[position];

               if (value.equals("আনন্দ পাঠ(বাংলা দ্রুত পঠন)"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","Anando Path8");
                    startActivity(intent);
                }
                else if (value.equals("বাংলা ব্যকরণ ও নির্মিতি"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","Bangla Grammer and Nirmity8");
                    startActivity(intent);
                }
                else if (value.equals("গণিত"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","Sdharon Gonit8");
                    startActivity(intent);
                }
                else if (value.equals("English Grammer and Composition"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","English Grammer and Composition8");
                    startActivity(intent);
                }
                else if (value.equals("English for Today"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","English for Today8");
                    startActivity(intent);
                }
                else if (value.equals("সাহিত্য কনিকা"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","sahitto8");
                    startActivity(intent);
                }
               else if (value.equals("বাংলাদেশ ও বিশ্বপরিচয়"))
               {
                   Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                   intent.putExtra("name","somaj8");
                   startActivity(intent);
               }
                else if (value.equals("কৃষি শিক্ষা"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","Agriculture studies8");
                    startActivity(intent);
                }
                else if (value.equals("গার্হস্থ্য বিজ্ঞান"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","Domestic Science8");
                    startActivity(intent);
                }
                else if (value.equals("শারীরিক শিক্ষা ও স্বাস্থ্য"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","Physical studies and health8");
                    startActivity(intent);
                }
                else if (value.equals("তথ্য ও যোগাযোগ প্রযুক্তি"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","ICT8");
                    startActivity(intent);
                }
                else if (value.equals("কর্ম ও জীবনমুকী শিক্ষা"))
                {
                    Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                    intent.putExtra("name","Work and Life Oriented Studies8");
                    startActivity(intent);
                }
               else if (value.equals("চারু ও কারুকলা"))
               {
                   Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                   intent.putExtra("name","Arts and crafts8");
                   startActivity(intent);
               }
               else if (value.equals("ইসলাম ও নৈতিক শিক্ষা"))
               {
                   Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                   intent.putExtra("name","islam8");
                   startActivity(intent);
               }
               else if (value.equals("হিন্দুধর্ম ও নৈতিক শিক্ষা"))
               {
                   Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                   intent.putExtra("name","hindu8");
                   startActivity(intent);
               }
               else if (value.equals("বিজ্ঞান"))
               {
                   Intent intent=new Intent(G_8_books.this, PDFViewer.class);
                   intent.putExtra("name","biggan8");
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
