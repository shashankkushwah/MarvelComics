package com.marvel.comics.di.comicdetail;

import com.marvel.comics.ui.comicdetail.ComicDetailActivity;
import com.marvel.comics.ui.comicdetail.ComicDetailContract;
import com.marvel.comics.ui.comicdetail.ComicDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shashank on 19/11/2017.
 */
@Module
public class ComicDetailActivityModule {

    private ComicDetailContract.View view;

    public ComicDetailActivityModule(ComicDetailContract.View view) {
        this.view = view;
    }

    @Provides
    @ComicDetailActivityScope
    public ComicDetailContract.View comicDetailView() {
        return view;
    }

}
