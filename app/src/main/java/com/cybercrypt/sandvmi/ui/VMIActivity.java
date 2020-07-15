package com.cybercrypt.sandvmi.ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.SandVMIApp;
import com.cybercrypt.sandvmi.databinding.ActivityVmiBinding;
import com.cybercrypt.sandvmi.ui.util.ILockedActivity;
import com.cybercrypt.sandvmi.ui.util.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class VMIActivity extends Activity implements ILockedActivity {

    private ActivityVmiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((SandVMIApp) this.getApplication()).resumeActivity=this;
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vmi);

        /*
            Loading dialog

         */
        final Dialog filterDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        filterDialog.setContentView(R.layout.custom_loading_screen);
        filterDialog.setCancelable(false);
        filterDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        //Show the dialog!
        filterDialog.show();

        //Set the dialog to immersive sticky mode
        filterDialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //Clear the not focusable flag from the window
        filterDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        new Timer().schedule(new TimerTask() {
            public void run() {
                filterDialog.dismiss();
            }}, 6000);//time in milliseconds



        Bundle bundle = getIntent().getExtras();
        String vmiUrl="";
        if(bundle != null){
            vmiUrl = bundle.getString("vmiUrl");
        }

        binding.webview.loadUrl(vmiUrl);

        // Enable Javascript
        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        binding.webview.setWebViewClient(new WebViewClient());

    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.hideTopNavigations(this);
    }

}
