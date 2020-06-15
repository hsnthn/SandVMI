package com.cybercrypt.sandvmi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.ActivityAuthenticationBinding;
import com.cybercrypt.sandvmi.ui.authentication.ForgotPasswordFragment;
import com.cybercrypt.sandvmi.ui.authentication.PinLockFragment;
import com.cybercrypt.sandvmi.ui.authentication.login.LoginFragment;
import com.cybercrypt.sandvmi.ui.authentication.signup.SignupFragment;
import com.cybercrypt.sandvmi.ui.util.BaseActivity;

public class AuthenticationActivity extends BaseActivity {

    ActivityAuthenticationBinding binding;
    public static final String SIGNUPTAG = "SIGNUP";
    public static final String LOGINTAG = "LOGIN";
    public static final String FORGOTPASSWORDTAG = "FORGOTPASSWORD";
    public static final String PINLOCKTAG_CREATE = "PINLOCK_CREATE";
    public static final String PINLOCKTAG_RETYPE = "PINLOCK_RETYPE";
    public static final String PINLOCKTAG = "PINLOCK";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication);
        bindToolbar(binding.toolbar);
/*
        if (PrefHelper.getPinAuth(getApplicationContext())){
            changeFragment(PinLockFragment.newInstance(PinLockFragment.PinStatus.MODE_AUTH), LOGINTAG);
        }else {
*/
        Intent intent = getIntent();
        if (intent.hasExtra("fragment")) {
            String fragName = intent.getStringExtra("fragment");
            if (fragName.equals(LOGINTAG)) {
                showLoginFragment();
            } else if (fragName.equals(SIGNUPTAG)) {
                showSignUpFragment();
            } else if (fragName.equals(PINLOCKTAG)) {
                changeFragment(PinLockFragment.newInstance(PinLockFragment.PinStatus.MODE_AUTH), LOGINTAG);
            }
        } else {
            showLoginFragment();
        }
        // }
    }

    private void showLoginFragment() {
        changeFragment(LoginFragment.newInstance(), LOGINTAG);
    }

    public void ForgotPinClick(View view) {
        changeFragment(LoginFragment.newInstance(), LOGINTAG);
    }

    private void showSignUpFragment() {
        changeFragment(SignupFragment.newInstance(), SIGNUPTAG);
    }

    public void ForgotPasswordClick(View view) {
        changeFragment(ForgotPasswordFragment.newInstance(), FORGOTPASSWORDTAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Utils.hideNavigations(this);
    }

    @Override
    public void onBackPressed() {
        hideKeyboard();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Log.w("fragment count", String.valueOf(fragmentManager.getBackStackEntryCount()));
        if (fragmentManager.getBackStackEntryCount() > 1) {

            fragmentManager.popBackStack();

        } else {
            finish();
        }
    }

    public void SignupClick(View view) {
        showSignUpFragment();
    }
}
