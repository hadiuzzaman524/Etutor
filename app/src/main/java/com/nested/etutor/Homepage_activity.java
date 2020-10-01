package com.nested.etutor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nested.etutor.fragments.Books_f;
import com.nested.etutor.fragments.Result_f;
import com.nested.etutor.fragments.Tuition_f;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Homepage_activity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    private CardView asteacher, tutorial, student, books;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    private AlertDialog.Builder builder;
    StringBuilder name = new StringBuilder();
    private Window window;
    private InterstitialAd mInterstitialAd;
    private StringBuilder pathstr;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        setContentView(R.layout.activity_homepage_activity);

        databaseReference = FirebaseDatabase.getInstance().getReference("PublicUserInformation");



        Toolbar toolbar = findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //write those code for tablayout with fragments
        TabLayout tabLayout=findViewById(R.id.tablayoutid);
        ViewPager viewPager=findViewById(R.id.viewpagerid);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Tuition_f(),"Tuition");
        viewPagerAdapter.addFragment(new Books_f(),"Books, Tutorials");
        viewPagerAdapter.addFragment(new Result_f(),"Result");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //for navigation drawer..
        drawerLayout = findViewById(R.id.navigation_drawerid);
        navigationView = findViewById(R.id.navigationviewid);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_, R.string.close_);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        storeInfo();


        loadHeader();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);

    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {


        ArrayList<Fragment> fragments;
        ArrayList<String> titles;

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
            this.titles=new ArrayList<>();
            this.fragments=new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment,String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return titles.get(position);
        }
    }

    private void loadHeader() {
        //header
        View view = navigationView.getHeaderView(0);
        final TextView tv = view.findViewById(R.id.headernameid);
        final TextView em = view.findViewById(R.id.headeremail);
        final ImageView profilep = view.findViewById(R.id.headerpicture);

        String m = getPath();

        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("uname").getValue());


                tv.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("uemail").getValue());


                em.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //image
        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("imagepath").getValue());
                if (!value.equals(""))
                    Picasso.with(getApplicationContext()).load(value).into(profilep);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String getPath() {
        pathstr = new StringBuilder();
        SharedPreferences sharedPreferences1 = getSharedPreferences("storeemail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        if (sharedPreferences1.contains("emailkey")) {
            pathstr.append(sharedPreferences1.getString("emailkey", "hadi@gmail.com"));
        }
        String xx = pathstr.toString();
        return xx;
    }

    private void storeInfo() {

        String name = "hadiuzzaman";
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", name);
        editor.commit();
    }

    @Override
    public void onClick(View v) {



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.shareappid) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject = "eTutor ";
            String body = "A simple app for those who are interested to find home tutor around them or teach privately.\nhttps://play.google.com/store/apps/details?id=com.nested.etutor";
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intent, "Share with "));

        }
        if (menuItem.getItemId() == R.id.profileid) {
            Intent intent = new Intent(Homepage_activity.this, UserProfile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        if (menuItem.getItemId() == R.id.aboutusid) {
            Intent intent = new Intent(Homepage_activity.this, MyProfile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }

        if (menuItem.getItemId() == R.id.rateid) {

//for rate us button
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())
                ));
            }
        }
        if (menuItem.getItemId() == R.id.s_logoutid) {
            SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("key", "");
            editor.commit();
            FirebaseAuth.getInstance().signOut();
            //LoginManager.getInstance().logOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), Loginpagedesign.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START, true);
        return false;
    }

    @Override
    public void onBackPressed() {

        finish();
       /* builder = new AlertDialog.Builder(Homepage_activity.this);
        builder.setTitle("    eTutor  ");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_jamanlogo);
        builder.setMessage("Are you sure you want to exit? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();*/
        // show();
    }


    public void show() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.exitdialog);
        TextView button = dialog.findViewById(R.id.negativebuttonid);
        dialog.setCancelable(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.cancel();
                //Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
            }
        });

        TextView p = dialog.findViewById(R.id.positivebuttonid);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.show();
    }

}
