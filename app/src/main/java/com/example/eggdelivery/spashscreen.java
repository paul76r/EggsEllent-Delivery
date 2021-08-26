package com.example.eggdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.eggdelivery.Activities.Login;

public class spashscreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;
    TextView appname;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spashscreen);

        appname = findViewById(R.id.appnme);
        lottie = findViewById(R.id.lottie);

        appname.animate().translationY(-850).setDuration(2700).setStartDelay(0);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(spashscreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIMER );
    }
}