package com.cybercrypt.sandvmi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.SandVMIApp;
import com.cybercrypt.sandvmi.data.local.model.DrawerItem;
import com.cybercrypt.sandvmi.data.remote.model.User;
import com.cybercrypt.sandvmi.databinding.ActivityMainBinding;
import com.cybercrypt.sandvmi.ui.adapter.CustomDrawerAdapter;
import com.cybercrypt.sandvmi.ui.homescreen.AboutFragment;
import com.cybercrypt.sandvmi.ui.homescreen.HomeFragment;
import com.cybercrypt.sandvmi.ui.homescreen.SettingsFragment;
import com.cybercrypt.sandvmi.ui.util.BaseActivity;
import com.cybercrypt.sandvmi.ui.util.FragmentUtils;
import com.cybercrypt.sandvmi.ui.util.ILockedActivity;
import com.cybercrypt.sandvmi.ui.util.Utils;
import com.cybercrypt.sandvmi.util.PrefHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.cybercrypt.sandvmi.ui.util.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, ILockedActivity {

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle mDrawerToggle;
    private CustomDrawerAdapter cAdapter;
    private ArrayList<DrawerItem> dataList;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    public static final String HOMETAG = "HOME";
    public static final String ABOUTTAG = "ABOUT";
    public static final String SETTINGTAG = "SETTING";
    public static final String PROFILETAG = "PROFILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((SandVMIApp) this.getApplication()).resumeActivity = this;
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bindToolbar(binding.appBar.toolbar);
        binding.appBar.toolbar.setIcon(R.drawable.ic_menu_white);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBar.toolbar.toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem(getString(R.string.menu_home), R.drawable.ic_home_white, HOMETAG));
        //dataList.add(new DrawerItem(getString(R.string.menu_profile), R.drawable.ic_profile_gray,PROFILETAG));
        dataList.add(new DrawerItem(getString(R.string.menu_setting), R.drawable.ic_settings_gray, SETTINGTAG));
        dataList.add(new DrawerItem(getString(R.string.menu_about), R.drawable.ic_info_outline_gray, ABOUTTAG));

        cAdapter = new CustomDrawerAdapter(
                MainActivity.this,
                R.layout.custom_drawer_item,
                dataList);
        binding.mdrawerList.setAdapter(cAdapter);

        binding.mdrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                navController(position);
                cAdapter.selectItem(position);
            }
        });

        if (PrefHelper.getLoggedStatus(getApplicationContext())) {

            binding.userInfo.setPlanname("yearly plan");
            User u = PrefHelper.getSharedUser(getApplicationContext());
            binding.userInfo.setUsername(u.username);

        }

        InitHomeFragment();

    }

    public void drawerOpen() {
        binding.drawerLayout.openDrawer(GravityCompat.START);
    }

    private void enableBackButton(boolean enable) {

        if (enable) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);


            if (!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            //You must regain the power of swipe for the drawer.
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }

    }

    @Override
    public void onBackPressed() {
        hideKeyboard();
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() == 1) {
                fragmentManager.popBackStack();
                binding.appBar.toolbar.setToolbartitle(dataList.get(0).getItemName());
                enableBackButton(false);
                cAdapter.selectItem(0);
            } else {
                ((SandVMIApp) this.getApplication()).resumeActivity = null;
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private void InitHomeFragment() {
        FragmentUtils.replaceFragment(this, HomeFragment.newInstance(), R.id.nav_host_fragment, false, TRANSITION_NONE);
    }


    private void navController(int pos) {
        Fragment fragment = null;
        Class fragmentClass = null;

        switch (dataList.get(pos).getLayout_tag()) {
            case HOMETAG:
                fragmentClass = HomeFragment.class;
                break;
            case SETTINGTAG:
                fragmentClass = SettingsFragment.class;
                enableBackButton(true);
                break;
            case ABOUTTAG:
                fragmentClass = AboutFragment.class;
                enableBackButton(true);
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.changeFragment(fragment, dataList.get(pos).getLayout_tag(), true);
    }

    private void changeFragment(Fragment fragment, String layoutTag, boolean fromDrawer) {
        if (!layoutTag.equals(HOMETAG)) {
            FragmentUtils.replaceFragment(this, fragment, R.id.nav_host_fragment, true, layoutTag, TRANSITION_NONE);
        }
        if (fromDrawer) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void SignoutClick(View view) {
        ((SandVMIApp) this.getApplication()).resumeActivity=null;
        startActivity(new Intent(MainActivity.this, AuthenticationActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideNavigations(this);
    }

}
