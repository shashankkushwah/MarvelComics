package com.marvel.comics.di.application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shashank on 18/11/2017.
 */
@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    public Context context() {
        return context;
    }
}
