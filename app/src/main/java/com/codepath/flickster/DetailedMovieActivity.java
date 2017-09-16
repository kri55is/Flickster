package com.codepath.flickster;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.models.Movie;
import com.codepath.flickster.models.MovieLibrary;
import com.squareup.picasso.Picasso;

import static com.loopj.android.http.AsyncHttpClient.log;

public class DetailedMovieActivity extends AppCompatActivity {

    private final String TAG = "DetailedMovieActivity";

    public MovieLibrary movieLibrary;

    public ImageView poster;
    public TextView originalTitle;
    public TextView overview;
    public ImageView backdropImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_movie_detail);

        originalTitle = (TextView) findViewById(R.id.tvTitle);
        poster = (ImageView) findViewById(R.id.ivMovieImage);
        overview = (TextView) findViewById(R.id.tvOverview);

        movieLibrary = MovieLibrary.getInstance();
        Intent intent = getIntent();
        int position = intent.getIntExtra("MoviePosition",0);

        int movieLibrarySize = movieLibrary.movies.size();
        if (movieLibrarySize > position) {
            Movie movie = movieLibrary.movies.get(position);

            originalTitle.setText(movie.getOriginalTitle());
            overview.setText(movie.getOverview());
            //populate information
            String imagePath = movie.getPosterPath();;

            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                log.d("debug", "landscape");
                imagePath = movie.getBackdropImage();
            }
            Picasso.with(this).load(imagePath).placeholder(R.drawable.bananatransparent).into(poster);
        }
        else{
            Log.d(TAG, "position and movieLibrarySize incomatible");
        }
    }
}
