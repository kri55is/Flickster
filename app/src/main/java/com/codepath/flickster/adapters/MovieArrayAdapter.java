package com.codepath.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by emilie on 9/11/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    static class ViewHolder{
        @BindView(R.id.ivMovieImage) ImageView posterPath;
        @BindView(R.id.tvTitle) TextView originalTitle;
        @BindView(R.id.tvOverview) TextView overview;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        //get the data item from the position
        Movie movie = getItem(position);

        ViewHolder viewHolder;

        //check the view being used
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder = new ViewHolder(convertView);
            viewHolder.posterPath = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.originalTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //populate information
        String imagePath = movie.getPosterPath();;

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            log.d("debug", "landscape");
            imagePath = movie.getBackdropImage();
        }
        Picasso.with(getContext()).load(imagePath).placeholder(R.drawable.bananatransparent).into(viewHolder.posterPath);

        viewHolder.originalTitle.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());


        return convertView;

    }
}
