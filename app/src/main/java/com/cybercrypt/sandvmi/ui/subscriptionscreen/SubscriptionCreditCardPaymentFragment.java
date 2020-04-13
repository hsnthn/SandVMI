package com.cybercrypt.sandvmi.ui.subscriptionscreen;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentSubscriptionCreditCardDetailsBinding;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class SubscriptionCreditCardPaymentFragment extends BaseFragment  {

    private FragmentSubscriptionCreditCardDetailsBinding binding;

    public static SubscriptionCreditCardPaymentFragment newInstance() {
        return new SubscriptionCreditCardPaymentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subscription_credit_card_details, container, false);


        setToolbarTitle(getResources().getString(R.string.subscription_choose_payment_plan_title));
        toolbarNavIcon(true);

        binding.btnCreditCardBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(SubscriptionConfirmationFragment.newInstance());
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.months_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spinSubscCardExpireMonth.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.years_array, R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.spinSubscCardExpireYear.setAdapter(adapter2);

        openKeyboard();

        binding.editSubscCardNumber.setTransformationMethod(null);
        binding.editSubscCardNumber.requestFocus();
        binding.editSubscCardNumber.addTextChangedListener(credit_card_num_watcher);
        binding.editSubscCardNumber.addTextChangedListener(textEmptyCheck);

        binding.editSubscCardCvv.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.editSubscCardCvv.addTextChangedListener(textEmptyCheck);

        binding.editSubscCardName.addTextChangedListener(textEmptyCheck);

        return binding.getRoot();
    }

    @Override
    public void onNavigationIconClick() {
        super.onNavigationIconClick();
        hideKeyboard();
        getActivity().onBackPressed();
    }

    private void openKeyboard() {
        InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void hideKeyboard() {
        try {
            InputMethodManager inputmanager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputmanager != null) {
                inputmanager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception var2) {
        }

    }


    private TextWatcher credit_card_num_watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2,
                                  int arg3) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String initial = s.toString();
            // remove all non-digits characters
            String processed = initial.replaceAll("\\D", "");
            // insert a space after all groups of 4 digits that are followed by another digit
            processed = processed.replaceAll("(\\d{4})(?=\\d)", "$1 ");
            // to avoid stackoverflow errors, check that the processed is different from what's already
            //  there before setting
            if (!initial.equals(processed)) {
                // set the value
                s.replace(0, initial.length(), processed);
            }

        }

    };

    private TextWatcher textEmptyCheck = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            binding.btnCreditCardBuy.setEnabled(binding.editSubscCardNumber.getText().toString().length() > 0 && binding.editSubscCardCvv.getText().toString().length() > 0 && binding.editSubscCardName.getText().toString().length() > 0);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }
    };
}
