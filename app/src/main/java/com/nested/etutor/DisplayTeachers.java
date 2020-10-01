package com.nested.etutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayTeachers extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdepter myAdepter;
    List<UploadTeacher> uploadList;
    DatabaseReference databaseReference;
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Available teachers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_display_teachers);
        recyclerView = findViewById(R.id.recyclerviewid);
        textView = findViewById(R.id.setemptyteacherid);
        progressBar = findViewById(R.id.progress_display_teacher);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        uploadList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            String nm = bundle.getString("displayteacher");
            databaseReference = FirebaseDatabase.getInstance().getReference(nm);
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    UploadTeacher upload = dataSnapshot1.getValue(UploadTeacher.class);
                    uploadList.add(upload);

                }

                int l = uploadList.size();
                if (l == 0) {
                    textView.setText("No teachers available");
                    progressBar.setVisibility(View.GONE);
                } else {
                    myAdepter = new MyAdepter(DisplayTeachers.this, uploadList);
                    recyclerView.setAdapter(myAdepter);
                    //new
                    myAdepter.setOnItemClickListener(new MyAdepter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {

                            String mobilenumber=uploadList.get(position).getTmobile();
                            String m=uploadList.get(position).getUserprofile();
                            Intent intent = new Intent(DisplayTeachers.this, DisplayUserProfile.class);
                            intent.putExtra("displayuserprofile", m);
                            intent.putExtra("mobilenumber",mobilenumber);
                            startActivity(intent);
                           // Toast.makeText(getApplicationContext(),""+m,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void callHim(int position) {
                            String text=uploadList.get(position).getTmobile();
                           Intent intent=new Intent(Intent.ACTION_DIAL);
                           intent.setData(Uri.parse("tel:"+text));
                           startActivity(intent);
                        }
                    });

                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*finish();
        Intent intent=new Intent(DisplayTeachers.this,Homepage_activity.class);
        startActivity(intent);*/

        Intent intent = new Intent(getApplicationContext(), Homepage_activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {

            Intent intent = new Intent(getApplicationContext(), Homepage_activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
