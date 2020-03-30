package com.cybercrypt.sandvmi.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.adapter.CustomDrawerAdapter;
import com.cybercrypt.sandvmi.model.DrawerItem;
import com.cybercrypt.sandvmi.ui.homescreen.AboutFragment;
import com.cybercrypt.sandvmi.ui.homescreen.ForgotPasswordFragment;
import com.cybercrypt.sandvmi.ui.homescreen.HomeFragment;
import com.cybercrypt.sandvmi.ui.homescreen.LoginFragment;
import com.cybercrypt.sandvmi.ui.homescreen.ProfileFragment;
import com.cybercrypt.sandvmi.ui.homescreen.SettingsFragment;
import com.cybercrypt.sandvmi.ui.homescreen.SignupFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,SettingsFragment.OnProfileSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerListView;
    private CustomDrawerAdapter cAdapter;
    private ArrayList<DrawerItem> dataList;
    private TextView toolbar_text;
    private Toolbar toolbar;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    public static final String HOMETAG="HOME";
    public static final String ABOUTTAG="ABOUT";
    public static final String SETTINGTAG="SETTING";
    public static final String PROFILETAG="PROFILE";
    public static final String SIGNUPTAG="SIGNUP";
    public static final String LOGINTAG = "LOGIN";
    public static final String FORGOTPASSWORDTAG = "FORGOTPASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_white));
        toolbar_text=toolbar.findViewById(R.id.toolbar_title);
        toolbar.bringToFront();

        drawer = findViewById(R.id.drawer_layout);

         mDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem(getString(R.string.menu_home), R.drawable.ic_home_gray,HOMETAG));
        //dataList.add(new DrawerItem(getString(R.string.menu_profile), R.drawable.ic_profile_gray,PROFILETAG));
        dataList.add(new DrawerItem(getString(R.string.menu_setting), R.drawable.ic_settings_gray,SETTINGTAG));
        dataList.add(new DrawerItem(getString(R.string.menu_about), R.drawable.ic_info_outline_gray,ABOUTTAG));

        mDrawerListView = findViewById(R.id.mdrawerList);
        cAdapter=new CustomDrawerAdapter(
                HomeActivity.this,
                R.layout.custom_drawer_item,
                dataList);
        mDrawerListView.setAdapter(cAdapter);

        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            // Remove hamburger
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));


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
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            // Remove back button
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_white));
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() ==1 ) {
                fragmentManager.popBackStack();
                toolbar_text.setText(dataList.get(0).getItemName());
                enableBackButton(false);
                cAdapter.selectItem(0);
            } else {
                super.onBackPressed();
                String fragTag=fragmentManager.findFragmentById(R.id.nav_host_fragment).getTag().toString();
                if (fragTag.equals(LOGINTAG)){
                    toolbar_text.setText(getResources().getText(R.string.toolbar_signin));
                }
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private void InitHomeFragment() {
        toolbar_text.setText(dataList.get(0).getItemName());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment.newInstance(),HOMETAG).commit();
    }

    private void showLoginFragment(){
        toolbar_text.setText(getResources().getText(R.string.toolbar_signin));
        enableBackButton(true);
        changeFragment(LoginFragment.newInstance(),LOGINTAG,false);
    }

    private void navController(int pos) {
        Fragment fragment = null;
        Class fragmentClass = null;
        toolbar_text.setText(dataList.get(pos).getItemName());

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
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment, layoutTag).addToBackStack(null).commit();
        }
        if (fromDrawer) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
    }


    public void SignupClick(View view){
        toolbar_text.setText(getResources().getText(R.string.toolbar_signup));
        enableBackButton(true);
        changeFragment(SignupFragment.newInstance(),SIGNUPTAG,false);
    }

    public void LoginClick(View view){
        showLoginFragment();
    }

    public void ForgotPasswordClick(View view){
        toolbar_text.setText(getResources().getText(R.string.toolbar_forgotpassword));
        changeFragment(ForgotPasswordFragment.newInstance(),FORGOTPASSWORDTAG,false);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof SettingsFragment) {
            SettingsFragment settingsFragment = (SettingsFragment) fragment;
            settingsFragment.setOnProfileSelectedListener(this);
        }
    }

    @Override
    public void onProfileSelected() {
        changeFragment(ProfileFragment.newInstance(),PROFILETAG,false);
    }
}
