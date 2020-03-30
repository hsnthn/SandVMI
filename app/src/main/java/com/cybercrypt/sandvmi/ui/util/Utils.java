package com.cybercrypt.sandvmi.ui.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.cybercrypt.sandvmi.R;
import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public static void Show(Context cont, String durum){
        Toast.makeText(cont, durum, Toast.LENGTH_SHORT).show();
    }
    public static boolean checkEmailPattern(String mail){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return  mail.trim().matches(emailPattern);
    }

    public static boolean checkUserNamePattern(String uname){
        String unamePattern = "^[a-z0-9_-]{3,15}$";
        return  uname.trim().matches(unamePattern);
    }

    public static boolean checkPasswordPattern(String pass){
        String passPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return  pass.trim().matches(passPattern);
    }



    public static void showSnackbar(Context context,View view, String errorText) { // Create the Snackbar

        final Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setPadding(0, 0, 0, 0);
        // Inflate your custom view with an Edit Text
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View snackView = inflater.inflate(R.layout.dialog_error, null);
        // custom_snac_layout is your custom xml

        // button id for snackbar
        TextView txt_error = (TextView) snackView.findViewById(R.id.txt_error);
        txt_error.setText(errorText);
        ImageButton button_mapel = (ImageButton) snackView.findViewById(R.id.imgbtn_error_close);

        // perform button click listener
        button_mapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        layout.addView(snackView, 0);
        snackbar.show();

    }


}
