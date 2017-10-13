package com.marvel.comics.ui.comiclist;

import com.marvel.comics.data.model.Comic;
import com.marvel.comics.ui.BaseContract;

import java.util.List;

/**
 * Created by Shashank on 13/10/2017.
 */

public interface ComicListContract {

    interface View extends BaseContract.View {
        void setProgressIndicator(boolean active);

        void showComicList(List<Comic> comicList);

        void showComicDetail(Comic comic);
    }

    interface Presenter extends BaseContract.Presenter {

        void loadComicList();

        void onComicItemClick(Comic comic);

    }
}
