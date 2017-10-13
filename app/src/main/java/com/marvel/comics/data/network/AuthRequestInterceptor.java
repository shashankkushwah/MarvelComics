package com.marvel.comics.data.network;

import com.marvel.comics.utils.Md5Util;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shashank on 13/10/2017.
 */

public class AuthRequestInterceptor implements Interceptor {
    private static final String PARAM_API_KEY = "apikey";
    private static final String PARAM_TIMESTAMP = "ts";
    private static final String PARAM_HASH = "hash";

    private final String apiKey;
    private final String apiSecret;

    public AuthRequestInterceptor(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        long timestamp = System.currentTimeMillis();
        String hash = Md5Util.md5(timestamp + apiSecret + apiKey);

        // All calls to the Marvel Comics API must pass your public key via an “apikey” parameter.
        //        ts - a timestamp (or other long string which can change on a request-by-request basis)
        //        hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5
        // (ts+privateKey+publicKey)

        HttpUrl url = originalHttpUrl.newBuilder().addQueryParameter(PARAM_API_KEY, apiKey).addQueryParameter
                (PARAM_TIMESTAMP, Long.toString(timestamp)).addQueryParameter(PARAM_HASH, hash).build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder().url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
