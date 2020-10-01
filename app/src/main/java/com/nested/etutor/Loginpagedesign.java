package com.nested.etutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class Loginpagedesign extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView, forget;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder builder;
    private EditText email, password;
    int x = 0;
    private Window window;
    private String ema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.commoncolor));

        setContentView(R.layout.activity_loginpagedesign);
        button = findViewById(R.id.loginbuttonid);
        textView = findViewById(R.id.dontaccountid);
        forget = findViewById(R.id.forgetpasswordid);
        forget.setOnClickListener(this);

        textView.setOnClickListener(this);
        button.setOnClickListener(this);

        progressBar = findViewById(R.id.login_progressbar);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String s = bundle.getString("msg");
            //  Toast.makeText(getApplicationContext(),""+s,Toast.LENGTH_LONG).show();

            if (s.equals("ashrafi")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Loginpagedesign.this);
                builder.setMessage("We will send link in your email address.Please check that for reset your password.Then try to login");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else if (s.equals("riad"))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(Loginpagedesign.this);
                builder.setMessage("You are successfully registered.Please check your email for verification then try to login");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

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
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.loginbuttonid) {

            if (isNetworkAvailable())
            {
                loginUser();
            }
            else
            {
                show();
            }


        }
        if (v.getId() == R.id.dontaccountid) {

            if (x == 0) {
                email.setText("");
                password.setText("");
                finish();
                Intent intent = new Intent(Loginpagedesign.this, Signuppagedesign.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }

        if (v.getId() == R.id.forgetpasswordid) {
            //finish();
            Intent intent = new Intent(Loginpagedesign.this, Forgetpassword.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            //Toast.makeText(Loginpagedesign.this,"forget",Toast.LENGTH_LONG).show();
        }

    }

    private void loginUser() {


        ema = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

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
            password.setError("Minimum length 6 characters");
            password.requestFocus();
            return;
        } else {
            ++x;
        }

        if (x == 1) {
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(ema, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information

                        email.setText("");
                        password.setText("");

                        String result1=removeCharsFromString(ema,".");
                        storeInfo(result1);
                       // emailVerification();
                        finish();
                        Intent intent = new Intent(Loginpagedesign.this, Homepage_activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_LONG).show();

                        --x;
                        progressBar.setVisibility(View.GONE);
                        return;


                    }
                }
            });
        }

    }

    private void emailVerification() {
        FirebaseUser firebaseUser = mAuth.getInstance().getCurrentUser();
        boolean emailflag = firebaseUser.isEmailVerified();

        if (emailflag) {




            email.setText("");
            password.setText("");
            finish();
            Intent intent = new Intent(Loginpagedesign.this, Homepage_activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Verify your email address! Please check your email", Toast.LENGTH_LONG).show();
            mAuth.signOut();
            progressBar.setVisibility(View.GONE);
            --x;
        }
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

    @Override
    public void onBackPressed() {

        finish();
      /*  builder = new AlertDialog.Builder(Loginpagedesign.this);
        builder.setTitle("    eTutor  ");
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
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();*/
    }

    private void storeInfo(String x) {

        String name =x;
        SharedPreferences sharedPreferences = getSharedPreferences("storeemail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("emailkey", name);
        editor.commit();
    }
}
