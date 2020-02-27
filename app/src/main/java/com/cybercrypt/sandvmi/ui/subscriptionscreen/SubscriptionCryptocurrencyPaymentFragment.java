package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.cybercrypt.sandvmi.R;
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

        View view =inflater.inflate(R.layout.fragment_subscription_cryptocurrency_details, container, false);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_subs);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_nav_back));
        final TextView page_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        page_title.setText(getResources().getString(R.string.subscription_choose_payment_plan_title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(SubscriptionChoosePaymentPlanFragment.newInstance());
            }
        });






        return view;
    }


}

