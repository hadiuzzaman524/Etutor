package com.nested.etutor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity implements View.OnClickListener, Bottom_edit_profile.ButtomEditprofileListener, BottomPhoto.PhotoChooseListener, Delete_post.DeletepostListener {

    LinearLayout editpro, editpost, post;
    private StringBuilder pathstr;
    ImageView choosephoto, profilep;
    private Dialog dialog=null;

    String imageurl;
    Uri imageuri;

    String keyfordatabase = "";
    String namefordatabase = "";
    private StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    Map<String, Object> updatedValues = new HashMap<>();

    TextView nam, qualificatio, abou, contac, birthda, jn;

    private DatabaseReference databaseReference;

    private StringBuilder email = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.setTitle("   Profile ");
        setContentView(R.layout.activity_user_profile);

        editpost = findViewById(R.id.editpostid);
        editpro = findViewById(R.id.editprofileid);
        post = findViewById(R.id.profilepostid);
        profilep = findViewById(R.id.profilepictureid);


        choosephoto = findViewById(R.id.pchooseid);
        profilep.setOnClickListener(this);

        editpro.setOnClickListener(this);
        editpost.setOnClickListener(this);
        post.setOnClickListener(this);
        choosephoto.setOnClickListener(this);

        nam = findViewById(R.id.pnameid);
        qualificatio = findViewById(R.id.pqualificationid);
        abou = findViewById(R.id.paboutid);
        contac = findViewById(R.id.pcontactid);
        birthda = findViewById(R.id.pbirthdayid);
        jn = findViewById(R.id.joinid);
        String x = "PublicUserInformation";
        databaseReference = FirebaseDatabase.getInstance().getReference("PublicUserInformation");
        storageReference = FirebaseStorage.getInstance().getReference(x);
        loadData();

        loadPicture();

        getKey();
        getDatabaseName();

    }

    //load profile picture
    private void loadPicture() {
        databaseReference.child(getPath()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageurl = String.valueOf(dataSnapshot.child("imagepath").getValue());
                if (!imageurl.equals(""))
                    Picasso.with(getApplicationContext()).load(imageurl).into(profilep);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"upload failed",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.profilepostid) {
            BottomSheet_Post bottomSheet_post = new BottomSheet_Post();
            bottomSheet_post.show(getSupportFragmentManager(), "postBottom");

        }
        if (v.getId() == R.id.editpostid) {

            Delete_post delete_post = new Delete_post();
            delete_post.show(getSupportFragmentManager(), "deletepost");

        }
        if (v.getId() == R.id.editprofileid) {
            Bottom_edit_profile edit_profile = new Bottom_edit_profile();
            edit_profile.show(getSupportFragmentManager(), "editprofile");

        }
        if (v.getId() == R.id.pchooseid) {
            loadImage();
        }
        if (v.getId() == R.id.profilepictureid) {
            loadImage();
        }


    }

    private void choose() {

        if (imageuri != null) {
            final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageuri));

           // show();
            ref.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            if (imageurl != null) {
                                try {
                                    deletePrivious();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful()) ;
                            Uri downloadurl = uriTask.getResult();
                            String var = downloadurl.toString();

                            Map<String, Object> updatedValues1 = new HashMap<>();
                            String imagepath = getPath();

                            updatedValues1.put("/" + imagepath + "/imagepath", var);
                            databaseReference.updateChildren(updatedValues1);

                            if (dialog!=null)
                            {
                                dialog.cancel();
                            }
                           // Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...

                            if (dialog!=null)
                            {
                                dialog.cancel();
                            }
                            Toast.makeText(getApplicationContext(), "Upload failed", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }


    //when change profile picture delete previous image...
    private void deletePrivious() {
        StorageReference deleteFile = FirebaseStorage.getInstance().getReferenceFromUrl(imageurl);
        deleteFile.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Toast.makeText(UserProfile.this, "Previous Image Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void photoChoose(boolean flag) {

        if (flag) {
            show();
           // Toast.makeText(getApplicationContext(), "Take short time depend on your network", Toast.LENGTH_LONG).show();
            choose();
        } else {
            Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData();
            Picasso.with(this).load(imageuri).into(profilep);
        }

        BottomPhoto bottomSheet_post = new BottomPhoto();
        bottomSheet_post.show(getSupportFragmentManager(), "photo");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ProfileOnclick(String name, String education, String about, String contact, String birthday) {

        if (!name.isEmpty()) {
            String m = getPath();
            updatedValues.put("/" + m + "/uname", name);
        }
        if (!education.isEmpty()) {
            String m = getPath();
            updatedValues.put("/" + m + "/qualification", education);
        }
        if (!about.isEmpty()) {
            String m = getPath();
            updatedValues.put("/" + m + "/about", about);
        }
        if (!contact.isEmpty()) {
            String m = getPath();
            updatedValues.put("/" + m + "/contact", contact);
        }
        if (!birthday.isEmpty()) {
            String m = getPath();
            updatedValues.put("/" + m + "/birthday", birthday);
        }

        databaseReference.updateChildren(updatedValues);
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

    private void loadData() {
        String m = getPath();
        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("uname").getValue());

                nam.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("about").getValue());

                abou.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("birthday").getValue());

                birthda.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("contact").getValue());

                contac.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("joindate").getValue());

                jn.setText(" Joined " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        databaseReference.child(m).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("qualification").getValue());

                qualificatio.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String getFileExtension(Uri imageuri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageuri));
    }


    private void getDatabaseName() {

        databaseReference.child(getPath()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namefordatabase = String.valueOf(dataSnapshot.child("databasename").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getKey() {

        databaseReference.child(getPath()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                keyfordatabase = String.valueOf(dataSnapshot.child("path").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //delete user post...
    @Override
    public void deleteListener(boolean flag) {

        if (namefordatabase.length() > 3 && keyfordatabase.length() > 3) {
            DatabaseReference db = FirebaseDatabase.getInstance().getReference(namefordatabase);
            if (flag) {
                db.child(keyfordatabase).removeValue();
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "empty post", Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(getApplicationContext(),""+namefordatabase+" "+keyfordatabase,Toast.LENGTH_LONG).show();
    }

    public void show() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loadinglayout);
        dialog.setCancelable(false);
        dialog.show();
    }
}
