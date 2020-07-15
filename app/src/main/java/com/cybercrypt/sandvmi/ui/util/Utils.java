package com.cybercrypt.sandvmi.ui.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.cybercrypt.sandvmi.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Utils {

    public static final String ALLOWED_SPECIAL_CHARACTERS = "_!@#$%^&+=.,";

    public static void Show(Context cont, String durum) {
        Toast.makeText(cont, durum, Toast.LENGTH_SHORT).show();
    }

    public static boolean checkEmailPattern(String mail) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return mail.trim().matches(emailPattern);
    }

    public static boolean checkUserNamePattern(String uname) {
        String unamePattern = "^[a-z0-9_-]{4,10}$";
        return uname.trim().matches(unamePattern);
    }

    public static boolean checkPasswordPattern(String pass) {
        String passPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*["+ALLOWED_SPECIAL_CHARACTERS+"])(?=\\S+$).{8,}$";
        return pass.trim().matches(passPattern);
    }

    public static void showSnackbar(Context context, View view,int color
                                    ,String dialogText) { // Create the Snackbar

        final Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
        snackbar.setDuration(3000);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setPadding(0, 0, 0, 0);
        // Inflate your custom view with an Edit Text
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View snackView = inflater.inflate(R.layout.dialog_error, null);
        RelativeLayout lay = (RelativeLayout) snackView.findViewById(R.id.layout_dialog);
        if (color==0) {
            lay.setBackgroundColor(ContextCompat.getColor(context,R.color.bg_dialog_error));
        }else{
            lay.setBackgroundColor(ContextCompat.getColor(context,R.color.bg_dialog_success));
        }
        // button id for snackbar
        TextView txt_error = (TextView) snackView.findViewById(R.id.txt_dialog);
        txt_error.setText(dialogText);
        ImageButton button_mapel = (ImageButton) snackView.findViewById(R.id.imgbtn_dialog_close);

        // perform button click listener
        button_mapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        layout.addView(snackView, 0);
        ViewCompat.setFitsSystemWindows(layout, false);
        ViewCompat.setOnApplyWindowInsetsListener(layout, null);
        snackbar.show();

    }


    public static void hideNavigations(Activity context) {
        View decorView = context.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

       // View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }


    public static void hideTopNavigations(Activity context) {
        View decorView = context.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
    }

    public static class DialogColor{
        public final static int ERROR=0;
        public final static int SUCCESS=1;
    }

    public static boolean isNetworkConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
            } else {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnected();
            }
        }

        return false;
    }


}
