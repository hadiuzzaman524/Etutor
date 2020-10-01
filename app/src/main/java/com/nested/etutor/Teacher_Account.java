package com.nested.etutor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Teacher_Account extends AppCompatActivity implements View.OnClickListener {

    private EditText name, address, education, _class, subject, mobile, salary,description;
    private String Tname, Taddress, Teducation, T_class, Tsubject, Tmobile, Tsalary, Timagelink;
    private Button chooseb, submitb;
    private ImageView imageView;
    private ProgressBar progressBar;
    private String userProfileInfo;
    private String nm;
    private StringBuilder pathstr ;
    private DatabaseReference databaseReference1;
    private InterstitialAd mInterstitialAd;
    int x = 0;
    int c = 0;
    int y = 0;
    private Uri imageuri;
    String orpi = "";

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        setContentView(R.layout.activity_teacher__account);
        name = findViewById(R.id.teacher_nameid);
        address = findViewById(R.id.teacher_addressid);
        education = findViewById(R.id.teacher_educationid);
        _class = findViewById(R.id.teacher_classid);
        subject = findViewById(R.id.teacher_subjectid);
        mobile = findViewById(R.id.teacher_mobileid);
        salary = findViewById(R.id.teacher_salaryid);
        description=findViewById(R.id.jamanheaderid);

        progressBar = findViewById(R.id.teacher_progressbarid);

        databaseReference1 = FirebaseDatabase.getInstance().getReference("PublicUserInformation");
        //for ads..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end ads
        submitb = findViewById(R.id.teacher_submitid);

        submitb.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            nm = bundle.getString("teacherkey");
            orpi = nm;
            databaseReference = FirebaseDatabase.getInstance().getReference(nm);
            storageReference = FirebaseStorage.getInstance().getReference(nm);
        }

        userProfileInfo=getPath();
    }

    @Override
    public void onClick(View v) {

        Tname = name.getText().toString().trim();
        Taddress = address.getText().toString().trim();
        Teducation = education.getText().toString().trim();
        T_class = _class.getText().toString().trim();
        Tsubject = subject.getText().toString().trim();
        Tmobile = mobile.getText().toString().trim();
        Tsalary = salary.getText().toString().trim();
        Timagelink=description.getText().toString().trim();


        if (v.getId() == R.id.teacher_submitid) {

           /* if (Timagelink.isEmpty()) {
                description.setError("Short description of your post");
                description.requestFocus();
                return;

            }*/
            if (Tname.isEmpty()) {
                name.setError("Enter your name");
                name.requestFocus();
                return;

            }
            if (Taddress.isEmpty()) {
                address.setError("Enter address");
                address.requestFocus();
                return;
            }
            if (Teducation.isEmpty()) {
                education.setError("Enter educational qualifications");
                education.requestFocus();
                return;
            }
            if (T_class.isEmpty()) {
                _class.setError("Enter class");
                _class.requestFocus();
                return;
            }
            if (Tsubject.isEmpty()) {
                subject.setError("Enter subject");
                subject.requestFocus();
                return;
            }
            if (Tmobile.isEmpty()) {
                mobile.setError("Enter mobile number");
                mobile.requestFocus();
                return;
            }
            if (Tsalary.isEmpty()) {
                salary.setError("Enter your expected salary");
                salary.requestFocus();
                return;
            }
            else {
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                UploadTeacher upload = new UploadTeacher(Tname, Taddress, Teducation, T_class, Tsubject, Tmobile, Tsalary, Timagelink, currentDate,userProfileInfo);
                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(upload);

                progressBar.setVisibility(View.GONE);
                name.setText("");
                address.setText("");
                education.setText("");
                mobile.setText("");
                salary.setText("");
                _class.setText("");
                subject.setText("");

                //for user profile
                String orp = "/" + nm;
                String or = "/" + key;

                try {
                    storeData(or, orp);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                finish();
                Intent intent = new Intent(Teacher_Account.this, DisplayTeachers.class);
                intent.putExtra("displayteacher", orpi);
                startActivity(intent);

                //for ads...
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
           /* if (x == 0) {
                Toast.makeText(getApplicationContext(), " Choose your photo ", Toast.LENGTH_LONG).show();
            } else {

                try {
                    progressBar.setVisibility(View.VISIBLE);

                    StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageuri));

                    ref.putFile(imageuri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    if (y == 0) {
                                        ++y;
                                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                        while (!uriTask.isSuccessful()) ;
                                        Uri downloadurl = uriTask.getResult();


                                        Calendar calendar = Calendar.getInstance();
                                        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                                        UploadTeacher upload = new UploadTeacher(Tname, Taddress, Teducation, T_class, Tsubject, Tmobile, Tsalary, downloadurl.toString(), currentDate,userProfileInfo);
                                        String key = databaseReference.push().getKey();
                                        databaseReference.child(key).setValue(upload);

                                        progressBar.setVisibility(View.GONE);
                                        name.setText("");
                                        address.setText("");
                                        education.setText("");
                                        mobile.setText("");
                                        salary.setText("");
                                        _class.setText("");
                                        subject.setText("");
                                        imageView.setImageResource(R.drawable.no_img);

                                        //for user profile
                                        String orp = "/" + nm;
                                        String or = "/" + key;

                                        try {
                                            storeData(or, orp);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        finish();
                                        Intent intent = new Intent(Teacher_Account.this, DisplayTeachers.class);
                                        intent.putExtra("displayteacher", orpi);
                                        startActivity(intent);

                                        //for ads...
                                        if (mInterstitialAd.isLoaded()) {
                                            mInterstitialAd.show();
                                        } else {
                                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                                        }
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    // ...
                                    Toast.makeText(getApplicationContext(), "Registration fail", Toast.LENGTH_LONG).show();
                                }
                            });
                } catch (Exception e) {
                    --x;
                    Toast.makeText(getApplicationContext(), "Choose your photo", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }*/
        }


    }




    public void storeData(String y, String yy) {
        Map<String, Object> updatedValues = new HashMap<>();
        String m = getPath();
        updatedValues.put("/" + m + "/path", y);
        updatedValues.put("/" + m + "/databasename", yy);
        databaseReference1.updateChildren(updatedValues);
    }

    private String getPath() {

        pathstr=new StringBuilder();
        SharedPreferences sharedPreferences1 = getSharedPreferences("storeemail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        if (sharedPreferences1.contains("emailkey")) {
            pathstr.append(sharedPreferences1.getString("emailkey", "hadi@gmail.com"));
        }
        String xx = pathstr.toString();

        return xx;
    }

}
