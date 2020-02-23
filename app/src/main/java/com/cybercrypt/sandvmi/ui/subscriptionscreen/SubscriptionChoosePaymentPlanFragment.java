package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;
import com.cybercrypt.sandvmi.ui.util.PaymentPlan;

public class SubscriptionChoosePaymentPlanFragment extends BaseFragment {

    private static final String ARG_PARAM = "payment";
    private PaymentPlan paymentplan;

    public SubscriptionChoosePaymentPlanFragment() {
        // Required empty public constructor
    }

    public static SubscriptionChoosePaymentPlanFragment newInstance(PaymentPlan param1) {
        SubscriptionChoosePaymentPlanFragment fragment = new SubscriptionChoosePaymentPlanFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paymentplan = (PaymentPlan) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_subscription_choose_payment_method, container, false);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_subs);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_nav_back));
        final TextView page_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        page_title.setText(getResources().getString(R.string.subscription_choose_plan_title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeFragment(SubscriptionChoosePlanFragment.newInstance());
            }
        });


        if (paymentplan==PaymentPlan.MONTH){

        }else{

        }


        return view;
    }


}

