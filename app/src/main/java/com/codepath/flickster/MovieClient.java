package com.codepath.flickster;

import android.util.Log;

import com.codepath.flickster.models.MoviesResponse;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by emilie on 11/6/17.
 */

public class MovieClient {
    final static String TAG = "MovieClientTAG";

    final static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    final static String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";

    static FlickerEndpointInterface flickerEndpointInterface;

    private MovieClient(){}

    public static FlickerEndpointInterface getMovieClient(){
        if (flickerEndpointInterface == null){
            OkHttpClient okhttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl httpUrl = original.url();

                            HttpUrl newUrl = httpUrl.newBuilder().addQueryParameter("api_key", API_KEY).build();
                            Request.Builder requestBuilder = original.newBuilder().url(newUrl);

                            Request request = requestBuilder.build();
                            Log.d(TAG, "Request send = " + request );

                            return chain.proceed(request);
                        }
                    })
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okhttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            flickerEndpointInterface = retrofit.create(FlickerEndpointInterface.class);
        }
        return flickerEndpointInterface;
    }

    public interface FlickerEndpointInterface {
        // Request method and URL specified in the annotation
        // Callback for the parsed response is the last parameter

        @GET("now_playing")
        Call<MoviesResponse> getMoviesNowPlaying();

    }
}
