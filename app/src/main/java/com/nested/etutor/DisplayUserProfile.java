package com.nested.etutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DisplayUserProfile extends AppCompatActivity {

    private TextView name, joindate, about, qualification, contact, birthday;
    private ImageView imageView;
    private Button button;

    String mobilenumber;
    private String emailkey;
    private String imageurl = "";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("  user info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_display_user_profile);

        name = findViewById(R.id.displaynameid);
        joindate = findViewById(R.id.displaydateid);
        about = findViewById(R.id.displayselfid);
        qualification = findViewById(R.id.displayqualification);
        contact = findViewById(R.id.displaycontactid);
        birthday = findViewById(R.id.displaybirthdayid);
        imageView = findViewById(R.id.displaypictureid);
        button=findViewById(R.id.usercallnowid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callHim();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            emailkey = bundle.getString("displayuserprofile");
            mobilenumber=bundle.getString("mobilenumber");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("PublicUserInformation");
        loadPicture();
        saveData();
    }

    public void callHim() {

        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+mobilenumber));
        startActivity(intent);
    }
    private void saveData() {

        databaseReference.child(emailkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("uname").getValue());

                name.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child(emailkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("about").getValue());

                about.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child(emailkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("birthday").getValue());

                birthday.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child(emailkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("contact").getValue());

                contact.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child(emailkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("joindate").getValue());

                joindate.setText(" Joined " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        databaseReference.child(emailkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("qualification").getValue());

                qualification.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void loadPicture() {
        databaseReference.child(emailkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageurl = String.valueOf(dataSnapshot.child("imagepath").getValue());
                if (!imageurl.equals(""))
                    Picasso.with(getApplicationContext()).load(imageurl).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
}
