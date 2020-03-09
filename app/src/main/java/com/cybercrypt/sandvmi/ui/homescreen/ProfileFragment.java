package com.cybercrypt.sandvmi.ui.homescreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;

public class ProfileFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        return root;
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
}