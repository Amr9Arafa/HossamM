package com.example.amrarafa.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.amrarafa.movies.pojo.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by amr arafa on 4/15/2017.
 */
public class MovieAdapter extends ArrayAdapter<Result> {

    public MovieAdapter(Context context, ArrayList<Result> movieDetails) {
        super(context, 0 ,movieDetails);
        // TODO Auto-generated constructor stub
    }

    public MovieAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View recycled, ViewGroup container) {
        final ImageView myImageView;
        if (recycled == null) {
            myImageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.grid_view_poster, container, false);
        } else {
            myImageView = (ImageView) recycled;
        }

        Result movie = getItem(position);

        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p/w185/"+movie.getPosterPath())
                .fit()
                .into(myImageView);


        return myImageView;
    }
}