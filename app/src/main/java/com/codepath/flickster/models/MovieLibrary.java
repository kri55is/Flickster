package com.codepath.flickster.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilie on 9/15/17.
 */

public class MovieLibrary {

    private static MovieLibrary mMovieLibaray = null;
    public List<Movie> movies;

    private MovieLibrary(){
        movies = new ArrayList<>();
    }

    public static  MovieLibrary getInstance(){
        if (mMovieLibaray == null){
            mMovieLibaray = new MovieLibrary();
        }
        return mMovieLibaray;
    }




}
