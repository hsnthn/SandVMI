package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.HomeActivity;
import com.cybercrypt.sandvmi.ui.IntroSliderScreenActivity;

public class SubscriptionConfirmationFragment extends Fragment {


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


        View view =inflater.inflate(R.layout.fragment_subscription_confirmation, container, false);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_subs);
        final TextView page_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        page_title.setText(getResources().getString(R.string.subscription_confirmation_title));

        TextView txt_confirmation_information = view.findViewById(R.id.confirmation_txt_1);
        String str ="You will be billed £15 on 21/02/20 and once a month thereafter until cancelled.";
        txt_confirmation_information.setText(str);

        Button btn_enter_sand = view.findViewById(R.id.btn_enter_sand_home);
        btn_enter_sand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });


        return view;
    }

    private void launchHomeScreen() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }


}

