package com.example.amrarafa.movies;

import com.example.amrarafa.movies.pojo.Example;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by amr arafa on 4/12/2017.
 */
public interface MoviesInterface {

    @GET("/3/discover/movie?api_key=19dfd5ebe589153dc9d6788c7c9f347b")
    //This is RX YA SON SOOON
    Observable<Example> resultsMovies(
            @Query("sort_by") String sort_by
    );
}
