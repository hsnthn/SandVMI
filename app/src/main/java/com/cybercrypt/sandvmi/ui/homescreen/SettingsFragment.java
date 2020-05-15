package com.cybercrypt.sandvmi.ui.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.ui.adapter.CustomSettingsListAdapter;
import com.cybercrypt.sandvmi.databinding.FragmentSettingsBinding;
import com.cybercrypt.sandvmi.data.local.model.SettingsItem;
import com.cybercrypt.sandvmi.ui.MainActivity;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;
import com.cybercrypt.sandvmi.ui.util.FragmentUtils;

import java.util.ArrayList;

public class SettingsFragment extends BaseFragment {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        FragmentSettingsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        setToolbarTitle(getResources().getString(R.string.menu_setting));
        setToolbarIcon(BaseFragment.BACK_ICON);

        final ArrayList<SettingsItem> settingsMenuList=prepareSettingsMenu();
        final CustomSettingsListAdapter cAdapter=new CustomSettingsListAdapter(
                getActivity(),
                R.layout.custom_settings_list_item,
                settingsMenuList);
        binding.listSettings.setAdapter(cAdapter);

        binding.listSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (settingsMenuList.get(position).getSettingItem().equals("Profile")){
                    FragmentUtils.replaceFragment(getActivity(), ProfileFragment.newInstance(), R.id.nav_host_fragment, true, MainActivity.PROFILETAG, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);

                }
            }
        });

        return binding.getRoot();
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

    @Override
    public void onNavigationIconClick() {
        getActivity().onBackPressed();
    }


}