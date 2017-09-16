package com.codepath.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by emilie on 9/11/17.
 */

public class Movie {

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropImage() {
        return String.format("https://image.tmdb.org/t/p/w342%s", backdropImage);

    }
    public float getRating(int div) {
        //we get the rating which is a rating on 10. Here we might want to get the rating on something else.
        if(div >0) {
            float result = (rating / 10) * div;
            return result;
        }
        else
            return 0;
    }

    String posterPath;
    String originalTitle;
    String overview;
    String backdropImage;
    float rating;


    public Movie (JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropImage = jsonObject.getString("backdrop_path");
        this.rating =(float) jsonObject.getDouble("vote_average");

    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0 ; x < array.length(); x++){

            try {
                results.add(new Movie(array.getJSONObject(x)));
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
        return results;
    }
}
