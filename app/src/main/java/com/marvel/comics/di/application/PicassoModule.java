package com.marvel.comics.di.application;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shashank on 18/11/2017.
 */
@Module(includes = {NetworkModule.class})
public class PicassoModule {

    @Provides
    @ApplicationScope
    public Picasso picasso(Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context).downloader(okHttp3Downloader).build();
    }
}
