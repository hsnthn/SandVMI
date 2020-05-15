package com.cybercrypt.sandvmi.ui;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.ActivitySubscriptionscreenBinding;
import com.cybercrypt.sandvmi.ui.subscriptionscreen.SubscriptionChoosePlanFragment;
import com.cybercrypt.sandvmi.ui.subscriptionscreen.SubscriptionConfirmationFragment;
import com.cybercrypt.sandvmi.ui.util.BaseActivity;
import com.cybercrypt.sandvmi.ui.util.FragmentUtils;
import com.cybercrypt.sandvmi.ui.util.Utils;

import static com.cybercrypt.sandvmi.ui.util.FragmentUtils.TRANSITION_NONE;

public class SubscriptionScreenActivity extends BaseActivity {

    public ActivitySubscriptionscreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subscriptionscreen);
        bindToolbar(binding.toolbar);

        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(this, SubscriptionChoosePlanFragment.newInstance(), R.id.nav_host_fragment, false, TRANSITION_NONE);
        }

    }


    @Override
    public void onBackPressed() {
        // disable subscription confirmation screen's back button
        if (getSupportFragmentManager().findFragmentByTag(SubscriptionConfirmationFragment.class.getCanonicalName()) instanceof SubscriptionConfirmationFragment )
        {

        }else
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideNavigations(this);
    }
}
