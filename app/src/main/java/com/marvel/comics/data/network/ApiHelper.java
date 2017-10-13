package com.marvel.comics.data.network;

import com.marvel.comics.data.model.Comic;

import java.util.List;

/**
 * Created by Shashank on 13/10/2017.
 */

public interface ApiHelper {

    interface Callback<T> {

        void onSuccess(T data);

        void onFailed(String message);
    }

    void getComics(int offset, int limit, Callback<List<Comic>> callback);

}
