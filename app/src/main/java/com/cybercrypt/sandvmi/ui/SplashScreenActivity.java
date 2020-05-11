package com.cybercrypt.sandvmi.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.util.Utils;
import com.cybercrypt.sandvmi.util.PrefHelper;


public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        int SPLASH_DISPLAY_LENGTH = 1000;

        /**
         * Application first run
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PrefHelper.getFirstTimeRun(getApplicationContext()) == 0) {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, IntroSliderScreenActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), AuthenticationActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideNavigations(this);
    }

}