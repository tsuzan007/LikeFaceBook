package com.example.macbookpro.MovieBook.Model.NetworkHelper;

import android.content.res.Resources;
import android.util.Log;

import com.example.macbookpro.MovieBook.MovieDetailActivity;
import com.example.macbookpro.MovieBook.ViewClass.IConsumer;
import com.example.macbookpro.likefacebook.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
        Call<Movie> call = myMovieApiClient.getData(Resources.getSystem().getString(R.string.Apikey));
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                List<Movie.ResultsBean> upComingMovies = response.body().getResults();
                EventBus.getDefault().post(upComingMovies);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(".......", t.getMessage().toString());


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
                Log.e(".......", t.getMessage().toString());


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
                Log.e(".......", t.getMessage().toString());


            }
        });

    }

    @Override
    public void showdetails(int movieid) {
        Call<MovieDetail> call = myMovieApiClient.getMovieDetail(movieid,"1c9be957b6ec20365e7917f29f3ebdc7");
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                MovieDetail movieDetail = response.body();
                EventBus.getDefault().post(movieDetail);
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e(".......", t.getMessage().toString());


            }
        });

    }

}
