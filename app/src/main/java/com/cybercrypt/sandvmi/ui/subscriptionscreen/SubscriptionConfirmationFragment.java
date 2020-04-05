package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentSubscriptionConfirmationBinding;
import com.cybercrypt.sandvmi.ui.MainActivity;
import com.cybercrypt.sandvmi.ui.SubscriptionScreenActivity;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;


public class SubscriptionConfirmationFragment extends BaseFragment {

    public SubscriptionConfirmationFragment() {
        // Required empty public constructor
    }

    public static SubscriptionConfirmationFragment newInstance() {
        SubscriptionConfirmationFragment fragment = new SubscriptionConfirmationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentSubscriptionConfirmationBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_subscription_confirmation, container, false);

        setToolbarTitle(getResources().getString(R.string.subscription_confirmation_title));
        toolbarNavIcon(false);

        String str ="You will be billed £15 on 21/02/20 and once a month thereafter until cancelled.";
        binding.confirmationTxt1.setText(str);
        binding.btnEnterSandHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        return binding.getRoot();
    }

    private void launchHomeScreen() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }



}

