package com.nested.etutor.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nested.etutor.R;
import com.nested.etutor.models.PersonalUserInformations;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Signuppagedesign extends AppCompatActivity implements View.OnClickListener {
    Button button;

    private EditText name, email, password, confirm_pass;
    private FirebaseAuth mAuth;
    private TextView alreadyacc;
    private ProgressBar progressBar;
    private AlertDialog.Builder builder;
    String value = "";
    int x = 0;
    private Window window;
    private String nam;
    private  String ema;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.commoncolor));

        setContentView(R.layout.activity_signuppagedesign);

        mAuth = FirebaseAuth.getInstance();
//storage
        databaseReference= FirebaseDatabase.getInstance().getReference("PublicUserInformation");

        button = findViewById(R.id.createaccountid);
        button.setOnClickListener(this);
        alreadyacc = findViewById(R.id.alreadyaccoundid);
        alreadyacc.setOnClickListener(this);

        name = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        confirm_pass = findViewById(R.id.signup_confirmpassword);
        progressBar = findViewById(R.id.signup_progressbar);


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
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.createaccountid) {
            if (isNetworkAvailable())
            {
                registerUser();
            }
            else{
                show();
            }


        } else if (v.getId() == R.id.alreadyaccoundid) {

            if (x == 0) {
                email.setText("");
                name.setText("");
                password.setText("");
                confirm_pass.setText("");
                finish();
                Intent intent = new Intent(Signuppagedesign.this, Loginpagedesign.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }
    }

    private void registerUser() {

         nam = name.getText().toString().trim();
         ema = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String cm_pass = confirm_pass.getText().toString().trim();

        value += nam;


        if (nam.isEmpty()) {
            name.setError("Enter name");
            name.requestFocus();
            return;
        }
        if (ema.isEmpty()) {
            email.setError("Enter Email");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(ema).matches()) {
            email.setError("Enter Valid Email");
            email.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("Enter Password");
            password.requestFocus();
            return;
        }
        if (pass.length() < 6) {
            password.setError("Minimum Length 6 Characters");
            password.requestFocus();
            return;
        }
        if (cm_pass.isEmpty()) {
            confirm_pass.setError("Enter Confirm Password");
            confirm_pass.requestFocus();
            return;
        }
        if (cm_pass.length() < 6) {
            confirm_pass.setError("Not Matched");
            confirm_pass.requestFocus();
            return;
        }
        if (cm_pass.equals(pass)) {

            ++x;

            if (x == 1) {

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(ema, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information


                            storeData();
                            email.setText("");
                            name.setText("");
                            password.setText("");
                            confirm_pass.setText("");
                           // emailVerification();
                            finish();
                            Intent intent = new Intent(Signuppagedesign.this, Homepage_activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.

                            --x;
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Already registered", Toast.LENGTH_LONG).show();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Registration fail", Toast.LENGTH_LONG).show();
                            }


                        }
                    }
                });

            }
        } else {
            // Toast.makeText(getApplicationContext(), "Password are not matches!", Toast.LENGTH_LONG).show();
            confirm_pass.setError("Not Matched");
            confirm_pass.requestFocus();
            return;
        }

    }

    private void emailVerification() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {

                        mAuth.signOut();
                        finish();
                        Intent intent = new Intent(Signuppagedesign.this, Loginpagedesign.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("msg","riad");
                        startActivity(intent);
                       // Toast.makeText(getApplicationContext(), "Successfully Registered.Please verify your email address", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Verification failed", Toast.LENGTH_LONG).show();
                        --x;

                    }
                }
            });
        }
    }
    public void storeData()
    {
        //sname=name.getText().toString().trim();
        //sage=age.getText().toString().trim();
       // smobile=mobile.getText().toString().trim();

          String x=".";
          String key=removeCharsFromString(ema,x);

          storeInfo(key);
         Calendar calendar = Calendar.getInstance();
         String currentDate = DateFormat.getDateInstance().format(calendar.getTime());


        PersonalUserInformations student=new PersonalUserInformations(nam,ema,"","",currentDate,"","","","","");

        databaseReference.child(key).setValue(student);

       /* Map<String,Object> updatedValues=new HashMap<>();

        updatedValues.put("/-M36hpSFT6hFQMSJYwyf/name",sname);
        updatedValues.put("/-M36hpSFT6hFQMSJYwyf/mobile",smobile);
        updatedValues.put("/-M36hpSFT6hFQMSJYwyf/age",sage);

        databaseReference.updateChildren(updatedValues);*/
    }


    //remove anything from string
    static String removeCharsFromString(String word1, String word2)
    {
        StringBuilder sb = new StringBuilder(word1);

       // System.out.println(sb);
        //char[] word2characters= word2.toCharArray();
        HashMap<Character, Integer> table = new HashMap<Character, Integer>();

        for (int i = 0; i < word2.length(); i++)
        {
            table.put(word2.charAt(i), 1);

        }

        int p = 0;
        for (int i = 0; i < word1.length(); i++)
        {

            if (table.containsKey(word1.charAt(i)))
            {
                if (p == 0)
                {
                    sb.deleteCharAt(i);
                    //p++;
                }
                else
                {
                    sb.deleteCharAt(i - p);
                }
                //System.out.println(sb);
                p++;
            }

        }

        return sb.toString();
    }

    private void storeInfo(String x) {

        String name =x;
        SharedPreferences sharedPreferences = getSharedPreferences("storeemail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("emailkey", name);
        editor.commit();
    }


}
