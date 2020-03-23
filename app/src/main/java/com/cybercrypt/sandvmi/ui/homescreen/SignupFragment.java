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
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.util.Utils;

public class SignupFragment extends Fragment {

    private boolean email_val_status=false;
    private boolean uname_val_status=false;
    private boolean pass_val_status=false;

    private Button btn_signup;
    private TextView txt_signup_info;
    private EditText edit_email,edit_user,edit_pass;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        btn_signup =root.findViewById(R.id.btn_su_signup);
        txt_signup_info =root.findViewById(R.id.txt_su_info);
        edit_email = root.findViewById(R.id.edit_su_email);
        edit_user = root.findViewById(R.id.edit_su_uname);
        edit_pass = root.findViewById(R.id.edit_su_pass);

        edit_email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (Utils.checkEmailPattern(edit_email.getText().toString())){
                        email_val_status=true;
                        edit_email.setBackgroundResource(R.drawable.bg_success_edit_text);
                    }else{
                        email_val_status=false;
                        edit_email.setBackgroundResource(R.drawable.bg_error_edit_text);
                    }
                    checkValidations();
                }
                return false;
            }
        });

        edit_user.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (Utils.checkUserNamePattern(edit_user.getText().toString())){
                        uname_val_status=true;
                        edit_user.setBackgroundResource(R.drawable.bg_success_edit_text);
                    }else{
                        uname_val_status=false;
                        edit_user.setBackgroundResource(R.drawable.bg_error_edit_text);
                    }
                    checkValidations();
                }
                return false;
            }
        });

        edit_pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (Utils.checkPasswordPattern(edit_pass.getText().toString())){
                        pass_val_status=true;
                        edit_pass.setBackgroundResource(R.drawable.bg_success_edit_text);
                    }else{
                        pass_val_status=false;
                        edit_pass.setBackgroundResource(R.drawable.bg_error_edit_text);
                    }
                    edit_pass.clearFocus();
                    InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_pass.getWindowToken(), 0);
                    checkValidations();

                }
                return false;
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.checkPasswordPattern(edit_pass.getText().toString())){
                    pass_val_status=true;
                    edit_pass.setBackgroundResource(R.drawable.bg_success_edit_text);
                }else{
                    pass_val_status=false;
                    edit_pass.setBackgroundResource(R.drawable.bg_error_edit_text);
                }

                if (Utils.checkUserNamePattern(edit_user.getText().toString())){
                    uname_val_status=true;
                    edit_user.setBackgroundResource(R.drawable.bg_success_edit_text);
                }else{
                    uname_val_status=false;
                    edit_user.setBackgroundResource(R.drawable.bg_error_edit_text);
                }
                if (Utils.checkEmailPattern(edit_email.getText().toString())){
                    email_val_status=true;
                    edit_email.setBackgroundResource(R.drawable.bg_success_edit_text);
                }else{
                    email_val_status=false;
                    edit_email.setBackgroundResource(R.drawable.bg_error_edit_text);
                }
                edit_email.clearFocus();
                edit_pass.clearFocus();
                edit_user.clearFocus();
                boolean validations = checkValidations();

                if (validations){

                }

            }
        });

        return root;
    }


    private boolean checkValidations(){
        if(email_val_status && uname_val_status && pass_val_status){
            btn_signup.setEnabled(true);
            txt_signup_info.setVisibility(View.GONE);
            return true;
        }else{
            if (btn_signup.isEnabled()) {
                btn_signup.setEnabled(false);
                txt_signup_info.setVisibility(View.VISIBLE);
            }
            return false;
        }
    }



}
