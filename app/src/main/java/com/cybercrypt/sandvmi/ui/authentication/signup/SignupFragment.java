package com.cybercrypt.sandvmi.ui.authentication.signup;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.api.Resources;
import com.cybercrypt.sandvmi.api.Status;
import com.cybercrypt.sandvmi.data.remote.model.User;
import com.cybercrypt.sandvmi.databinding.FragmentSignupBinding;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;
import com.cybercrypt.sandvmi.ui.util.Utils;
import com.cybercrypt.sandvmi.ui.widget.SandTextInputLayout;
import com.cybercrypt.sandvmi.util.PrefHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
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


        binding.btnSuSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.editSuPass.getWindowToken(), 0);
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
                    } else {
                        Utils.showSnackbar(getActivity(), binding.layoutSignup, Utils.DialogColor.ERROR, getResources().getString(R.string.error_internet_connection));
                    }
                }
            }
        });

        signupViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<Resources>() {
            @Override
            public void onChanged(Resources resources) {
              /*  if(getViewLifecycleOwner().getLifecycle().getCurrentState()== Lifecycle.State.){
                    // your code here ...
                }*/
                if (resources.status == Status.SUCCESS) {
                    PrefHelper.setLoginCredentials(getActivity(), new User(uname, pass));
                    showMessages(Utils.DialogColor.SUCCESS, getResources().getString(R.string.success_signup));
                    getActivity().onBackPressed();
                } else {
                    String serviceMessage = resources.getMessage();
                    showMessages(Utils.DialogColor.ERROR, serviceMessage);
                }
            }
        });


        binding.editSuEmail.setOnFocusChangeListener(emailFocusChangeListener);
        binding.editSuPass.setOnFocusChangeListener(passfocusChangeListener);
        binding.editSuUname.setOnFocusChangeListener(unameFocusChangeListener);

        binding.editSuEmail.addTextChangedListener(emailWatcher);
        binding.editSuUname.addTextChangedListener(unameWatcher);
        binding.editSuPass.addTextChangedListener(passWatcher);

        return binding.getRoot();
    }

    @Override
    public void onNavigationIconClick() {
        super.onNavigationIconClick();
        getActivity().onBackPressed();
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

    private TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            emailValControl();
            checkValidations();
        }
    };

    private TextWatcher unameWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            unameValControl();
            checkValidations();
        }
    };

    private TextWatcher passWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            passValControl();
            checkValidations();
        }
    };


    private void passValControl() {
        pass = binding.editSuPass.getText().toString();
        pass_val_status = true;
        List<String> errorList = new ArrayList<>();
        if (TextUtils.isEmpty(pass)) {
            pass_val_status = false;
            errorList.add(getResources().getString(R.string.error_fill));
        } else {
            if (pass.length() < 8 || pass.length() > 15) {
                pass_val_status = false;
                errorList.add(getResources().getString(R.string.error_pass_char_count));
            }


            //errorList.add(getResources().getString(R.string.error_valid_pass));

            //Pattern special = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Pattern allowedSpecial = Pattern.compile("[" + Utils.ALLOWED_SPECIAL_CHARACTERS + "]");
            Pattern number = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
            Pattern upper = Pattern.compile("[A-Z]");
            Pattern lower = Pattern.compile("[a-z]");
            Pattern whiteList = Pattern.compile("[a-zA-Z0-9_!@#$%^&+=,.]*$"); //

            //Matcher matcherSymbols = special.matcher(pass);
            Matcher matcherAllowedSymbols = allowedSpecial.matcher(pass);
            Matcher matcherNumber = number.matcher(pass);
            Matcher matcherUpperChar = upper.matcher(pass);
            Matcher matcherLowerChar = lower.matcher(pass);
            Matcher matcherWhiteList = whiteList.matcher(pass);

            //boolean constainsSymbols = matcherSymbols.find();
            boolean containsNumber = matcherNumber.find();
            boolean containsUpperChar = matcherUpperChar.find();
            boolean containsLowerChar = matcherLowerChar.find();
            boolean containsAllowedSymbols = matcherAllowedSymbols.find();
            boolean containsWhiteList = matcherWhiteList.find();

            if (!containsUpperChar) {
                pass_val_status = false;
                errorList.add(getResources().getString(R.string.error_pass_upper_char));
            }

            if (!containsLowerChar) {
                pass_val_status = false;
                errorList.add(getResources().getString(R.string.error_pass_lower_char));
            }

            if (!containsNumber) {
                pass_val_status = false;
                errorList.add(getResources().getString(R.string.error_pass_number));
            }

            if (!containsAllowedSymbols) {
                pass_val_status = false;
                errorList.add(getResources().getString(R.string.error_pass_symbols) );
            } else if (!matcherWhiteList.matches()) {
                pass_val_status = false;
                errorList.add(getResources().getString(R.string.error_pass_not_allowed)+ "\nAllowed symbols: \n" + Utils.ALLOWED_SPECIAL_CHARACTERS);

            }

        }

        if (pass_val_status) {
            binding.textinputPassword.setError(null);
            binding.textinputPassword.setStatus(SandTextInputLayout.SUCCESS);
        } else {
            StringBuilder errorText = new StringBuilder();
            for (String error : errorList) {
                if (errorText.length() == 0) {
                    errorText.append(error);
                } else {
                    errorText.append("\n").append(error);
                }

            }
            binding.textinputPassword.setError(errorText);
            binding.textinputPassword.setStatus(SandTextInputLayout.ERROR);
        }

    }

    private void unameValControl() {
        uname = binding.editSuUname.getText().toString();
        uname_val_status = true;
        List<String> errorList = new ArrayList<>();

        if (TextUtils.isEmpty(uname)) {
            uname_val_status = false;
            errorList.add(getResources().getString(R.string.error_fill));
        } else {

            if (uname.length() < 4 || uname.length() > 10) {
                uname_val_status = false;
                errorList.add(getResources().getString(R.string.error_uname_char_count));
            }

            if (!Utils.checkUserNamePattern(uname)) {
                uname_val_status = false;
                errorList.add(getResources().getString(R.string.error_valid_uname));
            }
        }

        if (uname_val_status) {
            binding.textinputUname.setError(null);
            binding.textinputUname.setStatus(SandTextInputLayout.SUCCESS);
        } else {
            StringBuilder errorText = new StringBuilder();
            for (String error : errorList) {
                if (errorText.length() == 0) {
                    errorText.append(error);
                } else {
                    errorText.append("\n").append(error);
                }

            }
            binding.textinputUname.setError(errorText);
            binding.textinputUname.setStatus(SandTextInputLayout.ERROR);
        }

    }

    private void emailValControl() {
        email = binding.editSuEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            email_val_status = false;
            binding.textinputEmail.setError(getResources().getString(R.string.error_fill));
            binding.textinputEmail.setStatus(SandTextInputLayout.ERROR);
        } else if (!Utils.checkEmailPattern(email)) {
            email_val_status = false;
            binding.textinputEmail.setError(getResources().getString(R.string.error_valid_email));
            binding.textinputEmail.setStatus(SandTextInputLayout.ERROR);
        } else {
            email_val_status = true;
            binding.textinputEmail.setError(null);
            binding.textinputEmail.setStatus(SandTextInputLayout.SUCCESS);
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
