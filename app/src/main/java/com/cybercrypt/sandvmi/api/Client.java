package com.cybercrypt.sandvmi.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.BuildConfig;

public class Client {
    private static Client instance = null;
    private Retrofit retrofit;
    private OkHttpClient client;
    private ApiService service;
    private static int REQUEST_TIMEOUT = 20;


    public Client() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        //if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(interceptor);
        //}

        client = okHttpBuilder.build();


        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        service = retrofit.create(ApiService.class);

    }

    public synchronized static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }

        return instance;
    }

    public ApiService getService() {
        return service;
    }

}
