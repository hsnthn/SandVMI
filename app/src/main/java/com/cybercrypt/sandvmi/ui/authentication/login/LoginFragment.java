package com.cybercrypt.sandvmi.ui.authentication.login;

import android.content.Intent;
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
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.api.Resources;
import com.cybercrypt.sandvmi.api.Status;
import com.cybercrypt.sandvmi.data.remote.model.User;
import com.cybercrypt.sandvmi.databinding.FragmentLoginBinding;
import com.cybercrypt.sandvmi.ui.AuthenticationActivity;
import com.cybercrypt.sandvmi.ui.authentication.PinLockFragment;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;
import com.cybercrypt.sandvmi.ui.util.Utils;
import com.cybercrypt.sandvmi.util.PrefHelper;
import com.sierra.swclient.ConnectionActivity;
import com.sierra.swclient.Constants;

import static com.cybercrypt.sandvmi.ui.AuthenticationActivity.LOGINTAG;

public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    private String uname, pass;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        setToolbarTitle(getResources().getString(R.string.toolbar_signin));
        setToolbarIcon(BaseFragment.NONE);


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
        txtboxsEmptyCheck();

        binding.btnLoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname = binding.editLoUname.getText().toString();
                pass = binding.editLoPass.getText().toString();

                Intent intent = new Intent(requireActivity(), ConnectionActivity.class);
                intent.putExtra(Constants.HOSTNAME, "88.119.23.19");
                intent.putExtra(Constants.PORTNO, 443);
                intent.putExtra(Constants.USERNAME, uname);
                intent.putExtra(Constants.PASSWORD, pass);
                intent.putExtra(Constants.GROUP, "local");
                intent.putExtra(Constants.KEY_SYN_WALLPAPER, false);
                intent.putExtra(Constants.SOFT_HOME, true);
                intent.putExtra(Constants.ENCODE_TYPE, 6);
                intent.putExtra(Constants.KEY_CODEC_TYPE, 2);
                intent.putExtra(Constants.KEY_DTLS_ENABLE, false);
                intent.putExtra(Constants.KEY_DTLS_TOUCH_ENABLE, false);
                intent.putExtra(Constants.UDP_HOLE_PUNCH, false);
                intent.putExtra(Constants.KEY_SSL_ENABLE, true);
                intent.putExtra(Constants.KEY_SSLPINNING_ENABLE, false);
                intent.putExtra(Constants.SCALE_TYPE, 0);
                intent.putExtra(Constants.FORCE_BANDWIDTH, 0);
                intent.putExtra(Constants.BIT_RATE, 2);
                intent.putExtra(Constants.NOTIFICATION, true);
                intent.putExtra(Constants.KEY_CAM, true);
                intent.putExtra(Constants.CAM_BIT_RATE, 1.0);
                intent.putExtra(Constants.KEY_CAM_ENCODE_TYPE, 1);
                intent.putExtra(Constants.KEY_CAM_CODEC_TYPE, 1);
                startActivity(intent);

//                if (Utils.isNetworkConnected(getContext()))
//                    loginViewModel.getUser(uname, pass);
//                else {
//                    hideKeyboard();
//                    Utils.showSnackbar(getActivity(), binding.layLogin, Utils.DialogColor.ERROR, getResources().getString(R.string.error_internet_connection));
//                }
            }
        });

        loginViewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<Resources>() {
            @Override
            public void onChanged(Resources resources) {
                hideKeyboard();

                if (resources.status == Status.SUCCESS) {
                    PrefHelper.setLoggedIn(getContext(), true);
                    PrefHelper.setSharedUser(getContext(), (User) resources.data);
                    PrefHelper.setLoginCredentials(getActivity(), new User(uname, pass));

                    if (!PrefHelper.getPinAuth(getContext()))
                        showPinFragmentForPinSet();
                    else {
                        changeFragment(PinLockFragment.newInstance(PinLockFragment.PinStatus.MODE_LOGIN), LOGINTAG);
                    }

                } else {
                    String message = getResources().getString(R.string.error_login);
                    String serviceMessage = resources.getMessage();
                    if (!serviceMessage.equals(""))
                        message = serviceMessage;
                    showMessages(Utils.DialogColor.ERROR, message);
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        User u = PrefHelper.getLoginCredentials(getContext());
        if (u != null) {
            binding.editLoUname.setText(u.username);
            binding.editLoPass.setText(u.password);
        }
    }

    private void showPinFragmentForPinSet() {
        clearBackStack();
        changeFragment(PinLockFragment.newInstance(PinLockFragment.PinStatus.MODE_CREATE), AuthenticationActivity.PINLOCKTAG_CREATE);
    }

    private void showMessages(int status, String message) {
        if (binding.editLoUname.isFocused()) binding.editLoUname.clearFocus();
        if (binding.editLoPass.isFocused()) binding.editLoPass.clearFocus();


        Utils.showSnackbar(getActivity(), binding.layLogin, status, message);
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

    private TextWatcher textEmptyCheck = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            txtboxsEmptyCheck();
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

    private void txtboxsEmptyCheck() {
        binding.btnLoLogin.setEnabled(binding.editLoUname.getText().toString().length() > 0 && binding.editLoPass.getText().toString().length() > 0);
    }


}