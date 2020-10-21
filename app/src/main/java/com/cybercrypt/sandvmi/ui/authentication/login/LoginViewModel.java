package com.cybercrypt.sandvmi.ui.authentication.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cybercrypt.sandvmi.api.Client;
import com.cybercrypt.sandvmi.api.Resources;
import com.cybercrypt.sandvmi.data.remote.model.User;
import com.cybercrypt.sandvmi.data.remote.response.LoginResponse;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    MutableLiveData<Resources> mutableLiveData = new MutableLiveData<>();

    void getUser(final String username, final String password) {
        trustAllCertificates();
        Client.getInstance()
                .getService()
                .login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<LoginResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ERROR", e.toString());
                    }

                    @Override
                    public void onNext(Response<LoginResponse> response) {
                        Log.w("sonuc", response.toString());
                        if (response.code() == 200) {
                            User user = new User();
                            user.username = username;
                            mutableLiveData.setValue(Resources.success(user, ""));
                        } else
                            mutableLiveData.setValue(Resources.error("", null));
                    }
                });
    }

    public void trustAllCertificates() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception e) {
        }
    }
}
