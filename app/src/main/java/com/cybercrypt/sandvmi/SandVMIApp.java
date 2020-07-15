package com.cybercrypt.sandvmi;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.cybercrypt.sandvmi.ui.AuthenticationActivity;
import com.cybercrypt.sandvmi.ui.util.ILockedActivity;

public class SandVMIApp extends Application implements LifecycleObserver {

    public Activity resumeActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onAppBackgrounded() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onAppForegrounded() {
        // app in foreground
        if (resumeActivity != null)
            if (resumeActivity instanceof ILockedActivity) {
                Intent intent = new Intent(getApplicationContext(), AuthenticationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("fragment", AuthenticationActivity.PINLOCKTAG);
                startActivity(intent);
            }
    }

}