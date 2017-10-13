package com.marvel.comics.data.network;

import android.support.annotation.NonNull;

import com.marvel.comics.data.model.Comic;
import com.marvel.comics.data.model.ComicsResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shashank on 13/10/2017.
 */

public class ApiHelperImpl implements ApiHelper {

    private static final String PARAM_FORMAT = "format"; // comic,  digital comic?
    private static final String PARAM_LIMIT = "limit"; // 100 needed
    private static final String PARAM_OFFSET = "offset";

    private static final String FORMAT_COMIC = "comic";

    private MarvelApi api;

    public ApiHelperImpl(MarvelApi api) {
        this.api = api;
    }

    @Override
    public void getComics(int offset, int limit, final Callback<List<Comic>> callback) {
        api.getComics(params(offset, limit)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ComicsResponse>() {
            @Override
            public void accept(@NonNull ComicsResponse comicsResponse) throws Exception {
                callback.onSuccess(comicsResponse.getData().getResults());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                callback.onFailed(throwable.getMessage());
            }
        });
    }

    private Map<String, Object> params(int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put(PARAM_FORMAT, FORMAT_COMIC);
        params.put(PARAM_OFFSET, offset);
        params.put(PARAM_LIMIT, limit);
        return params;
    }
}
