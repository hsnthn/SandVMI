package com.cybercrypt.sandvmi.ui.homescreen;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentLoginBinding;
import com.cybercrypt.sandvmi.ui.util.Utils;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        binding.editLoPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    binding.editLoPass.clearFocus();
                    hideKeyboard();
                }
                return false;
            }
        });

        binding.editLoUname.addTextChangedListener(textEmptyCheck);
        binding.editLoPass.addTextChangedListener(textEmptyCheck);
        binding.editLoUname.setOnFocusChangeListener(focusChangeListener);
        binding.editLoPass.setOnFocusChangeListener(focusChangeListener);

        binding.btnLoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showErrors();

            }
        });

        return binding.getRoot();
    }

    private void showErrors(){
        if (binding.editLoUname.isFocused()) binding.editLoUname.clearFocus();
        if (binding.editLoPass.isFocused()) binding.editLoPass.clearFocus();

        hideKeyboard();

        Utils.showSnackbar(getActivity(),binding.layLogin, getResources().getString(R.string.error_login));
        binding.editLoPass.setBackgroundResource(R.drawable.bg_error_edit_text);
        binding.editLoUname.setBackgroundResource(R.drawable.bg_error_edit_text);
    }

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.bg_edit_text);
            }
        }
    };

    private TextWatcher textEmptyCheck= new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            binding.btnLoLogin.setEnabled(binding.editLoUname.getText().toString().length() > 0 && binding.editLoPass.getText().toString().length()>0);
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

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.editLoPass.getWindowToken(), 0);
    }

}