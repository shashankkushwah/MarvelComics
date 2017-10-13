package com.marvel.comics.ui;

/**
 * Created by Shashank on 13/10/2017.
 */

public interface BaseContract {

    interface View {
        void showError(String error);
    }

    interface Presenter {
        void onDestroy();
    }

}
