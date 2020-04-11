package com.cybercrypt.sandvmi.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.adapter.CustomDrawerAdapter;
import com.cybercrypt.sandvmi.databinding.ActivityMainBinding;
import com.cybercrypt.sandvmi.model.DrawerItem;
import com.cybercrypt.sandvmi.ui.homescreen.AboutFragment;
import com.cybercrypt.sandvmi.ui.homescreen.ForgotPasswordFragment;
import com.cybercrypt.sandvmi.ui.homescreen.HomeFragment;
import com.cybercrypt.sandvmi.ui.homescreen.LoginFragment;
import com.cybercrypt.sandvmi.ui.homescreen.SettingsFragment;
import com.cybercrypt.sandvmi.ui.homescreen.SignupFragment;
import com.cybercrypt.sandvmi.ui.util.FragmentUtils;
import com.cybercrypt.sandvmi.ui.util.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.cybercrypt.sandvmi.ui.util.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle mDrawerToggle;
    private CustomDrawerAdapter cAdapter;
    private ArrayList<DrawerItem> dataList;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    public static final String HOMETAG= "HOME";
    public static final String ABOUTTAG="ABOUT";
    public static final String SETTINGTAG="SETTING";
    public static final String PROFILETAG="PROFILE";
    public static final String SIGNUPTAG="SIGNUP";
    public static final String LOGINTAG = "LOGIN";
    public static final String FORGOTPASSWORDTAG = "FORGOTPASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.appBar.toolbar.setNavicon(R.drawable.ic_menu_white);
        binding.appBar.toolbar.setToolbartitle(getResources().getString(R.string.subscription_choose_payment_plan_title));
        binding.appBar.toolbar.toolbarHome.bringToFront();


        mDrawerToggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBar.toolbar.toolbarHome, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem(getString(R.string.menu_home), R.drawable.ic_home_white,HOMETAG));
        //dataList.add(new DrawerItem(getString(R.string.menu_profile), R.drawable.ic_profile_gray,PROFILETAG));
        dataList.add(new DrawerItem(getString(R.string.menu_setting), R.drawable.ic_settings_gray,SETTINGTAG));
        dataList.add(new DrawerItem(getString(R.string.menu_about), R.drawable.ic_info_outline_gray,ABOUTTAG));

        cAdapter=new CustomDrawerAdapter(
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

        InitHomeFragment();

        Intent intent = getIntent();
        if (intent.hasExtra("fragment")) {
            String fragName= intent.getStringExtra("fragment");
            if (fragName.equals(LOGINTAG)){
                showLoginFragment();
            }
        }
    }

    private void enableBackButton(boolean enable) {

        if(enable) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            // Remove hamburger
            binding.appBar.toolbar.setNavicon(R.drawable.ic_arrow_back_white);


            if(!mToolBarNavigationListenerIsRegistered) {
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

            // Remove back button
            binding.appBar.toolbar.setNavicon(R.drawable.ic_menu_white);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }


    }

    private void hideKeyboard() {
        try {
            InputMethodManager inputmanager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputmanager != null) {
                inputmanager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception var2) {
        }

    }

    @Override
    public void onBackPressed() {
        hideKeyboard();
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() ==1 ) {
                fragmentManager.popBackStack();
                binding.appBar.toolbar.setToolbartitle(dataList.get(0).getItemName());
                enableBackButton(false);
                cAdapter.selectItem(0);
            } else {
                super.onBackPressed();
                String fragTag=fragmentManager.findFragmentById(R.id.nav_host_fragment).getTag();
                if (fragTag.equals(LOGINTAG)){
                    binding.appBar.toolbar.setToolbartitle(getResources().getString(R.string.toolbar_signin));
                }
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private void InitHomeFragment() {
        binding.appBar.toolbar.setToolbartitle(dataList.get(0).getItemName());
        FragmentUtils.replaceFragment(this, HomeFragment.newInstance(), R.id.nav_host_fragment, false, TRANSITION_NONE);
    }

    private void showLoginFragment(){
        binding.appBar.toolbar.setToolbartitle(getResources().getString(R.string.toolbar_signin));
        enableBackButton(true);
        changeFragment(LoginFragment.newInstance(),LOGINTAG,false);
    }

    private void navController(int pos) {
        Fragment fragment = null;
        Class fragmentClass = null;
        binding.appBar.toolbar.setToolbartitle(dataList.get(pos).getItemName());

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

        changeFragment(fragment,dataList.get(pos).getLayout_tag(),true);
    }

    private void changeFragment(Fragment fragment, String layoutTag, boolean fromDrawer){
        if (!layoutTag.equals(HOMETAG)) {
            FragmentUtils.replaceFragment(this, fragment, R.id.nav_host_fragment, true,layoutTag, TRANSITION_NONE);
        }
        if (fromDrawer) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    public void SignupClick(View view){
        binding.appBar.toolbar.setToolbartitle(getResources().getString(R.string.toolbar_signup));
        enableBackButton(true);
        changeFragment(SignupFragment.newInstance(),SIGNUPTAG,false);
    }

    public void LoginClick(View view){
        showLoginFragment();
    }

    public void ForgotPasswordClick(View view){
        binding.appBar.toolbar.setToolbartitle(getResources().getString(R.string.toolbar_forgotpassword));
        changeFragment(ForgotPasswordFragment.newInstance(),FORGOTPASSWORDTAG,false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideNavigations(this);
    }

}
