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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.util.Utils;

public class LoginFragment extends Fragment {

    private EditText edit_user,edit_pass;
    private Button btn_login;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);

        edit_user = root.findViewById(R.id.edit_lo_uname);
        edit_pass = root.findViewById(R.id.edit_lo_pass);
        btn_login = root.findViewById(R.id.btn_lo_login);

        edit_pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edit_pass.clearFocus();
                    InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_pass.getWindowToken(), 0);
                }
                return false;
            }
        });

        edit_user.addTextChangedListener(textEmptyCheck);
        edit_pass.addTextChangedListener(textEmptyCheck);
        edit_user.setOnFocusChangeListener(focusChangeListener);
        edit_pass.setOnFocusChangeListener(focusChangeListener);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showErrors();

            }
        });

        return root;
    }

    private void showErrors(){
        if (edit_user.isFocused()) edit_user.clearFocus();
        if (edit_pass.isFocused()) edit_pass.clearFocus();

        InputMethodManager imm = (InputMethodManager)getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_pass.getWindowToken(), 0);
        LinearLayout linearLayout = getActivity().findViewById(R.id.lay_login);
        Utils.showSnackbar(getActivity(),linearLayout, getResources().getString(R.string.error_login));
        edit_pass.setBackgroundResource(R.drawable.bg_error_edit_text);
        edit_user.setBackgroundResource(R.drawable.bg_error_edit_text);
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
            btn_login.setEnabled(edit_user.getText().toString().length() > 0 && edit_pass.getText().toString().length()>0);
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