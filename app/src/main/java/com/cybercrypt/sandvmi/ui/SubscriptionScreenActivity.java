package com.cybercrypt.sandvmi.ui;


import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.ActivitySubscriptionscreenBinding;
import com.cybercrypt.sandvmi.databinding.ToolbarSubscriptionBinding;
import com.cybercrypt.sandvmi.ui.subscriptionscreen.SubscriptionChoosePlanFragment;
import com.cybercrypt.sandvmi.ui.subscriptionscreen.SubscriptionConfirmationFragment;
import com.cybercrypt.sandvmi.ui.util.FragmentUtils;

import static com.cybercrypt.sandvmi.ui.util.FragmentUtils.TRANSITION_NONE;

public class SubscriptionScreenActivity extends FragmentActivity {

    public ActivitySubscriptionscreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subscriptionscreen);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(this, SubscriptionChoosePlanFragment.newInstance(), R.id.fragment_place, false, TRANSITION_NONE);
        }

    }

    public ToolbarSubscriptionBinding getSubscriptionToolBar(){
        return  binding.toolbar;
    }

    @Override
    public void onBackPressed() {
        // disable subscription confirmation screen's back button
        if (getSupportFragmentManager().findFragmentByTag(SubscriptionConfirmationFragment.class.getCanonicalName()) instanceof SubscriptionConfirmationFragment )
        {

        }else
        super.onBackPressed();
    }
}
