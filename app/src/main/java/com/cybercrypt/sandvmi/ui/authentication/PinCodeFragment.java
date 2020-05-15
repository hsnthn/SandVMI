package com.cybercrypt.sandvmi.ui.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentPinCodeBinding;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class PinCodeFragment extends BaseFragment {

    private FragmentPinCodeBinding binding;

    public static PinCodeFragment newInstance() {
        return new PinCodeFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_pin_code, container, false);
        setToolbarTitle(getResources().getString(R.string.toolbar_forgotpassword));
        setToolbarIcon(BaseFragment.NONE);

        final PinEntryEditText pinEntry = binding.txtPinEntry;
        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().equals("1234")) {

                        pinEntry.setText(null);
                    }
                }
            });
        }


        return binding.getRoot();
    }
}
