package com.cybercrypt.sandvmi.ui.util;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.SubscriptionScreenActivity;

public class BaseFragment extends Fragment implements OnNavigationIconClick {


    protected void setToolbarTitle(String title){
        ((SubscriptionScreenActivity)getActivity()).getSubscriptionToolBar().setToolbartitle(title);
    }

    protected void toolbarNavIcon(boolean icon){
        if (icon)
            ((SubscriptionScreenActivity)getActivity()).getSubscriptionToolBar().setNavicon(R.drawable.ic_nav_back);
        else
            ((SubscriptionScreenActivity)getActivity()).getSubscriptionToolBar().toolbarSubs.setNavigationIcon(null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((SubscriptionScreenActivity)getActivity()).getSubscriptionToolBar().toolbarSubs.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationIconClick();
            }
        });
    }

    public void changeFragment(Fragment fra){
        FragmentUtils.replaceFragment(getActivity(), fra, R.id.fragment_place, true, FragmentUtils.TRANSITION_NONE);
    }

    @Override
    public void onNavigationIconClick() { }
}
