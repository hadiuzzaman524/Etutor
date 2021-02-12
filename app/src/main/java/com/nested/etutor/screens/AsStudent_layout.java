package com.nested.etutor.screens;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.nested.etutor.R;
import com.nested.etutor.SplashScreen;

public class AsStudent_layout extends AppCompatActivity {

    private AlertDialog.Builder builder;
    StringBuilder name = new StringBuilder();
    private Window window;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as_student_layout);

        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.commoncolor));

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.contains("key")) {
            name.append(sharedPreferences.getString("key", "jaman"));
        }
        String x = name.toString();
        if (x.equals("hadiuzzaman")) {
            finish();
            Intent intent = new Intent(AsStudent_layout.this, SplashScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    work();
                    Intent intent = new Intent(AsStudent_layout.this, Loginpagedesign.class);
                    startActivity(intent);
                    finish();
                }

            });

            thread.start();
        }
    }

    void work() {
        for (int p = 10; p <= 100; p += 10) {
            try {
                Thread.sleep(90);
            } catch (InterruptedException e) {
                Toast.makeText(getApplicationContext(), "Not fit on your device", Toast.LENGTH_LONG).show();
            }
        }
    }

}

