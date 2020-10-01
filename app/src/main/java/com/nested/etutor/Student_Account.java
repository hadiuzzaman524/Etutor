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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Student_Account extends AppCompatActivity implements View.OnClickListener {

    private EditText name, address, mobile, salary, institute, _class, subject,shortdesc;
    private Button choosebutton, submitbutton;
    private String Sname, Saddress, Smobile, Ssalary, Sinstute, S_class, Ssubject,Sdes;
    private ProgressBar progressBar;

    private String userProfileInfo;
    String jaman = "";
    String nm;
    StorageTask storageTask;

    private StringBuilder pathstr;

    private InterstitialAd mInterstitialAd;
    int x = 0;
    int c = 0;
    int y = 0;

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1;
    private StorageReference storageReference;
    Uri imageuri;
    private Window window;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        setContentView(R.layout.activity_student__account);

        name = findViewById(R.id.student_nameid);
        address = findViewById(R.id.student_local_addressid);
        mobile = findViewById(R.id.student_mobileid);
        salary = findViewById(R.id.student_salaryid);
        institute = findViewById(R.id.student_instute_id);
        _class = findViewById(R.id.student_classid);
        subject = findViewById(R.id.student_subjectid);
        progressBar = findViewById(R.id.progressbarid);
        shortdesc=findViewById(R.id.shortdiscriptionid);


        databaseReference1 = FirebaseDatabase.getInstance().getReference("PublicUserInformation");

        submitbutton = findViewById(R.id.student_submitinfoid);
        submitbutton.setOnClickListener(this);
        //for ads..
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.mInterstitialAd));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest2);
        //end ads

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            nm = bundle.getString("studentkey");
            jaman = nm;
            databaseReference = FirebaseDatabase.getInstance().getReference(nm);
            storageReference = FirebaseStorage.getInstance().getReference(nm);
        }

        userProfileInfo=getPath();
    }


    @Override
    public void onClick(View v) {

        Sname = name.getText().toString().trim();
        Saddress = address.getText().toString().trim();
        Smobile = mobile.getText().toString().trim();
        Ssalary = salary.getText().toString().trim();
        Sinstute = institute.getText().toString().trim();
        S_class = _class.getText().toString().trim();
        Ssubject = subject.getText().toString().trim();
        Sdes=shortdesc.getText().toString().trim();


        if (v.getId() == R.id.student_submitinfoid) {
           /* if (Sdes.isEmpty()) {
                shortdesc.setError("Short description of your post");
                shortdesc.requestFocus();
                return;
            }*/
            if (Sname.isEmpty()) {
                name.setError("Enter your name");
                name.requestFocus();
                return;
            }
            if (Saddress.isEmpty()) {
                address.setError("Enter local address");
                address.requestFocus();
                return;
            }
            if (Smobile.isEmpty()) {
                mobile.setError("Enter mobile number");
                mobile.requestFocus();
                return;
            }
            if (Ssalary.isEmpty()) {
                salary.setError("Enter salary");
                salary.requestFocus();
                return;
            }
            if (Sinstute.isEmpty()) {
                institute.setError("Enter your institute name");
                institute.requestFocus();
                return;
            }
            if (S_class.isEmpty()) {
                _class.setError("Enter your class");
                _class.requestFocus();
                return;
            }
            if (Ssubject.isEmpty()) {
                subject.setError("Enter subject");
                subject.requestFocus();
                return;
            }
          else {

               /* try {
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

                                        Upload upload = new Upload(Sname, Saddress, Smobile, Ssalary, Sinstute, S_class, Ssubject, downloadurl.toString(), currentDate,userProfileInfo);
                                        String key = databaseReference.push().getKey();
                                        databaseReference.child(key).setValue(upload);

                                        progressBar.setVisibility(View.GONE);
                                        name.setText("");
                                        address.setText("");
                                        mobile.setText("");
                                        salary.setText("");
                                        institute.setText("");
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

                                        //end...

                                        finish();
                                        Intent intent = new Intent(Student_Account.this, DisplayStudents.class);
                                        intent.putExtra("displaystudent", jaman);
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
                }*/
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                Upload upload = new Upload(Sname, Saddress, Smobile, Ssalary, Sinstute, S_class, Ssubject, Sdes, currentDate,userProfileInfo);
                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(upload);

                progressBar.setVisibility(View.GONE);
                name.setText("");
                address.setText("");
                mobile.setText("");
                salary.setText("");
                institute.setText("");
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

                //end...

                finish();
                Intent intent = new Intent(Student_Account.this, DisplayStudents.class);
                intent.putExtra("displaystudent", jaman);
                startActivity(intent);
                //for ads...
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        }

    }

    private void storeInfo(String x) {

        String name = x;
        SharedPreferences sharedPreferences = getSharedPreferences("Students", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("studentkey", name);
        editor.commit();
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
