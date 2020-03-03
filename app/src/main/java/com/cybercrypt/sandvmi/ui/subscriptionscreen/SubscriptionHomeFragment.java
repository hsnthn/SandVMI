package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.HomeActivity;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;


public class SubscriptionHomeFragment extends BaseFragment {


    public SubscriptionHomeFragment() {
        // Required empty public constructor
    }

    public static SubscriptionHomeFragment newInstance() {
        return new SubscriptionHomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_subscription_home, container, false);


        final TextView trial7Day = (TextView)view.findViewById(R.id.link_skip);

        trial7Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHome();
            }
        });


        final Button btn_subcribe_now = (Button) view.findViewById(R.id.btn_subcribe);

        btn_subcribe_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeFragment(SubscriptionChoosePlanFragment.newInstance());
            }
        });

        return view;
    }

    private void launchHome(){
        Intent homeIntenet = new Intent(getActivity(), HomeActivity.class);
        startActivity(homeIntenet);
        getActivity().finish();

    }



}
