package com.nested.etutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.nested.etutor.screens.Homepage_activity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.commoncolor));


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                work();
                Intent intent=new Intent(SplashScreen.this, Homepage_activity.class);
                startActivity(intent);
                finish();
            }

        });


        thread.start();
    }

    void work()
    {
        for(int p=10; p<=100; p+=10)
        {
            try{
                Thread.sleep(90);
            }
            catch (InterruptedException e)
            {
                Toast.makeText(getApplicationContext(),"Not fit on your device",Toast.LENGTH_LONG).show();

            }
        }
    }

}
