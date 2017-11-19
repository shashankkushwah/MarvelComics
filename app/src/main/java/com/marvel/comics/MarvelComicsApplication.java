package com.marvel.comics;

import android.app.Activity;
import android.app.Application;

import com.marvel.comics.di.application.ApplicationComponent;
import com.marvel.comics.di.application.ContextModule;
import com.marvel.comics.di.application.DaggerApplicationComponent;

/**
 * Created by Shashank on 13/10/2017.
 */

public class MarvelComicsApplication extends Application {

    public static MarvelComicsApplication get(Activity activity) {
        return (MarvelComicsApplication) activity.getApplicationContext();
    }

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
