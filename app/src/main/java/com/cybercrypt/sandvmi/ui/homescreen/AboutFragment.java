package com.cybercrypt.sandvmi.ui.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.FragmentAboutBinding;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

public class AboutFragment extends BaseFragment {

    private FragmentAboutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);

        setToolbarTitle(getResources().getString(R.string.menu_about));
        setToolbarIcon(BaseFragment.BACK_ICON);


        return binding.getRoot();
    }

    @Override
    public void onNavigationIconClick() {
        getActivity().onBackPressed();
    }
}