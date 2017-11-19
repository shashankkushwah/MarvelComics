package com.marvel.comics.di.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marvel.comics.data.network.ApiConfig;
import com.marvel.comics.data.network.ApiHelper;
import com.marvel.comics.data.network.ApiHelperImpl;
import com.marvel.comics.data.network.MarvelApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shashank on 18/11/2017.
 */
@Module(includes = {NetworkModule.class})
public class ApiHelperModule {

    @Provides
    @ApplicationScope
    public ApiHelper apiHelper(Retrofit retrofit) {
        return new ApiHelperImpl(retrofit.create(MarvelApi.class));
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    public Gson gson() {
        return new GsonBuilder().create();
    }
}
