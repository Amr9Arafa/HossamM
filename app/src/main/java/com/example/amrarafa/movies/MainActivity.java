package com.example.amrarafa.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.example.amrarafa.movies.pojo.Example;
import com.example.amrarafa.movies.pojo.Result;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    String API_BASE_URL = "https://api.themoviedb.org";
    MovieAdapter mMovieAdapter;
    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=(GridView)findViewById(R.id.gridView);
        mMovieAdapter= new MovieAdapter(this,new ArrayList<Result>());
        gridView.setAdapter(mMovieAdapter);


        setRetrofit();

    }

    private void setRetrofit() {

        //        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL).
                        addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        ).build();

        MoviesInterface moviesInterface= retrofit.create(MoviesInterface.class);

        Observable<Example> movies = moviesInterface.resultsMovies("popularity");



        movies.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Example>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Example example) {

                        Log.d("TanTan","BamBAm  "+example.getResults().get(0).getTitle());
                        mMovieAdapter= new MovieAdapter(MainActivity.this, (ArrayList<Result>) example.getResults());
                        gridView.setAdapter(mMovieAdapter);
                    }
                });



                    /*THIS is rtrofit without RX.. */


//        Retrofit retrofit = builder.client(
//                                httpClient.build()
//                        ).build();
//        MoviesInterface client =  retrofit.create(MoviesInterface.class);

//        Call<Example> call =
//                client.resultsMovies("popularity");

//           Execute the call asynchronously. Get a positive or negative callback.
//           call.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                // The network call was a success and we got a response
//                // TODO: use the repository list and display it
//
//                  Log.d("TanTan","BamBAm  "+response.body().getResults().get(0).getTitle());
//
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//                // the network call was a failure
//                // TODO: handle error
//
//                Log.d("nannan",t.toString());
//
//            }
//        });


    }
}
