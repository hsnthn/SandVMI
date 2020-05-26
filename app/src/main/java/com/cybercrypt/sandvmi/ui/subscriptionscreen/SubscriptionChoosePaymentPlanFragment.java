package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentSubscriptionChoosePaymentMethodBinding;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class SubscriptionChoosePaymentPlanFragment extends BaseFragment {

    private  FragmentSubscriptionChoosePaymentMethodBinding binding;

    public SubscriptionChoosePaymentPlanFragment() {
        // Required empty public constructor
    }

    public static SubscriptionChoosePaymentPlanFragment newInstance() {
        return new SubscriptionChoosePaymentPlanFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_subscription_choose_payment_method, container, false);

        setToolbarTitle(getResources().getString(R.string.subscription_choose_payment_plan_title));
        setToolbarIcon(BaseFragment.BACK_ICON);

        binding.checkSubscriptipnPaymentMethod1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.subsc1.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_selected));
                    binding.subsc2.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_unselected));
                    binding.checkSubscriptipnPaymentMethod2.setChecked(false);
                    enableButton();
                    binding.checkSubscriptipnPaymentMethod1.setEnabled(false);
                    binding.checkSubscriptipnPaymentMethod2.setEnabled(true);
                }
            }
        });

        binding.checkSubscriptipnPaymentMethod2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.subsc2.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_selected));
                    binding.subsc1.setBackground(getResources().getDrawable(R.drawable.ic_check_sub_plan_unselected));
                    binding.checkSubscriptipnPaymentMethod1.setChecked(false);
                    enableButton();
                    binding.checkSubscriptipnPaymentMethod2.setEnabled(false);
                    binding.checkSubscriptipnPaymentMethod1.setEnabled(true);
                }
            }
        });



        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkSubscriptipnPaymentMethod1.isChecked()){
                    changeFragment(SubscriptionCreditCardPaymentFragment.newInstance(),"SubscriptionCreditCardPaymentFragment");
                }else{
                    changeFragment(SubscriptionCryptocurrencyPaymentFragment.newInstance(),"SubscriptionCryptocurrencyPaymentFragment");
                }

            }
        });


        return binding.getRoot();
    }

    @Override
    public void onNavigationIconClick() {
        super.onNavigationIconClick();
        getActivity().onBackPressed();
    }

    private void enableButton(){
        if (!binding.btnContinue.isEnabled()) {
            binding.btnContinue.setEnabled(true);
        }
    }


}

