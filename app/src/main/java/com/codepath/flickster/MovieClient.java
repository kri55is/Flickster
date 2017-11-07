package com.codepath.flickster;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by emilie on 11/6/17.
 */

public class MovieClient {
    final static String TAG = "MovieClientTAG";

    final static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    final static String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    static MovieClient instance = null;
    AsyncHttpClient client;


    private MovieClient(){
        client = new AsyncHttpClient();
    }

    public static MovieClient getInstance(){
        if (instance == null){
            instance = new MovieClient();
        }
        return instance;
    }

    void getMoviesNowPlaying(JsonHttpResponseHandler handler){
        String request = BASE_URL + "now_playing";
        RequestParams params = new RequestParams();
        params.put("api_key", API_KEY);
        Log.d(TAG, "Request send = " + request + params.toString());
        client.get(request, params, handler);
    }
}
