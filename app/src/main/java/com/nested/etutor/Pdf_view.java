package com.nested.etutor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Pdf_view extends AppCompatActivity {

   private WebView webView;
   private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            setContentView(R.layout.activity_pdf_view);


            webView = findViewById(R.id.webviewid);
            progressBar = findViewById(R.id.progress_pdf_id);

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                                         @Override
                                         public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                             super.onPageStarted(view, url, favicon);
                                             progressBar.setVisibility(View.VISIBLE);
                                             // setTitle("Loading...");
                                         }

                                         @Override
                                         public void onPageFinished(WebView view, String url) {
                                             super.onPageFinished(view, url);
                                             progressBar.setVisibility(View.GONE);
                                             // setTitle(view.getTitle());
                                         }
                                     }
            );

//new
            if (savedInstanceState == null) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {

                        if(isNetworkAvailable())
                        {
                            loadwebsite();//old

                        }
                        else
                        {
                            progressBar.setVisibility(View.GONE);
                            show();
                           // Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }//


        } catch (Exception e) {

            Toast.makeText(getApplicationContext(),"Not open in your device",Toast.LENGTH_LONG).show();
            finish();
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

    private void loadwebsite() {

        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {
            String value = bundle.getString("name");

            //class one
            if (value.equals("Bangla1")) {
                webView.loadUrl("https://drive.google.com/file/d/1TPHsoBYBY-e_9P6OB-227HNF8mziG7ZZ/view");
            } else if (value.equals("English1")) {
                webView.loadUrl("https://drive.google.com/file/d/1vdZA4d4jVJFSy6BgGl-wAyP75uqoiGMN/view");
            } else if (value.equals("Math1")) {
                webView.loadUrl("https://drive.google.com/file/d/1UKcRnDcBi_6HX2rXuxTs6MJf0ki-xHpc/view");
            }

            //class two

            if (value.equals("Bangla2")) {
                webView.loadUrl("https://drive.google.com/file/d/1Qbzb3oMEQNHfzlhjVieb2XrAYwFpW61G/view");
            } else if (value.equals("English2")) {
                webView.loadUrl("https://drive.google.com/file/d/1BV2Vj_n-034yVlHM2HPlXH1GiCZp-P7M/view");
            } else if (value.equals("Math2")) {
                webView.loadUrl("https://drive.google.com/file/d/1XlPMXbxUvaJriaihgb9qVq1AkHiKhvop/view");
            }

            //class three
            else if (value.equals("bangla3")) {
                webView.loadUrl("https://drive.google.com/file/d/11pWRj2iosqv0BBDP-u67lTxyjhQop0Hx/view");
            } else if (value.equals("math3")) {
                webView.loadUrl("https://drive.google.com/file/d/18aFJ3CvAo7_1eFHWcZ-SflZGyyFBCZHp/view");
            } else if (value.equals("english3")) {
                webView.loadUrl("https://drive.google.com/file/d/1kR2-PNn32r0kC83e8OXJ0GZ8OyXiLUba/view");
            } else if (value.equals("islam3")) {
                webView.loadUrl("https://drive.google.com/file/d/1eWpbUYfq3HactBM6nLx7izAuHZwFCDOT/view");
            } else if (value.equals("hindu3")) {
                webView.loadUrl("https://drive.google.com/file/d/163PXDCr9XFY8V821IRYAo-mI95b3C4vM/view");
            } else if (value.equals("ethical3")) {
                webView.loadUrl("https://drive.google.com/file/d/1gtGskIIGVVq6hxYtLqprD6PQXxZv6--D/view");
            } else if (value.equals("somaj3")) {
                webView.loadUrl("https://drive.google.com/file/d/1qO1dy5AAtsN1RkBT2m628VlgatmdflBI/view");
            }
            else if (value.equals("biggan3")) {
                webView.loadUrl("https://drive.google.com/file/d/1BuVN0teMBTo2MHosbatn6EGcAfhTzsDs/view");
            }

            //class four...

            else if (value.equals("bangla4")) {
                webView.loadUrl("https://drive.google.com/file/d/1B8rkPqScvT2LBH4HVNrWzozx9tXo5gr9/view");
            } else if (value.equals("math4")) {
                webView.loadUrl("https://drive.google.com/file/d/1Bo0N_8icdALUtTSpE_A9wdukgEENl4B_/view");
            } else if (value.equals("english4")) {
                webView.loadUrl("https://drive.google.com/file/d/161tPf8qCUTQ_4-Kmo4390u5DXOuJ6xoI/view");
            } else if (value.equals("islam4")) {
                webView.loadUrl("https://drive.google.com/file/d/1TQZai9qifVRC9XYMMI4ngrgUHMYi3nR-/view");
            } else if (value.equals("hindu4")) {
                webView.loadUrl("https://drive.google.com/file/d/1cpSwQQrg4zIV68XiNGnljfxIuwxazPyn/view");
            } else if (value.equals("ethical4")) {
                webView.loadUrl("https://drive.google.com/file/d/1-gTOB7s3CorXhhSmTnTy81R8mpin87c7/view");
            } else if (value.equals("somaj4")) {
                webView.loadUrl("https://drive.google.com/file/d/1SGRqu5lMFW8hFxh594j_5Kznt8Vw94w2/view");
            } else if (value.equals("biggan4")) {
                webView.loadUrl("https://drive.google.com/file/d/1KQb1dFmFYrZb4Mrqv2zMyUPmZrFXNqBL/view");
            }

            //class five

            else if (value.equals("bangla5")) {
                webView.loadUrl("https://drive.google.com/file/d/1nWloUqY6vBJdNvxV085Kk3zFcxTA0Ced/view");
            } else if (value.equals("math5")) {
                webView.loadUrl("https://drive.google.com/file/d/1iKaq5WjAo89ApTpXH1c6z0XgCOjRgDZ_/view");
            } else if (value.equals("english5")) {
                webView.loadUrl("https://drive.google.com/file/d/1k-VVPtHjcCWWhd1kHg2gMs9LzUdlDgxz/view");
            } else if (value.equals("islam5")) {
                webView.loadUrl("https://drive.google.com/file/d/1uq71wdEAmyK5MT2_4vU1-CWMVimOTWGp/view");
            } else if (value.equals("science5")) {
                webView.loadUrl("https://drive.google.com/file/d/1owW1nVBVlsKmyU8Fi7hbGb3zgG-Y6gfo/view");
            } else if (value.equals("somaj5")) {
                webView.loadUrl("https://drive.google.com/file/d/16PDNyprjDyy1SSud_q3dDBeSFNm4tq1E/view");
            } else if (value.equals("hindu5")) {
                webView.loadUrl("https://drive.google.com/file/d/1CIZTCHWA7t3NWeP--n-Zmhz36Heq2GIX/view");
            } else if (value.equals("christan5")) {
                webView.loadUrl("https://drive.google.com/file/d/1I_iO2HupsMiYej5K7LH6lu5ISqEQ8oev/view");
            }


            //class six..

            if (value.equals("Anando Path6")) {
                webView.loadUrl("https://drive.google.com/file/d/12Ag53b6L7Sza9sFedaBLd-OlPPQ7kNDB/view");

            } else if (value.equals("Bangla Grammer and Nirmity6")) {
                webView.loadUrl("https://drive.google.com/file/d/1-kXc1eBRiJ2GbFrtoPULAE6zAITXDgcp/view");

            }
            else if (value.equals("biggan6")) {
                webView.loadUrl("https://drive.google.com/file/d/11G8j-omy5ErN0AVGYa0Xm2cZX-5uQ_Sb/view");

            }
            else if (value.equals("somaj6")) {
                webView.loadUrl("https://drive.google.com/file/d/1YdoMgTY0sUwIVgDFGXZ8_n26AEdCQiEI/view");
            }
            else if (value.equals("hindu6")) {
                webView.loadUrl("https://drive.google.com/file/d/1p7Ws5B11rpOAjX090kSAKwtFxVPk3t-x/view");
            }
            else if (value.equals("islam6")) {
                webView.loadUrl("https://drive.google.com/file/d/1GVX0kdumumLd3JP38baXBchWPWm8rleZ/view");
            }
            else if (value.equals("Krishi Shikkha6")) {
                webView.loadUrl("https://drive.google.com/file/d/1al0hJCk1gQotHL3ITeoJNO38xeV-KUXf/view");

            } else if (value.equals("Domestic Science6")) {
                webView.loadUrl("https://drive.google.com/file/d/16yl6GVln7qac_xxSQ2jhTpOmAymKb5Tw/view");

            } else if (value.equals("Physical studies and health6")) {
                webView.loadUrl("https://drive.google.com/file/d/1OXW0zNtSn34xQhvEagztWOftGsNvVY26/view");

            } else if (value.equals("ICT6")) {
                webView.loadUrl("https://drive.google.com/file/d/1v4TM9137UZ1R39nwgKInF5kiWtzXI_61/view");

            } else if (value.equals("Work and Life Oriented Studies6")) {
                webView.loadUrl("https://drive.google.com/file/d/1m5Q9pgSTpYjG6f3TxacFzKTc_qZZplgc/view");

            } else if (value.equals("English Grammer and Composition6")) {
                webView.loadUrl("https://drive.google.com/file/d/1QW-M4ZqNDHRzQ8hFhQqhwb-g7i-tG-mM/view");

            } else if (value.equals("Charuphat6")) {
                webView.loadUrl("https://drive.google.com/file/d/13XqMoGHqMhNaF4YYQKGcflVnIpC2933N/view");

            } else if (value.equals("General Marh6")) {
                webView.loadUrl("https://drive.google.com/file/d/1uJrruRv85yNX_VSJ74uB9TFqQhJjJn0U/view");

            } else if (value.equals("Charu and Karu Kola6")) {
                webView.loadUrl("https://drive.google.com/file/d/1K3miVyBmM2UwjSvzskTOaCiJEJ8VEkzL/view");

            }


            //class seven....

            if (value.equals("Anando Path7")) {
                webView.loadUrl("https://drive.google.com/file/d/1SEH6wB9xADfrh96OzHYPgMeYUMSIM6xf/view");

            } else if (value.equals("Bangla Grammer and Nirmity7")) {
                webView.loadUrl("https://drive.google.com/file/d/1-XiYL3zy0-WYOYudKc5tFELr2TBo36O0/view");

            } else if (value.equals("Math7")) {
                webView.loadUrl("https://drive.google.com/file/d/1XKZdSGI2bBGKsiNEnaPUW053AqcdGPyE/view");

            }
            else if (value.equals("gir7")) {
                webView.loadUrl("https://drive.google.com/file/d/1crQD72TkTqcf6sdFZeguJ1ZhqEBgyo4Y/view");

            }
            else if (value.equals("sopto7")) {
                webView.loadUrl("https://drive.google.com/file/d/1vDWbxGbNd03qDMWhGqqT6gOka-wKrJyA/view");
            }
            else if (value.equals("charuk7")) {
                webView.loadUrl("https://drive.google.com/file/d/1IV7Gk9Dn1IgEOB-SaidqYTMGf7Ceg9nY/view");
            }
            else if (value.equals("hindu7")) {
                webView.loadUrl("https://drive.google.com/file/d/16v-SvN6MAw3eeokqSdxgUrQ1YntrmKeX/view");
            }
            else if (value.equals("eng7")) {
                webView.loadUrl("https://drive.google.com/file/d/1dGRR2aVCxFjpTNbyitklYx-xG_EL2Qdr/view");
            }
            else if (value.equals("Agriculture studies7")) {
                webView.loadUrl("https://drive.google.com/file/d/1qdO-M7RRTpLtdZw3F5qSqXPYUH0_4Ev5/view");

            } else if (value.equals("Domestic Science7")) {
                webView.loadUrl("https://drive.google.com/file/d/1crQD72TkTqcf6sdFZeguJ1ZhqEBgyo4Y/view");

            } else if (value.equals("Saharon Biggan7")) {
                webView.loadUrl("https://drive.google.com/file/d/1dV-WZqCndSOVOwiTMtMgpjapI35O01Ld/view");

            } else if (value.equals("Islam and Ethics7")) {
                webView.loadUrl("https://drive.google.com/file/d/1PLdZUtRDzmFJCbm0F0KNNQDPQb3CYYZ_/view");

            } else if (value.equals("English Grammer and Composition7")) {
                webView.loadUrl("https://drive.google.com/file/d/1ixRJ30CwOi1xT978ktNKAp25ZTUObWnI/view");
            } else if (value.equals("Bangladesh and Bissho Porichoy7")) {
                webView.loadUrl("https://drive.google.com/file/d/1HJs9gBU8ZlDm8el2E_mEsVmDXcgZ6A_w/view");
            } else if (value.equals("Physical studies and health7")) {
                webView.loadUrl("https://drive.google.com/file/d/1CEA1jy4N5YMTPPJnAvyOXwNNg8ERfTdr/view");
            } else if (value.equals("ICT7")) {
                webView.loadUrl("https://drive.google.com/file/d/11h2nGrdISfESa65ejh1KhpvdYxwA0gbV/view");
            } else if (value.equals("Work and Life Oriented Studies7")) {
                webView.loadUrl("https://drive.google.com/file/d/1pNQ19wtTHSHrqTPc6xKQe6KgIr63Yzfp/view");
            }

            //class eight...

            else if (value.equals("Anando Path8")) {
                webView.loadUrl("https://drive.google.com/file/d/1DTJbtM5ENQU3ELLaEAuXTsM_73Lqwnz5/view");
            } else if (value.equals("Bangla Grammer and Nirmity8")) {
                webView.loadUrl("https://drive.google.com/file/d/1Kf2-DP_OnA7ValWDnV3z2OjsXQiaCJZs/view");
            } else if (value.equals("Sdharon Gonit8")) {
                webView.loadUrl("https://drive.google.com/file/d/1A0xVnvfUs15ajrU-SSLXB6KOdNvCUlWf/view");
            } else if (value.equals("English Grammer and Composition8")) {
                webView.loadUrl("https://drive.google.com/file/d/1husSsad9UEpM19UPmufhx-idFjiluMYG/view");
            } else if (value.equals("English for Today8")) {
                webView.loadUrl("https://drive.google.com/file/d/1bj89echN4zV4s-TkLgLYHtYTMpCni5jt/view");
            } else if (value.equals("sahitto8")) {
                webView.loadUrl("https://drive.google.com/file/d/1Rhq9SSXFOdgXPHYVi6n3r71E4B9zNOsB/view");
            }
            else if (value.equals("somaj8")) {
                webView.loadUrl("https://drive.google.com/file/d/1RsZ1SYasjCDnC4GAZcamqyi73dKpAR9W/view");
            }
            else if (value.equals("islam8")) {
                webView.loadUrl("https://drive.google.com/file/d/149Seo52WUhMq-keYIc8qdHCdDKjRbOhx/view");
            }
            else if (value.equals("hindu8")) {
                webView.loadUrl("https://drive.google.com/file/d/1cNov5uxhEibwNXhbNlx9aSlVxRmoHwNI/view");
            }
            else if (value.equals("biggan8")) {
                webView.loadUrl("https://drive.google.com/file/d/1dT5FdrTQ2c9YVTRNpjWIP5k9ZX43HT-s/view");
            }
            else if (value.equals("Agriculture studies8")) {
                webView.loadUrl("https://drive.google.com/file/d/1C5M2Jee-cZDK12zkHbxhBSDo0gTCIxug/view");
            } else if (value.equals("Domestic Science8")) {
                webView.loadUrl("https://drive.google.com/file/d/11OsFfaUGta9ztTb0tTMXZ50ZrGZ-e53b/view");
            } else if (value.equals("Physical studies and health8")) {
                webView.loadUrl("https://drive.google.com/file/d/1AgJwMxVOcvPQaOfsoFy7tJw1Sm151A6r/view");
            } else if (value.equals("ICT8")) {
                webView.loadUrl("https://drive.google.com/file/d/1Ggmrpr7x0AaMbQDRTQmTbtN5ZDgRXXAg/view");
            } else if (value.equals("Work and Life Oriented Studies8")) {
                webView.loadUrl("https://drive.google.com/file/d/1scJaaCN9Vx22PPVEkZwc6QNce6tOFRTk/view");
            } else if (value.equals("Arts and crafts8")) {
                webView.loadUrl("https://drive.google.com/file/d/1ZKtwfoFUrebwzPi_va9e3jZVhDpZ4-Qc/view");
            }

            //class nine-ten

            if (value.equals("Bangla Sohopath9")) {
                webView.loadUrl("https://drive.google.com/file/d/1NSf3IPb0c3SVvdwuvhZomTPhxA4Hzz2E/view");
            }
            else if (value.equals("sahitto9")) {
                webView.loadUrl("https://drive.google.com/file/d/147ArExQeWqn8KE_9bHue3uPfAA_ZCKWV/view");
            }
            else if (value.equals("rochona9")) {
                webView.loadUrl("https://drive.google.com/file/d/1RoYbFr2huSUaYCk61vXQHZJIaVH88xeV/view");
            }
            else if (value.equals("biggan9")) {
                webView.loadUrl("https://drive.google.com/file/d/1rWgt2LQexPjE5JAR_G-WRFrcZmAHqgmG/view");
            }
            else if (value.equals("english9")) {
                webView.loadUrl("https://drive.google.com/file/d/1jpxSRQOvKwu2Z4MeAA_hE5-wqJbQSqFj/view");
            }
            else if (value.equals("grammer9")) {
                webView.loadUrl("https://drive.google.com/file/d/1Khj8ZI6LTUkCwItqugeTTRotVhehmnhT/view");
            }
            else if (value.equals("Bangla Grammer and Nirmity9")) {
                webView.loadUrl("https://drive.google.com/file/d/1wMs2xhWEg_ht5suXE6lGzmB5rx960if0/view");
            } else if (value.equals("General Math9")) {
                webView.loadUrl("https://drive.google.com/file/d/167jA2Z-H8hNzPeJRkMI2BJmEISs-tWH5/view");
            } else if (value.equals("Higher Math9")) {
                webView.loadUrl("https://drive.google.com/file/d/1y4EK2f3hEejZA92NiBQQUALwK1gKbsSw/view");
            } else if (value.equals("Arts and crafts9")) {
                webView.loadUrl("https://drive.google.com/file/d/1pNAHhoSjpaxcbVw9_XL-C4asBxA_Dc7U/view");
            } else if (value.equals("ICT9")) {
                webView.loadUrl("https://drive.google.com/file/d/1Gg_9vknQ_tyNZ_pDUwqu1hlAwgq7t-5D/view");
            } else if (value.equals("Career Education9")) {
                webView.loadUrl("https://drive.google.com/file/d/1kwNzmzzx7hCS-ucA4mWMC9ke5Dei5X_N/view");
            }  else if (value.equals("Chemistry9")) {
                webView.loadUrl("https://drive.google.com/file/d/1rsdEgYDB2MXG_BEEd2rqdTFyR6xLAXZa/view");
            } else if (value.equals("Biology9")) {
                webView.loadUrl("https://drive.google.com/file/d/1pNvxcbkXz1WBGcscgZuVru1zFh7tCHYk/view");
            } else if (value.equals("History and civilization9")) {
                webView.loadUrl("https://drive.google.com/file/d/1vVXxkG0DMxjKRn8_iRmdRvyjHRmbG4_6/view");
            } else if (value.equals("Geography and Environment9")) {
                webView.loadUrl("https://drive.google.com/file/d/13NvA70xjRGmfIrUZJYFkIXWevn-qN0sz/view");
            } else if (value.equals("Economics9")) {
                webView.loadUrl("https://drive.google.com/file/d/1hXXnQv5bugKNSGOxyxyy0MiM87chzC0u/view");
            } else if (value.equals("Agriculture9")) {
                webView.loadUrl("https://drive.google.com/file/d/1oTPZmCy5dqWfRMqkcz95nCc4fnWL7WG3/view");
            } else if (value.equals("Domestic Science9")) {
                webView.loadUrl("https://drive.google.com/file/d/1ukPJ1zV5W-Oj4p7m5y8ShZj6Y0iEHhSA/view");
            } else if (value.equals("Civics and Citizenship9")) {
                webView.loadUrl("https://drive.google.com/file/d/1zTGzBuzPywGBhCJJtnnHM0DHHOjsT8pi/view");
            } else if (value.equals("Physics9")) {
                webView.loadUrl("https://drive.google.com/file/d/12yi6t-a4XwGwyaki8d4euzYp44I6Iteh/view");
            }
            else if (value.equals("hisab9")) {
                webView.loadUrl("https://drive.google.com/file/d/1bi8SbGfJhlMTz-yQ3FggvN585T6hAlDX/view");
            }
            else if (value.equals("financ9")) {
                webView.loadUrl("https://drive.google.com/file/d/1nZTLYxm-RyQYYfBUxn9zamtrEg8wgp3T/view");
            }
            else if (value.equals("babosa9")) {
                webView.loadUrl("https://drive.google.com/file/d/1wvr1PeHdU9PwdfYCbhxnZxN-NgUhELaa/view");
            }
            else if (value.equals("islam9")) {
                webView.loadUrl("https://drive.google.com/file/d/10Q3syDXWyDKbqQnYuNi0V24cHCL0CqHq/view");
            }
            else if (value.equals("hindu9")) {
                webView.loadUrl("https://drive.google.com/file/d/10Q3syDXWyDKbqQnYuNi0V24cHCL0CqHq/view");
            }


        }


    }


    @Override
    public void onBackPressed() {
     try {
         if (webView.canGoBack()) {
             webView.goBack();
         } else {
             super.onBackPressed();
         }
     }
     catch (Exception e)
     {

     }

    }

}
