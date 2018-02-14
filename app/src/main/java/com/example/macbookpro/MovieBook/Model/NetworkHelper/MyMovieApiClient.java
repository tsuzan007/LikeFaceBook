package com.example.macbookpro.MovieBook.Model.NetworkHelper;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MyMovieApiClient {

    @GET("popular?")
    Call<Movie> getData(@Query("api_key") String key);

    @GET("upcoming?")
    Call<Movie> getUpcomingData(@Query("api_key") String key);

    @GET("now_playing?")
    Call<Movie> getNowPlayingData(@Query("api_key") String key);

    @GET("{movie_id}?")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") int movie_id, @Query("api_key") String key);

}
