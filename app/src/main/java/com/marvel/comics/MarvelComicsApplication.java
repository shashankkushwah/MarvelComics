package com.marvel.comics;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marvel.comics.data.network.ApiConfig;
import com.marvel.comics.data.network.ApiHelper;
import com.marvel.comics.data.network.ApiHelperImpl;
import com.marvel.comics.data.network.AuthRequestInterceptor;
import com.marvel.comics.data.network.MarvelApi;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shashank on 13/10/2017.
 */

public class MarvelComicsApplication extends Application {

    private ApiHelper apiHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        createDependencies();
    }

    private void createDependencies() {
        Gson gson = new GsonBuilder().create();

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor logginInterceptor = new HttpLoggingInterceptor();
            logginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(logginInterceptor);
        }

        AuthRequestInterceptor authRequestInterceptor = new AuthRequestInterceptor(ApiConfig.PUBLIC_KEY, ApiConfig.PRIVATE_KEY);
        httpClientBuilder.addInterceptor(authRequestInterceptor);

        OkHttpClient okHttpClient = httpClientBuilder
                .readTimeout(60, TimeUnit.SECONDS)//
                .connectTimeout(30, TimeUnit.SECONDS)//
                .followSslRedirects(true)//
                .followRedirects(true).retryOnConnectionFailure(true)//
                .connectionPool(new ConnectionPool(30, 120, TimeUnit.SECONDS))//
                .build();


        Retrofit retrofit = new Retrofit.Builder() //
                .baseUrl(ApiConfig.BASE_ENDPOINT) //
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //
                .addConverterFactory(GsonConverterFactory.create(gson)) //
                .client(okHttpClient) //
                .build();
        apiHelper = new ApiHelperImpl(retrofit.create(MarvelApi.class));

    }

    @NonNull
    public ApiHelper getApiHelper() {
        return apiHelper;
    }

}
