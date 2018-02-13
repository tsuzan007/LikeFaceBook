package com.example.macbookpro.MovieBook.Model.NetworkHelper;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.macbookpro.MovieBook.Model.NetworkHelper.DaggerRetrofitComponent.builder;


public class RetrofitCall implements RetrofitModelOps {

    static RetrofitComponent retrofitComponent;
    public MyMovieApiClient myMovieApiClient;

    public RetrofitCall() {
        retrofitComponent = builder().build();
        myMovieApiClient = retrofitComponent.getMovieApiClient();

    }

    public void onLoadPopularMovies() {
        Call<Movie> call = myMovieApiClient.getData("1c9be957b6ec20365e7917f29f3ebdc7");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                List<Movie.ResultsBean> upComingMovies = response.body().getResults();
                EventBus.getDefault().post(upComingMovies);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(".......", "message");


            }
        });
    }

    @Override
    public void onLoadUpcomingMovies() {
        Call<Movie> call = myMovieApiClient.getUpcomingData("1c9be957b6ec20365e7917f29f3ebdc7");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                List<Movie.ResultsBean> upComingMovies = response.body().getResults();
                EventBus.getDefault().post(upComingMovies);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(".......", "message");


            }
        });

    }

    @Override
    public void onLoadNowPlayingMovies() {
        Call<Movie> call = myMovieApiClient.getNowPlayingData("1c9be957b6ec20365e7917f29f3ebdc7");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                List<Movie.ResultsBean> upComingMovies = response.body().getResults();
                EventBus.getDefault().post(upComingMovies);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(".......", "message");


            }
        });

    }
}
