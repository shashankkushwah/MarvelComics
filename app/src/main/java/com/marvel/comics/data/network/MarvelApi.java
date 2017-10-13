package com.marvel.comics.data.network;

import com.marvel.comics.data.model.ComicsResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Shashank on 13/10/2017.
 */

public interface MarvelApi {

    @GET("v1/public/comics")
    Observable<ComicsResponse> getComics(@QueryMap Map<String, Object> params);

}
