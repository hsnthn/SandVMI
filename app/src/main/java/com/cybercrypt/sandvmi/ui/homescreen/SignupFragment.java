package com.cybercrypt.sandvmi.ui.homescreen;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentSignupBinding;
import com.cybercrypt.sandvmi.ui.util.Utils;

public class SignupFragment extends Fragment {

    private FragmentSignupBinding binding;
    private boolean email_val_status=false;
    private boolean uname_val_status=false;
    private boolean pass_val_status=false;


    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);


        binding.editSuEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (Utils.checkEmailPattern(binding.editSuEmail.getText().toString())){
                        email_val_status=true;
                        binding.editSuEmail.setBackgroundResource(R.drawable.bg_success_edit_text);
                    }else{
                        email_val_status=false;
                        binding.editSuEmail.setBackgroundResource(R.drawable.bg_error_edit_text);
                    }
                    checkValidations();
                }
                return false;
            }
        });

        binding.editSuUname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (Utils.checkUserNamePattern(binding.editSuUname.getText().toString())){
                        uname_val_status=true;
                        binding.editSuUname.setBackgroundResource(R.drawable.bg_success_edit_text);
                    }else{
                        uname_val_status=false;
                        binding.editSuUname.setBackgroundResource(R.drawable.bg_error_edit_text);
                    }
                    checkValidations();
                }
                return false;
            }
        });

        binding.editSuPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (Utils.checkPasswordPattern(binding.editSuPass.getText().toString())){
                        pass_val_status=true;
                        binding.editSuPass.setBackgroundResource(R.drawable.bg_success_edit_text);
                    }else{
                        pass_val_status=false;
                        binding.editSuPass.setBackgroundResource(R.drawable.bg_error_edit_text);
                    }
                    binding.editSuPass.clearFocus();
                    InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.editSuPass.getWindowToken(), 0);
                    checkValidations();

                }
                return false;
            }
        });

        binding.editSuEmail.setOnFocusChangeListener(focusChangeListener);
        binding.editSuPass.setOnFocusChangeListener(focusChangeListener);
        binding.editSuUname.setOnFocusChangeListener(focusChangeListener);

        binding.btnSuSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.checkPasswordPattern(binding.editSuPass.getText().toString())){
                    pass_val_status=true;
                    binding.editSuPass.setBackgroundResource(R.drawable.bg_success_edit_text);
                }else{
                    pass_val_status=false;
                    binding.editSuPass.setBackgroundResource(R.drawable.bg_error_edit_text);
                }

                if (Utils.checkUserNamePattern(binding.editSuUname.getText().toString())){
                    uname_val_status=true;
                    binding.editSuUname.setBackgroundResource(R.drawable.bg_success_edit_text);
                }else{
                    uname_val_status=false;
                    binding.editSuUname.setBackgroundResource(R.drawable.bg_error_edit_text);
                }
                if (Utils.checkEmailPattern(binding.editSuEmail.getText().toString())){
                    email_val_status=true;
                    binding.editSuEmail.setBackgroundResource(R.drawable.bg_success_edit_text);
                }else{
                    email_val_status=false;
                    binding.editSuEmail.setBackgroundResource(R.drawable.bg_error_edit_text);
                }
                binding.editSuEmail.clearFocus();
                binding.editSuPass.clearFocus();
                binding.editSuUname.clearFocus();
                boolean validations = checkValidations();

                if (validations){

                }

            }
        });

        return binding.getRoot();
    }

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (!hasFocus) {
                binding.txtSuInfo.setVisibility(View.VISIBLE);
            }else{
                binding.txtSuInfo.setVisibility(View.GONE);

            }

        }
    };


    private boolean checkValidations(){
        if(email_val_status && uname_val_status && pass_val_status){
            binding.btnSuSignup.setEnabled(true);
            return true;
        }else{
            if (binding.btnSuSignup.isEnabled()) {
                binding.btnSuSignup.setEnabled(false);
            }
            return false;
        }
    }



}
