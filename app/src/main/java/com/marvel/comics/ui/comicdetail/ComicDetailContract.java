package com.marvel.comics.ui.comicdetail;

import com.marvel.comics.data.model.Comic;
import com.marvel.comics.ui.BaseContract;

/**
 * Created by Shashank on 13/10/2017.
 */

public interface ComicDetailContract {

    interface View extends BaseContract.View {
        void showComicDetail(Comic comic);
    }

    interface Presenter extends BaseContract.Presenter {
        void loadComicDetail(Comic comic);
    }

}
