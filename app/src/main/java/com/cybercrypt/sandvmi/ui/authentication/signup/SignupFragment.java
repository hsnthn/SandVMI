package com.cybercrypt.sandvmi.ui.authentication.signup;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.api.Resources;
import com.cybercrypt.sandvmi.api.Status;
import com.cybercrypt.sandvmi.data.remote.model.User;
import com.cybercrypt.sandvmi.databinding.FragmentSignupBinding;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;
import com.cybercrypt.sandvmi.ui.util.Utils;
import com.cybercrypt.sandvmi.util.PrefHelper;

public class SignupFragment extends BaseFragment {

    private FragmentSignupBinding binding;
    private boolean email_val_status = false;
    private boolean uname_val_status = false;
    private boolean pass_val_status = false;
    private SignupViewModel signupViewModel;
    private String uname, pass, email;


    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupViewModel = new ViewModelProvider(requireActivity()).get(SignupViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        setToolbarTitle(getResources().getString(R.string.toolbar_signup));
        setToolbarIcon(BaseFragment.BACK_ICON);

        binding.editSuPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    binding.editSuPass.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.editSuPass.getWindowToken(), 0);

                }
                return false;
            }
        });

        binding.editSuEmail.setOnFocusChangeListener(emailFocusChangeListener);
        binding.editSuPass.setOnFocusChangeListener(passfocusChangeListener);
        binding.editSuUname.setOnFocusChangeListener(unameFocusChangeListener);

        binding.btnSuSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passValControl();
                unameValControl();
                emailValControl();
                binding.editSuEmail.clearFocus();
                binding.editSuPass.clearFocus();
                binding.editSuUname.clearFocus();
                boolean validations = checkValidations();

                if (validations) {
                    if (Utils.isNetworkConnected(getContext())) {
                        uname = binding.editSuUname.getText().toString();
                        email = binding.editSuEmail.getText().toString();
                        pass = binding.editSuPass.getText().toString();
                        signupViewModel.signup(email, uname, pass);
                    }else{
                        hideKeyboard();
                        Utils.showSnackbar(getActivity(), binding.layoutSignup, Utils.DialogColor.ERROR, getResources().getString(R.string.error_internet_connection));
                    }
                }
            }
        });

        signupViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<Resources>() {
            @Override
            public void onChanged(Resources resources) {
                hideKeyboard();

                if (resources.status == Status.SUCCESS) {
                    PrefHelper.setLoginCredentials(getActivity(), new User(uname, pass));
                    showMessages(Utils.DialogColor.SUCCESS, getResources().getString(R.string.success_signup));

                } else {
                    String serviceMessage = resources.getMessage();
                    showMessages(Utils.DialogColor.ERROR, serviceMessage);
                }
            }
        });

        return binding.getRoot();
    }


    private void showMessages(int status, String message) {
        if (binding.editSuEmail.isFocused()) binding.editSuEmail.clearFocus();
        if (binding.editSuPass.isFocused()) binding.editSuPass.clearFocus();
        if (binding.editSuUname.isFocused()) binding.editSuUname.clearFocus();


        Utils.showSnackbar(getActivity(), binding.layoutSignup, status, message);
        int drawable = R.drawable.bg_error_edit_text;
        if (status == Utils.DialogColor.SUCCESS) {
            drawable = R.drawable.bg_success_edit_text;
        }

        binding.editSuEmail.setBackgroundResource(drawable);
        binding.editSuPass.setBackgroundResource(drawable);
        binding.editSuUname.setBackgroundResource(drawable);
    }

    private View.OnFocusChangeListener passfocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (!hasFocus) {
                binding.txtSuInfo.setVisibility(View.VISIBLE);
                passValControl();
                checkValidations();
            } else {
                binding.txtSuInfo.setVisibility(View.GONE);
            }
        }
    };

    private View.OnFocusChangeListener unameFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (!hasFocus) {
                binding.txtSuInfo.setVisibility(View.VISIBLE);
                unameValControl();
                checkValidations();
            } else {
                binding.txtSuInfo.setVisibility(View.GONE);
            }
        }
    };


    private View.OnFocusChangeListener emailFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (!hasFocus) {
                binding.txtSuInfo.setVisibility(View.VISIBLE);
                emailValControl();
                checkValidations();
            } else {
                binding.txtSuInfo.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onNavigationIconClick() {
        super.onNavigationIconClick();
        getActivity().onBackPressed();
    }

    private void passValControl() {
        if (Utils.checkPasswordPattern(binding.editSuPass.getText().toString())) {
            pass_val_status = true;
            binding.editSuPass.setBackgroundResource(R.drawable.bg_success_edit_text);
        } else {
            pass_val_status = false;
            binding.editSuPass.setBackgroundResource(R.drawable.bg_error_edit_text);
        }
    }

    private void unameValControl() {
        if (Utils.checkUserNamePattern(binding.editSuUname.getText().toString())) {
            uname_val_status = true;
            binding.editSuUname.setBackgroundResource(R.drawable.bg_success_edit_text);
        } else {
            uname_val_status = false;
            binding.editSuUname.setBackgroundResource(R.drawable.bg_error_edit_text);
        }
    }

    private  void emailValControl(){
        if (Utils.checkEmailPattern(binding.editSuEmail.getText().toString())) {
            email_val_status = true;
            binding.editSuEmail.setBackgroundResource(R.drawable.bg_success_edit_text);
        } else {
            email_val_status = false;
            binding.editSuEmail.setBackgroundResource(R.drawable.bg_error_edit_text);
        }
    }

    private boolean checkValidations() {
        if (email_val_status && uname_val_status && pass_val_status) {
            binding.btnSuSignup.setEnabled(true);
            return true;
        } else {
            if (binding.btnSuSignup.isEnabled()) {
                binding.btnSuSignup.setEnabled(false);
            }
            return false;
        }
    }

}
