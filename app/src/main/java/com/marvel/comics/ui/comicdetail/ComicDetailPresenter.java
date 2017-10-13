package com.marvel.comics.ui.comicdetail;

import com.marvel.comics.data.model.Comic;

/**
 * Created by Shashank on 13/10/2017.
 */

public class ComicDetailPresenter implements ComicDetailContract.Presenter {

    private ComicDetailContract.View comicDetailView;

    public ComicDetailPresenter(ComicDetailContract.View comicDetailView) {
        this.comicDetailView = comicDetailView;
    }

    @Override
    public void loadComicDetail(Comic comic) {
        comicDetailView.showComicDetail(comic);
    }

    @Override
    public void onDestroy() {
        comicDetailView = null;
    }
}
