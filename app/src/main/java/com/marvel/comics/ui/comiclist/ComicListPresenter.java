package com.marvel.comics.ui.comiclist;

import com.marvel.comics.data.model.Comic;
import com.marvel.comics.data.network.ApiHelper;

import java.util.Collections;
import java.util.List;

/**
 * Created by Shashank on 13/10/2017.
 */

public class ComicListPresenter implements ComicListContract.Presenter {
    private static final int LIMIT = 100;

    private ComicListContract.View comicListView;
    private ApiHelper apiHelper;

    public ComicListPresenter(ComicListContract.View comicListView, ApiHelper apiHelper) {
        this.comicListView = comicListView;
        this.apiHelper = apiHelper;
    }

    @Override
    public void loadComicList() {
        comicListView.setProgressIndicator(true);
        apiHelper.getComics(0, LIMIT, new ApiHelper.Callback<List<Comic>>() {
            @Override
            public void onSuccess(List<Comic> data) {
                comicListView.setProgressIndicator(false);
                Collections.sort(data);
                comicListView.showComicList(data);
            }

            @Override
            public void onFailed(String message) {
                comicListView.setProgressIndicator(false);
                comicListView.showError(message);
            }
        });
    }

    @Override
    public void onComicItemClick(Comic comic) {
        comicListView.showComicDetail(comic);
    }

    @Override
    public void onDestroy() {
        comicListView = null;
    }
}
