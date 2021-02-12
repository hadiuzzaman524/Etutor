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

public class G_1_books extends AppCompatActivity{

    String name[];
    ListView listView;
    int flags[]={R.drawable.ic_book_list,R.drawable.ic_book_list,R.drawable.ic_book_list};
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(" Class one books");
        setContentView(R.layout.activity_g_1_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name=getResources().getStringArray(R.array.g_classonebooks_name);


        //for ads..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end ads


        // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listview_layout_formate,R.id.textviewidforlistview,name);
        listView=findViewById(R.id.g_1_bookslistviewid);
        BookViewAdapter adapter=new BookViewAdapter(this,name,flags);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value=name[position];

                if (value.equals("আমার বাংলা বই"))
                {
                    Intent intent=new Intent(G_1_books.this, PDFViewer.class);
                    intent.putExtra("name","Bangla1");
                    startActivity(intent);
                }
               else if (value.equals("English for Today"))
                {
                    Intent intent=new Intent(G_1_books.this, PDFViewer.class);
                    intent.putExtra("name","English1");
                    startActivity(intent);
                }
               else if (value.equals("গণিত"))
                {
                    Intent intent=new Intent(G_1_books.this, PDFViewer.class);
                    intent.putExtra("name","Math1");
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
