package com.cybercrypt.sandvmi.ui.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.adapter.CustomSettingsListAdapter;
import com.cybercrypt.sandvmi.model.SettingsItem;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    OnProfileSelectedListener callback;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        ListView mSettingListView = root.findViewById(R.id.list_settings);

        final ArrayList<SettingsItem> settingsMenuList=prepareSettingsMenu();
        final CustomSettingsListAdapter cAdapter=new CustomSettingsListAdapter(
                getActivity(),
                R.layout.custom_settings_list_item,
                settingsMenuList);
        mSettingListView.setAdapter(cAdapter);

        mSettingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (settingsMenuList.get(position).getSettingItem().equals("Profile")){
                    callback.onProfileSelected();
                }
            }
        });

        return root;
    }

    private ArrayList<SettingsItem> prepareSettingsMenu(){
        ArrayList<SettingsItem> items= new ArrayList<SettingsItem>();
        items.add(new SettingsItem("Profile", "Contact details, preferences, etc.",R.drawable.ic_profile_gray));
        items.add(new SettingsItem("Subscriptions & Payments","Manage subscription, payment method, etc.", R.drawable.ic_credit_card_gray));
        items.add(new SettingsItem("Security","Password, wipe settings, etc.", R.drawable.ic_lock_outline_gray));
        items.add(new SettingsItem("Privacy & Permissions","Tor connections, permissions, etc.", R.drawable.ic_verified_user_gray));
        items.add(new SettingsItem("Premade Themes","Choose preinstalled apps", R.drawable.ic_perm_media_gray));
        return items;
    }

    public interface OnProfileSelectedListener {
        public void onProfileSelected();
    }

    public void setOnProfileSelectedListener(OnProfileSelectedListener callback) {
        this.callback = callback;
    }


}