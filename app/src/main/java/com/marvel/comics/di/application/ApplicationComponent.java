package com.marvel.comics.di.application;

import com.marvel.comics.data.network.ApiHelper;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by shashank on 18/11/2017.
 */

@Component(modules = {ApiHelperModule.class, PicassoModule.class})
@ApplicationScope
public interface ApplicationComponent {

    ApiHelper apiHelper();

    Picasso picasso();

}
