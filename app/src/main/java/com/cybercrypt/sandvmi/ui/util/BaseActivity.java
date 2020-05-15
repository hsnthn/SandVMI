package com.cybercrypt.sandvmi.ui.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.databinding.CustomToolbarBinding;

import static com.cybercrypt.sandvmi.ui.util.FragmentUtils.TRANSITION_NONE;

public abstract class BaseActivity extends AppCompatActivity {

    public CustomToolbarBinding mToolbar;

    protected void bindToolbar(CustomToolbarBinding toolbarTitle) {
        mToolbar = toolbarTitle;
        mToolbar.toolbarMain.bringToFront();
    }

    public void setTitle(String title) {
        mToolbar.setToolbartitle(title);
    }

    public void setNavIcon(int icon){
        if (icon!=0)
            mToolbar.setIcon(icon);
        else
            mToolbar.toolbarMain.setNavigationIcon(null);
    }

    protected void changeFragment(Fragment fragment, String layoutTag){
        FragmentUtils.replaceFragment(this, fragment, R.id.nav_host_fragment, true,layoutTag, TRANSITION_NONE);
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputmanager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputmanager != null) {
                inputmanager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception var2) {
        }
    }
}
