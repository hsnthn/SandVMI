package com.cybercrypt.sandvmi.api;

import com.cybercrypt.sandvmi.data.remote.model.User;
import com.cybercrypt.sandvmi.data.remote.response.LoginResponse;
import com.cybercrypt.sandvmi.data.remote.response.SignupResponse;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {
    @FormUrlEncoded
    @POST(ApiConstants.LOGIN)
    Observable<Response<LoginResponse>> login(@Field("username") String username,
                                              @Field("password") String password);

    @FormUrlEncoded
    @POST(ApiConstants.SIGNUP)
    Observable<Response<SignupResponse>> signup(@Field("email") String email,
                                                @Field("username") String username,
                                                @Field("password") String password);
}
