package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentSubscriptionCryptocurrencyDetailsBinding;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class SubscriptionCryptocurrencyPaymentFragment extends BaseFragment {



    public SubscriptionCryptocurrencyPaymentFragment() {
        // Required empty public constructor
    }

    public static SubscriptionCryptocurrencyPaymentFragment newInstance() {
        return new SubscriptionCryptocurrencyPaymentFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentSubscriptionCryptocurrencyDetailsBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_subscription_cryptocurrency_details, container, false);

        setToolbarTitle(getResources().getString(R.string.subscription_choose_payment_plan_title));
        setToolbarIcon(BaseFragment.BACK_ICON);


        return binding.getRoot();
    }

    @Override
    public void onNavigationIconClick() {
        super.onNavigationIconClick();
        getActivity().onBackPressed();
    }
}

