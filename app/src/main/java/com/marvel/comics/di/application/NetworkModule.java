package com.marvel.comics.di.application;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.marvel.comics.data.network.ApiConfig;
import com.marvel.comics.data.network.AuthRequestInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by shashank on 18/11/2017.
 */
@Module(includes = {ContextModule.class})
public class NetworkModule {

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, AuthRequestInterceptor authRequestInterceptor, ConnectionPool connectionPool, Cache cache) {
        return new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor)
                .addInterceptor(authRequestInterceptor).readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .followSslRedirects(true)
                .followRedirects(true).retryOnConnectionFailure(true)
                .connectionPool(connectionPool)
                .cache(cache).build();
    }

    @Provides
    @ApplicationScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @ApplicationScope
    public AuthRequestInterceptor authRequestInterceptor() {
        return new AuthRequestInterceptor(ApiConfig.PUBLIC_KEY, ApiConfig
                .PRIVATE_KEY);
    }

    @Provides
    @ApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 50 * 1000 * 1000);
    }

    @Provides
    @ApplicationScope
    public File cacheFile(Context context) {
        File cacheFile = new File(context.getCacheDir(), "okttp_cache");
        cacheFile.mkdirs();
        return cacheFile;
    }

    @Provides
    @ApplicationScope
    public ConnectionPool connectionPool() {
        return new ConnectionPool(30, 120, TimeUnit.SECONDS);
    }
}
