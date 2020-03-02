package com.cybercrypt.sandvmi.ui.util;

import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import android.widget.Toast;

public class Utils {

    public static void Show(Context cont, String durum){
        Toast.makeText(cont, durum, Toast.LENGTH_SHORT).show();
    }


}
