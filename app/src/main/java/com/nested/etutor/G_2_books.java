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

public class G_2_books extends AppCompatActivity {
    String name[];
    ListView listView;
    private InterstitialAd mInterstitialAd;
    int flags[]={R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Class two books");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_g_2_books);


        //for ads..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end ads


        listView=findViewById(R.id.g_2_bookslistviewid);
        name=getResources().getStringArray(R.array.g_classonebooks_name);
       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_layout_formate,R.id.textviewidforlistview,name);
        BookViewAdapter adapter=new BookViewAdapter(this,name,flags);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value=name[position];

                if (value.equals("আমার বাংলা বই"))
                {
                    Intent intent=new Intent(G_2_books.this,Pdf_view.class);
                    intent.putExtra("name","Bangla1");
                    startActivity(intent);
                }
               else if (value.equals("English for Today"))
                {
                    Intent intent=new Intent(G_2_books.this,Pdf_view.class);
                    intent.putExtra("name","English2");
                    startActivity(intent);
                }
                else if (value.equals("গণিত"))
                {
                    Intent intent=new Intent(G_2_books.this,Pdf_view.class);
                    intent.putExtra("name","Math2");
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
