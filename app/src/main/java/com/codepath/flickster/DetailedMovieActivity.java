package com.codepath.flickster;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flickster.models.Movie;
import com.codepath.flickster.models.MovieLibrary;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.loopj.android.http.AsyncHttpClient.log;

public class DetailedMovieActivity extends AppCompatActivity {

    private final String TAG = "DetailedMovieActivity";

    public MovieLibrary movieLibrary;

    @BindView(R.id.ivMovieImage) public ImageView poster;
    @BindView(R.id.tvTitle) public TextView originalTitle;
    @BindView(R.id.tvOverview) public TextView overview;
    @BindView(R.id.rbRating) public RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_movie_detail);

        //Add icon to title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher_round);
        ButterKnife.bind(this);
//        originalTitle = (TextView) findViewById(R.id.tvTitle);
//        poster = (ImageView) findViewById(R.id.ivMovieImage);
//        overview = (TextView) findViewById(R.id.tvOverview);
//        rating = (RatingBar) findViewById(R.id.rbRating);

        movieLibrary = MovieLibrary.getInstance();
        Intent intent = getIntent();
        int position = intent.getIntExtra("MoviePosition",0);

        int movieLibrarySize = movieLibrary.movies.size();
        if (movieLibrarySize > position) {
            Movie movie = movieLibrary.movies.get(position);

            //populate information
            originalTitle.setText(movie.getOriginalTitle());
            overview.setText(movie.getOverview());
            double d = movie.getVoteAverage(5);
            float averageRating = (float) d;
            rating.setRating(averageRating);
            String imagePath = movie.getPosterPath();

            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                log.d("debug", "landscape");
                imagePath = movie.getBackdropPath();
            }
            Picasso.with(this).load(imagePath).placeholder(R.drawable.bananatransparent).into(poster);
        }
        else{
            Log.d(TAG, "position and movieLibrarySize incomatible");
        }
    }
}
