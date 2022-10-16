package com.teraninja.guard.UI;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;


import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.teraninja.guard.Controil;
import com.teraninja.guard.R;



public class SplashScreen extends LocalizationActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash_screen);


        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashScreen.this, Controil.class);
            startActivity(intent);
            finish();
        }
    }, 3000);


//
//        int currentNightMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
//        switch (currentNightMode) {
//            case Configuration.UI_MODE_NIGHT_NO:
//                // Night mode is not active, we're using the light theme
//                break;
//            case Configuration.UI_MODE_NIGHT_YES:
//                // Night mode is active, we're using dark theme
//                break;
//        }
    }
}