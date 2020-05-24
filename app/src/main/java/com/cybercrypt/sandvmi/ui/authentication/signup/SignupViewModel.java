package com.cybercrypt.sandvmi.ui.authentication.signup;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cybercrypt.sandvmi.api.Client;
import com.cybercrypt.sandvmi.api.Resources;
import com.cybercrypt.sandvmi.data.remote.response.SignupResponse;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

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
                        else{
                            Gson gson = new Gson();
                            SignupResponse registerResponse = null;
                            TypeAdapter<SignupResponse> adapter = gson.getAdapter(SignupResponse.class);
                            try {
                                if (response.errorBody() != null)
                                    registerResponse =
                                            adapter.fromJson(
                                                    response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                                registerResponse = new SignupResponse();
                                registerResponse.message="unknown error";
                            }
                            mutableLiveData.setValue(Resources.error(registerResponse.message,null));
                        }
                    }
                });
    }
}