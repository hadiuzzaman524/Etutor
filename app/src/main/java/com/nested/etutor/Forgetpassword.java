
package com.nested.etutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgetpassword extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText em;
    Button resetbutton;
    ProgressBar progressBar;
    private int x = 0;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.commoncolor));


        setContentView(R.layout.activity_forgetpassword);

        mAuth = FirebaseAuth.getInstance();

        em = findViewById(R.id.resetemailid);
        resetbutton = findViewById(R.id.resetpasswordid);
        resetbutton.setOnClickListener(this);
        progressBar = findViewById(R.id.resetprogressbarid);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.resetpasswordid) {

            String email = em.getText().toString().trim();

            if (email.isEmpty()) {
                em.setError("Enter email");
                em.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                em.setError("Enter Valid Email");
                em.requestFocus();
                return;
            } else {
                ++x;
            }
            if (x == 1) {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            //Toast.makeText(getApplicationContext(), "We will send link in your mail.please check that for reset your password.", Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(Forgetpassword.this, Loginpagedesign.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("msg","ashrafi");
                            startActivity(intent);
                           // Toast.makeText(getApplicationContext(),"We will send link in your email.please check that for reset your password.",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        } else {
                            Toast.makeText(getApplicationContext(), "Something want to be wrong.Please try again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            --x;
                        }
                    }
                });
            }

        }
    }
}
