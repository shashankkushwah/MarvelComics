package com.marvel.comics.di.comiclist;

import com.marvel.comics.di.application.ApplicationComponent;
import com.marvel.comics.ui.comiclist.ComicListActivity;

import dagger.Component;

/**
 * Created by shashank on 18/11/2017.
 */
@Component(modules = {ComicListActivityModule.class}, dependencies = {ApplicationComponent.class})
@ComicListActivityScope
public interface ComicListActivityComponent {

    void injectComicListActivity(ComicListActivity activity);

}
