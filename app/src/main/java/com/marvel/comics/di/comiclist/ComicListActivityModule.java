package com.marvel.comics.di.comiclist;

import com.marvel.comics.data.model.Comic;
import com.marvel.comics.data.network.ApiHelper;
import com.marvel.comics.ui.comiclist.ComicListActivity;
import com.marvel.comics.ui.comiclist.ComicListAdapter;
import com.marvel.comics.ui.comiclist.ComicListContract;
import com.marvel.comics.ui.comiclist.ComicListPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shashank on 18/11/2017.
 */
@Module
public class ComicListActivityModule {

    private ComicListContract.View view;

    public ComicListActivityModule(ComicListContract.View view) {
        this.view = view;
    }

    @Provides
    @ComicListActivityScope
    public ComicListContract.View comicListActivity() {
        return view;
    }

}
