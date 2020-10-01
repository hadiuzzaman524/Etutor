package com.nested.etutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class PostForTution extends AppCompatActivity implements View.OnClickListener {

    private String upazila_name[];
    private String district_name[];
    AutoCompleteTextView district, upazila;
    Button nex;
    private Window window;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        this.setTitle("Post for tuition");

        setContentView(R.layout.activity_post_for_tution);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for add..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end add

        nex = findViewById(R.id.Fnextbuttonid);
        nex.setOnClickListener(this);

        district = findViewById(R.id.Fdistrctid);
        upazila = findViewById(R.id.Fupazilaid);

        district_name = getResources().getStringArray(R.array.zilla_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, district_name);
        district.setThreshold(1);

        district.setAdapter(adapter);

        district.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String s = district.getText().toString();

                setUpazila(s);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String districtN = district.getText().toString().trim();
        String upazilaN = upazila.getText().toString().trim();

        String pass="student"+districtN+upazilaN;


        if (districtN.contains("."))
        {
            district.setError("Ignore (.)");
            district.requestFocus();
            return;
        }
        if (upazilaN.contains("."))
        {
            upazila.setError("Ignore (.)");
            upazila.requestFocus();
            return;
        }

        if (districtN.isEmpty()) {
            district.setError("Enter District");
            district.requestFocus();
            return;
        }
        if (upazilaN.isEmpty()) {
            upazila.setError("Enter Upazilla");
            upazila.requestFocus();
            return;
        }
        else {

            if (isNetworkAvailable()) {
                if (v.getId() == R.id.Fnextbuttonid) {
                    Intent intent = new Intent(PostForTution.this, Student_Account.class);
                    intent.putExtra("studentkey", pass);
                    startActivity(intent);
                    //for add...
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
                }
            }
            else {
                show();
            }
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

    void setUpazila(String s) {

        switch (s) {
            case "Barguna": {
                upazila_name = getResources().getStringArray(R.array.Barguna_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Barishal": {
                upazila_name = getResources().getStringArray(R.array.Barishal_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Jhalokati": {
                upazila_name = getResources().getStringArray(R.array.Jhalokati_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Bhola": {
                upazila_name = getResources().getStringArray(R.array.Bhola_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Patuakhali": {
                upazila_name = getResources().getStringArray(R.array.Patuakhali_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Pirojpur": {
                upazila_name = getResources().getStringArray(R.array.Pirojpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Bandarban": {
                upazila_name = getResources().getStringArray(R.array.Bandarban_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Brahmanbaria": {
                upazila_name = getResources().getStringArray(R.array.Brahmanbaria_distirct);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Chandpur": {
                upazila_name = getResources().getStringArray(R.array.Chandpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Chattogram": {
                upazila_name = getResources().getStringArray(R.array.Chattogram_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Cumilla": {
                upazila_name = getResources().getStringArray(R.array.Cumilla_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }
            case "Cox Bazar": {
                upazila_name = getResources().getStringArray(R.array.Cox_Bazar_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }
            case "Feni": {
                upazila_name = getResources().getStringArray(R.array.Feni_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Khagrachhari": {
                upazila_name = getResources().getStringArray(R.array.Khagrachhari_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Lakshmipur": {
                upazila_name = getResources().getStringArray(R.array.Lakshmipur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Noakhali": {
                upazila_name = getResources().getStringArray(R.array.Noakhali_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Rangamati": {
                upazila_name = getResources().getStringArray(R.array.Rangamati_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Dhaka": {
                upazila_name = getResources().getStringArray(R.array.Dhaka_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Faridpur": {
                upazila_name = getResources().getStringArray(R.array.Faridpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Gazipur": {
                upazila_name = getResources().getStringArray(R.array.Gazipur_distrct);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Gopalganj": {
                upazila_name = getResources().getStringArray(R.array.Gopalganj_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Kishoreganj": {
                upazila_name = getResources().getStringArray(R.array.Kishoreganj_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Madaripur": {
                upazila_name = getResources().getStringArray(R.array.Madaripur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Manikganj": {
                upazila_name = getResources().getStringArray(R.array.Manikganj_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Munshiganj": {
                upazila_name = getResources().getStringArray(R.array.Munshiganj_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Narayanganj": {
                upazila_name = getResources().getStringArray(R.array.Narayanganj_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Narsingdi": {
                upazila_name = getResources().getStringArray(R.array.Narsingdi_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Rajbari": {
                upazila_name = getResources().getStringArray(R.array.Rajbari_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Shariatpur": {
                upazila_name = getResources().getStringArray(R.array.Sherpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Tangail": {
                upazila_name = getResources().getStringArray(R.array.Tangail_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Bagerhat": {
                upazila_name = getResources().getStringArray(R.array.Bagerhat_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Chuadanga": {
                upazila_name = getResources().getStringArray(R.array.Chandpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Jashore": {
                upazila_name = getResources().getStringArray(R.array.Jashore_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Jhenaidah": {
                upazila_name = getResources().getStringArray(R.array.Jhenaida_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Khulna": {
                upazila_name = getResources().getStringArray(R.array.Khulna_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Kushtia": {
                upazila_name = getResources().getStringArray(R.array.Kushtia_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Magura": {
                upazila_name = getResources().getStringArray(R.array.Magura_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Meherpur": {
                upazila_name = getResources().getStringArray(R.array.Meherpurp_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Narail": {
                upazila_name = getResources().getStringArray(R.array.Narail_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Satkhira": {
                upazila_name = getResources().getStringArray(R.array.Satkhira_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Jamalpur": {
                upazila_name = getResources().getStringArray(R.array.Jamalpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Mymensingh": {
                upazila_name = getResources().getStringArray(R.array.Mymensingh_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Netrokona": {
                upazila_name = getResources().getStringArray(R.array.Netrokona_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Sherpur": {
                upazila_name = getResources().getStringArray(R.array.Sherpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Bogura": {
                upazila_name = getResources().getStringArray(R.array.Bogura_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Joypurhat": {

                upazila_name = getResources().getStringArray(R.array.Joypurhat_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Naogaon": {
                upazila_name = getResources().getStringArray(R.array.Naogaon_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Natore": {
                upazila_name = getResources().getStringArray(R.array.Natore_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Chapainawabganj": {
                upazila_name = getResources().getStringArray(R.array.Nawabganj_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Pabna": {
                upazila_name = getResources().getStringArray(R.array.Pabna_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Rajshahi": {
                upazila_name = getResources().getStringArray(R.array.Rajshahi_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Sirajganj": {
                upazila_name = getResources().getStringArray(R.array.Sirajganj_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Dinajpur": {
                upazila_name = getResources().getStringArray(R.array.Dinajpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Gaibandha": {
                upazila_name = getResources().getStringArray(R.array.Gaibandha_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Kurigram": {
                upazila_name = getResources().getStringArray(R.array.Kurigram_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }
            case "Lalmonirhat": {
                upazila_name = getResources().getStringArray(R.array.Lalmonirhat_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }
            case "Nilphamari": {
                upazila_name = getResources().getStringArray(R.array.Nilphamari_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Panchagarh": {
                upazila_name = getResources().getStringArray(R.array.Panchagarh_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }
            case "Rangpur": {
                upazila_name = getResources().getStringArray(R.array.Rangpur_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }
            case "Thakurgaon": {
                upazila_name = getResources().getStringArray(R.array.Thakurgaon_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Habiganj": {
                upazila_name = getResources().getStringArray(R.array.Habiganj_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

            case "Moulvibazar": {
                upazila_name = getResources().getStringArray(R.array.Moulvibazar_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }
            case "Sunamganj": {
                upazila_name = getResources().getStringArray(R.array.Sunamganj_distirct);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }
            case "Sylhet": {
                upazila_name = getResources().getStringArray(R.array.Sylhet_district);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, upazila_name);
                upazila.setThreshold(1);
                upazila.setAdapter(adapter);

                break;
            }

        }
    }
}
