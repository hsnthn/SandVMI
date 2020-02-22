package com.cybercrypt.sandvmi.ui.util;

import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;

public class BaseFragment extends Fragment {


    public void changeFragment(Fragment fra){
        getActivity().
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place,fra)
                .commit();
    }

}
