package com.cybercrypt.sandvmi.ui.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.adapter.CustomProfileListAdapter;
import com.cybercrypt.sandvmi.databinding.FragmentProfileBinding;
import com.cybercrypt.sandvmi.model.ProfileItem;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);


        final CustomProfileListAdapter cAdapter=new CustomProfileListAdapter(
                getActivity(),
                R.layout.custom_profile_list_item,
                prepareProfileMenu());
        binding.listProfile.setAdapter(cAdapter);

        binding.listProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

            }
        });

        return binding.getRoot();
    }

    private ArrayList<ProfileItem> prepareProfileMenu(){
        ArrayList<ProfileItem> items= new ArrayList<ProfileItem>();
        items.add(new ProfileItem(getActivity().getResources().getString(R.string.profile_payment_text), R.drawable.ic_credit_card_gray));
        items.add(new ProfileItem(getActivity().getResources().getString(R.string.profile_contact_text), R.drawable.ic_mail_gray));

        return items;
    }





}