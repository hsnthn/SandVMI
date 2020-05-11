package com.cybercrypt.sandvmi.ui.authentication.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cybercrypt.sandvmi.api.Client;
import com.cybercrypt.sandvmi.api.Resources;
import com.cybercrypt.sandvmi.data.remote.model.User;
import com.cybercrypt.sandvmi.data.remote.response.LoginResponse;

import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    MutableLiveData<Resources> mutableLiveData = new MutableLiveData<>();

    void getUser(final String username, final String password) {
        Client.getInstance()
                .getService()
                .login(username,password)
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
                        Log.w("sonuc",response.toString());
                        if (response.code() == 200) {
                            User user = new User();
                            user.username=username;
                            mutableLiveData.setValue(Resources.success(user,""));
                        }else
                            mutableLiveData.setValue(Resources.error("",null));
                    }
                });
    }
}
