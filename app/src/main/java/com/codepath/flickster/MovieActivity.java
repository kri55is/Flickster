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
import com.codepath.flickster.models.Movie;
import com.codepath.flickster.models.MovieLibrary;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    String TAG = "MovieActivity";
    MovieLibrary movieLibrary;
    MovieArrayAdapter movieAdapter;
    @BindView(R.id.lvMovies) ListView lvItems;

    NowPlayingHandler nowPlayingHandler;
    MovieClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        //Add icon to title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher_round);

        movieLibrary = MovieLibrary.getInstance();
        //lvItems = (ListView) findViewById(R.id.lvMovies);
        ButterKnife.bind(this);
        movieAdapter = new MovieArrayAdapter(this, movieLibrary.movies);
        lvItems.setAdapter(movieAdapter);
        lvItems.setOnItemClickListener(mClickItemListener);

        nowPlayingHandler = new NowPlayingHandler();
        client = MovieClient.getInstance();
        client.getMoviesNowPlaying(nowPlayingHandler);
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


    public class NowPlayingHandler extends JsonHttpResponseHandler{
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            JSONArray movieJsonResults = null;

            try {
                movieJsonResults = response.getJSONArray("results");
                movieLibrary.movies.addAll(Movie.fromJSONArray(movieJsonResults));
                movieAdapter.notifyDataSetChanged();
                Log.d(TAG, movieLibrary.movies.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
            super.onSuccess(statusCode, headers, response);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {
            super.onSuccess(statusCode, headers, responseString);
        }
    }

}
