package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentSubscriptionChoosePlanBinding;
import com.cybercrypt.sandvmi.ui.AuthenticationActivity;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class SubscriptionChoosePlanFragment extends BaseFragment {

    private FragmentSubscriptionChoosePlanBinding binding;

    public SubscriptionChoosePlanFragment() {
        // Required empty public constructor
    }

    public static SubscriptionChoosePlanFragment newInstance() {
        return new SubscriptionChoosePlanFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_subscription_choose_plan, container, false);

        setToolbarTitle(getResources().getString(R.string.subscription_choose_plan_title));
        setToolbarIcon(BaseFragment.BACK_ICON);

        binding.checkSubscriptipnMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.subsc1.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_selected));
                    binding.subsc2.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_unselected));
                    binding.checkSubscriptipnMenu2.setChecked(false);
                    enableButton();
                    binding.checkSubscriptipnMenu.setEnabled(false);
                    binding.checkSubscriptipnMenu2.setEnabled(true);
                }
            }
        });

        binding.checkSubscriptipnMenu2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.subsc2.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_selected));
                    binding.subsc1.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_unselected));
                    binding.checkSubscriptipnMenu.setChecked(false);
                    enableButton();
                    binding.checkSubscriptipnMenu2.setEnabled(false);
                    binding.checkSubscriptipnMenu.setEnabled(true);
                }
            }
        });


        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(SubscriptionChoosePaymentPlanFragment.newInstance());
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onNavigationIconClick() {
        super.onNavigationIconClick();
        goToAuthentication();
    }

    private void enableButton(){
        if (!this.binding.btnContinue.isEnabled()) {
            this.binding.btnContinue.setEnabled(true);
        }
    }

    private void goToAuthentication() {
        Intent i = new Intent(getActivity(), AuthenticationActivity.class);
        startActivity(i);
        getActivity().finish();
    }


}

