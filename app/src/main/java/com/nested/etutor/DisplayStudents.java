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

public class DisplayStudents extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    List<Upload> uploadList;
    DatabaseReference databaseReference;
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_display_students);
        this.setTitle("Available students");


        recyclerView = findViewById(R.id.recyclerviewidstudent);
        textView = findViewById(R.id.setemptystudentid);
        progressBar = findViewById(R.id.progress_display_student);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        uploadList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            String nm = bundle.getString("displaystudent");
            databaseReference = FirebaseDatabase.getInstance().getReference(nm);

        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Upload upload = dataSnapshot1.getValue(Upload.class);
                    uploadList.add(upload);

                }

                int l = uploadList.size();
                if (l == 0) {
                    textView.setText("No students available");
                    progressBar.setVisibility(View.GONE);
                } else {

                    customAdapter = new CustomAdapter(DisplayStudents.this, uploadList);
                    recyclerView.setAdapter(customAdapter);

                    customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {

                            String mobilenumber=uploadList.get(position).getMobile();
                            String m=uploadList.get(position).getProfileinfo();
                            Intent intent = new Intent(DisplayStudents.this, DisplayUserProfile.class);
                            intent.putExtra("displayuserprofile", m);
                            intent.putExtra("mobilenumber",mobilenumber);
                            startActivity(intent);
                           // Toast.makeText(getApplicationContext(),""+m,Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void callHim(int position) {

                            String text=uploadList.get(position).getMobile();
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

                Toast.makeText(getApplicationContext(), "Empty list", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* finish();
        Intent intent=new Intent(DisplayStudents.this,Homepage_activity.class);
        startActivity(intent);
        */
        Intent intent = new Intent(getApplicationContext(), Homepage_activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(getApplicationContext(), Homepage_activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
