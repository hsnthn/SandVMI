package com.cybercrypt.sandvmi.ui;

import android.os.Build;
import android.os.Bundle;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.adapter.CustomDrawerAdapter;
import com.cybercrypt.sandvmi.model.DrawerItem;
import com.cybercrypt.sandvmi.ui.homescreen.AboutFragment;
import com.cybercrypt.sandvmi.ui.homescreen.HomeFragment;
import com.cybercrypt.sandvmi.ui.homescreen.ProfileFragment;
import com.cybercrypt.sandvmi.ui.homescreen.SettingsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ListView mDrawerListView;
    private ArrayList<DrawerItem> dataList;
    private TextView toolbar_text;
    private FrameLayout nav_host_fragment;

    private final String HOMETAG="HOME";
    private final String ABOUTTAG="ABOUT";
    private final String SETTINGTAG="SETTING";
    private final String PROFILETAG="PROFILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_white));
        toolbar_text=toolbar.findViewById(R.id.toolbar_title);
        toolbar.bringToFront();

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        dataList = new ArrayList<DrawerItem>();
        dataList.add(new DrawerItem(getString(R.string.menu_home), R.drawable.ic_home_gray,HOMETAG));
        dataList.add(new DrawerItem(getString(R.string.menu_profile), R.drawable.ic_profile_gray,PROFILETAG));
        dataList.add(new DrawerItem(getString(R.string.menu_setting), R.drawable.ic_settings_gray,SETTINGTAG));
        dataList.add(new DrawerItem(getString(R.string.menu_about), R.drawable.ic_info_outline_gray,ABOUTTAG));

        mDrawerListView = findViewById(R.id.mdrawerList);
        final CustomDrawerAdapter cAdapter=new CustomDrawerAdapter(
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


        Initializing();

    }

    private void Initializing() {
        Fragment fragment = null;
        Class fragmentClass = HomeFragment.class;
        toolbar_text.setText(dataList.get(0).getItemName());
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void navController(int pos) {
        Fragment fragment = null;
        Class fragmentClass = null;
        toolbar_text.setText(dataList.get(pos).getItemName());

        switch (dataList.get(pos).getLayout_tag()) {
            case HOMETAG:
                fragmentClass = HomeFragment.class;
                break;
            case PROFILETAG:
                fragmentClass = ProfileFragment.class;
                break;
            case SETTINGTAG:
                fragmentClass = SettingsFragment.class;
                break;
            case ABOUTTAG:
                fragmentClass = AboutFragment.class;
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }// Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


}
