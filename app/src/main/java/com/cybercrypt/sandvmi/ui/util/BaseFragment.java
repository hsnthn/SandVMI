package com.cybercrypt.sandvmi.ui.util;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;

public class BaseFragment extends Fragment implements OnNavigationIconClick {

    public final static int BACK_ICON = R.drawable.ic_nav_back;
    public final static int MENU_ICON = R.drawable.ic_menu_white;
    public final static int NONE = android.R.color.transparent;

    protected void setToolbarTitle(String title){
        ((BaseActivity)getActivity()).setTitle(title);
    }

    protected void setToolbarIcon(int icon){

            ((BaseActivity)getActivity()).setNavIcon(icon);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            ((BaseActivity) getActivity()).mToolbar.toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationIconClick();
                }
            });
    }


    protected void changeFragment(Fragment fragment, String layoutTag){
        FragmentUtils.replaceFragment(getActivity(), fragment, R.id.nav_host_fragment, true,layoutTag, FragmentUtils.TRANSITION_NONE);
    }

    protected void clearBackStack(){
        FragmentUtils.clearFragmentBackStack(getActivity());
    }

    @Override
    public void onNavigationIconClick() { }

    protected void hideKeyboard(){
        ((BaseActivity) getActivity()).hideKeyboard();
    }

    protected void showKeyboard(EditText view){
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity(). getSystemService(getActivity().INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

}
