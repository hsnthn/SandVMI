package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class SubscriptionChoosePlanFragment extends BaseFragment {


    private Button btn_paymentMethod;

    public SubscriptionChoosePlanFragment() {
        // Required empty public constructor
    }

    public static SubscriptionChoosePlanFragment newInstance() {
        return new SubscriptionChoosePlanFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_subscription_choose_plan, container, false);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_subs);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_nav_back));
        final TextView page_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        page_title.setText(getResources().getString(R.string.subscription_choose_plan_title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(SubscriptionHomeFragment.newInstance());
            }
        });

        final CheckBox chk_sub_plan_1 = (CheckBox) view.findViewById(R.id.check_subscriptipn_menu);
        final CheckBox chk_sub_plan_2 = (CheckBox) view.findViewById(R.id.check_subscriptipn_menu_2);
        final RelativeLayout chk_1_bg = (RelativeLayout)view.findViewById(R.id.subsc_1);
        final RelativeLayout chk_2_bg = (RelativeLayout)view.findViewById(R.id.subsc_2);
        btn_paymentMethod = (Button)view.findViewById(R.id.btn_continue);

        chk_sub_plan_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    chk_1_bg.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_selected));
                    chk_2_bg.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_unselected));
                    chk_sub_plan_2.setChecked(false);
                    enableButton();
                    chk_sub_plan_1.setEnabled(false);
                    chk_sub_plan_2.setEnabled(true);
                }
            }
        });

        chk_sub_plan_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    chk_2_bg.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_selected));
                    chk_1_bg.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_unselected));
                    chk_sub_plan_1.setChecked(false);
                    enableButton();
                    chk_sub_plan_2.setEnabled(false);
                    chk_sub_plan_1.setEnabled(true);
                }
            }
        });



        btn_paymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(SubscriptionChoosePaymentPlanFragment.newInstance());
            }
        });



        return view;
    }

    private void enableButton(){
        if (!btn_paymentMethod.isEnabled()) {
            btn_paymentMethod.setEnabled(true);
            btn_paymentMethod.setBackground( getResources().getDrawable(R.drawable.bg_buttons));

        }
    }


}

