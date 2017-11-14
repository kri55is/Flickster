package com.codepath.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.flickster.adapters.MovieArrayAdapter;
import com.codepath.flickster.models.MovieLibrary;
import com.codepath.flickster.models.MoviesResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    String TAG = "MovieActivity";
    MovieLibrary movieLibrary;
    MovieArrayAdapter movieAdapter;
    @BindView(R.id.lvMovies) ListView lvItems;

    MovieClient.FlickerEndpointInterface client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        //Add icon to title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher_round);

        movieLibrary = MovieLibrary.getInstance();
        ButterKnife.bind(this);
        movieAdapter = new MovieArrayAdapter(this, movieLibrary.movies);
        lvItems.setAdapter(movieAdapter);
        lvItems.setOnItemClickListener(mClickItemListener);

        client = MovieClient.getMovieClient();
        Call<MoviesResponse> call = client.getMoviesNowPlaying();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                MoviesResponse moviesResponse = response.body();
                movieLibrary.movies.clear();
                movieLibrary.movies.addAll(moviesResponse.getMovies());
                movieAdapter.notifyDataSetChanged();
                Log.d(TAG,"StatusCode="+ statusCode + ". Found " + movieLibrary.movies.size() + " movies");
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable throwable) {
                Log.d(TAG, "failure");
            }
        });
    }

    private AdapterView.OnItemClickListener mClickItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getBaseContext(), "click ", Toast.LENGTH_SHORT);

            Intent intent = new Intent(getApplicationContext(), DetailedMovieActivity.class);
            intent.putExtra("MoviePosition", i);
            startActivity(intent);
        }
    };

}
