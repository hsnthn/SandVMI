package com.cybercrypt.sandvmi.ui;


import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.subscriptionscreen.SubscriptionHomeFragment;

public class SubscriptionScreenActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptionscreen);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_place, SubscriptionHomeFragment.newInstance())
                    .commit();

        }


    }



}
