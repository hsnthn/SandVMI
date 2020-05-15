package com.cybercrypt.sandvmi.ui.authentication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentForgotPasswordBinding;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class ForgotPasswordFragment extends BaseFragment {

    private FragmentForgotPasswordBinding binding;

    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false);
        setToolbarTitle(getResources().getString(R.string.toolbar_forgotpassword));
        setToolbarIcon(BaseFragment.BACK_ICON);

        binding.editFpEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    binding.editFpEmail.clearFocus();
                }
                return false;
            }
        });

        binding.editFpEmail.addTextChangedListener(textEmptyCheck);


        return binding.getRoot();
    }

    private TextWatcher textEmptyCheck= new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            binding.btnFpReset.setEnabled(binding.editFpEmail.getText().toString().length() > 0 );
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

    @Override
    public void onNavigationIconClick() {
        getActivity().onBackPressed();
    }
}