package com.cybercrypt.sandvmi.ui.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.adapter.CustomProfileListAdapter;
import com.cybercrypt.sandvmi.model.ProfileItem;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        ListView mSettingListView = root.findViewById(R.id.list_profile);

        final CustomProfileListAdapter cAdapter=new CustomProfileListAdapter(
                getActivity(),
                R.layout.custom_profile_list_item,
                prepareProfileMenu());
        mSettingListView.setAdapter(cAdapter);

        mSettingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

            }
        });

        return root;
    }

    private ArrayList<ProfileItem> prepareProfileMenu(){
        ArrayList<ProfileItem> items= new ArrayList<ProfileItem>();
        items.add(new ProfileItem(getActivity().getResources().getString(R.string.profile_payment_text), R.drawable.ic_credit_card_gray));
        items.add(new ProfileItem(getActivity().getResources().getString(R.string.profile_contact_text), R.drawable.ic_mail_gray));

        return items;
    }





}