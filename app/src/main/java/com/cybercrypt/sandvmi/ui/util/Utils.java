package com.cybercrypt.sandvmi.ui.util;

import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import android.widget.Toast;

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



}
