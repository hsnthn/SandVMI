package com.cybercrypt.sandvmi.ui.authentication.signup;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cybercrypt.sandvmi.api.Client;
import com.cybercrypt.sandvmi.api.Resources;
import com.cybercrypt.sandvmi.data.remote.response.SignupResponse;

import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SignupViewModel extends ViewModel {
    MutableLiveData<Resources> mutableLiveData = new MutableLiveData<>();

    void signup(final String email,final String username, final String password) {
        Client.getInstance()
                .getService()
                .signup(email,username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<SignupResponse>>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ERROR", e.toString());
                    }

                    @Override
                    public void onNext(Response<SignupResponse> response) {
                        Log.w("sonuc",response.toString());
                        if (response.code() == 200)
                            mutableLiveData.setValue(Resources.success(null,""));
                        else
                            mutableLiveData.setValue(Resources.error(response.message(),null));
                    }
                });
    }
}