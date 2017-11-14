package com.codepath.flickster.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilie on 11/13/17.
 */

public class MoviesResponse {

    public List<Movie> getMovies() {
        return movies;
    }

    @SerializedName("results")
    List<Movie> movies;

    public MoviesResponse() {
            movies = new ArrayList<>();
        }

    public static MoviesResponse fromJson(String response) {
        Gson gson = new GsonBuilder().create();
        MoviesResponse boxOfficeMovieResponse = gson.fromJson(response, MoviesResponse.class);
        return boxOfficeMovieResponse;
    }
}
