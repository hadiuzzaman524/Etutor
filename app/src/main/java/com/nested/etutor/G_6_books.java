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

public class G_6_books extends AppCompatActivity {

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
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list,
            R.drawable.ic_book_list,R.drawable.ic_book_list};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Class six books");
        setContentView(R.layout.activity_g_6_books);


        //for add..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end add

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name=getResources().getStringArray(R.array.g_classsixbooks_name);
        listView=findViewById(R.id.g_6_bookslistviewid);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_layout_formate,R.id.textviewidforlistview,name);
        BookViewAdapter adapter=new BookViewAdapter(this,name,flags);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value=name[position];

                if (value.equals("আনন্দ পাঠ(বাংলা দ্রুত পঠন)"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","Anando Path6");
                    startActivity(intent);
                }
                else if (value.equals("বাংলা ব্যাকরণ ও নির্মিতি"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","Bangla Grammer and Nirmity6");
                    startActivity(intent);
                }
                else if (value.equals("বিজ্ঞান"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","biggan6");
                    startActivity(intent);
                }
                else if (value.equals("কৃষি শিক্ষা"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","Krishi Shikkha6");
                    startActivity(intent);
                }
                else if (value.equals("গার্হস্থ্য বিজ্ঞান"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","Domestic Science6");
                    startActivity(intent);
                }
                else if (value.equals("শারীরিক শিক্ষা ও স্বাস্থ্য"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","Physical studies and health6");
                    startActivity(intent);
                }
                else if (value.equals("তথ্য ও যোগাযোগ প্রযুক্তি"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","ICT6");
                    startActivity(intent);
                }
                else if (value.equals("বাংলাদেশ ও বিশ্বপরিচয়"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","somaj6");
                    startActivity(intent);
                }
                else if (value.equals("কর্ম ও জীবনমুকী শিক্ষা"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","Work and Life Oriented Studies6");
                    startActivity(intent);
                }
                else if (value.equals("English Grammer and Composition"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","English Grammer and Composition6");
                    startActivity(intent);
                }
                else if (value.equals("চারুপাঠ"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","Charuphat6");
                    startActivity(intent);
                }
                else if (value.equals("ইসলাম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","islam6");
                    startActivity(intent);
                }
                else if (value.equals("হিন্দুধর্ম ও নৈতিক শিক্ষা"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","hindu6");
                    startActivity(intent);
                }
                else if (value.equals("গণিত"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","General Marh6");
                    startActivity(intent);
                }
                else if (value.equals("চারু ও কারুকলা"))
                {
                    Intent intent=new Intent(G_6_books.this,Pdf_view.class);
                    intent.putExtra("name","Charu and Karu Kola6");
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
