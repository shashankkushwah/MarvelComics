package com.marvel.comics.di.comicdetail;

import com.marvel.comics.di.application.ApplicationComponent;
import com.marvel.comics.ui.comicdetail.ComicDetailActivity;

import dagger.Component;

/**
 * Created by shashank on 19/11/2017.
 */
@Component(modules = {ComicDetailActivityModule.class}, dependencies = {ApplicationComponent.class})
@ComicDetailActivityScope
public interface ComicDetailActivityComponent {

    void injectComicDetailActivity(ComicDetailActivity activity);
}
