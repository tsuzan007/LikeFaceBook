package com.example.macbookpro.MovieBook.Model.NetworkHelper;

import android.util.Log;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.macbookpro.MovieBook.Model.NetworkHelper.DaggerRetrofitComponent.*;


public class RetrofitCall implements RetrofitModelOps {

    public MyMovieApiClient myMovieApiClient;

    static RetrofitComponent retrofitComponent;

    public void onLoadPopularMovies(){
        retrofitComponent = builder().build();
        myMovieApiClient=retrofitComponent.getMovieApiClient();
        Call<Movie> call=myMovieApiClient.getUpcomingData("1c9be957b6ec20365e7917f29f3ebdc7");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.e(".......",response.body()+"");
                List<Movie.ResultsBean> upComingMovies=response.body().getResults();
                EventBus.getDefault().post(upComingMovies);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(".......","message");


            }
        });
    }
}
