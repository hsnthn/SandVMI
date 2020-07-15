package com.cybercrypt.sandvmi.ui;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.SandVMIApp;
import com.cybercrypt.sandvmi.databinding.ActivityVmiBinding;
import com.cybercrypt.sandvmi.ui.util.ILockedActivity;
import com.cybercrypt.sandvmi.ui.util.Utils;

public class VMIActivity extends Activity implements ILockedActivity {

    private ActivityVmiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((SandVMIApp) this.getApplication()).resumeActivity=this;
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vmi);
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
        Utils.hideNavigations(this);
    }

}
