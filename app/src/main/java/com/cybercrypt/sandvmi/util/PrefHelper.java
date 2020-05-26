package com.cybercrypt.sandvmi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.cybercrypt.sandvmi.BuildConfig;
import com.cybercrypt.sandvmi.data.remote.model.User;
import com.google.gson.Gson;

import static com.cybercrypt.sandvmi.util.PrefContants.APP_FIRST_TIME;
import static com.cybercrypt.sandvmi.util.PrefContants.LOGGED_IN_PREF;
import static com.cybercrypt.sandvmi.util.PrefContants.LOGGED_IN_USER_PREF;
import static com.cybercrypt.sandvmi.util.PrefContants.LOGIN_CREDENTIALS;
import static com.cybercrypt.sandvmi.util.PrefContants.PIN_AUTH_PREF;
import static com.cybercrypt.sandvmi.util.PrefContants.PIN_CODE;
import static com.cybercrypt.sandvmi.util.PrefContants.TEMPORARY_PIN_CODE;

public class PrefHelper {
    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }


    public static void setSharedUser(Context context,
                                       Object value) {

        SharedPreferences.Editor prefsEditor = getPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        prefsEditor.putString(LOGGED_IN_USER_PREF, json);
        prefsEditor.apply();
    }

    public static User getSharedUser(Context context) {

        Gson gson = new Gson();
        String json = getPreferences(context).getString(LOGGED_IN_USER_PREF, "");
        return new Gson().fromJson(json, User.class);
    }
    /*

        0: If this is the first time.
        1: It has started ever.
        2: It has started once, but not that version , ie it is an update.

     */
    public static int getFirstTimeRun(Context context) {
        //Check if App Start First Time
        int appCurrentBuildVersion = BuildConfig.VERSION_CODE;
        int appLastBuildVersion = getPreferences(context).getInt(APP_FIRST_TIME, 0);


        if (appLastBuildVersion == appCurrentBuildVersion ) {
            return 1;

        } else {
            if (appLastBuildVersion == 0) {
                return 0;
            } else {
                return 2;
            }
        }
    }

    public static void appFirstRun(Context context){
        int appCurrentBuildVersion = BuildConfig.VERSION_CODE;
        getPreferences(context).edit().putInt(APP_FIRST_TIME,
                appCurrentBuildVersion).apply();
    }

    public static void setLoginCredentials(Context context,Object value){
        SharedPreferences.Editor prefsEditor = getPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        prefsEditor.putString(LOGIN_CREDENTIALS, json);
        prefsEditor.apply();
    }

    public static User getLoginCredentials(Context context) {

        Gson gson = new Gson();
        String json = getPreferences(context).getString(LOGIN_CREDENTIALS, "");
        return new Gson().fromJson(json, User.class);
    }

    /**
     *
     * @param context
     * @param pin
     */

    public static void setPinAuth(Context context, boolean pin) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(PIN_AUTH_PREF, pin);
        editor.apply();
    }

    public static boolean getPinAuth(Context context) {
        return getPreferences(context).getBoolean(PIN_AUTH_PREF, false);
    }

    public static void setPinCode(Context context,String value){
        SharedPreferences.Editor prefsEditor = getPreferences(context).edit();
        prefsEditor.putString(PIN_CODE, value);
        prefsEditor.apply();
    }

    public static String getPinCode(Context context) {
        return getPreferences(context).getString(PIN_CODE, "");
    }

    public static void setRetypePinCode(Context context,String value){
        SharedPreferences.Editor prefsEditor = getPreferences(context).edit();
        prefsEditor.putString(TEMPORARY_PIN_CODE, value);
        prefsEditor.apply();
    }

    public static String getRetypePinCode(Context context) {
        return getPreferences(context).getString(TEMPORARY_PIN_CODE, "");
    }


}
