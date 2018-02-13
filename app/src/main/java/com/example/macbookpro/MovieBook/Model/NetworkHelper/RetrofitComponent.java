package com.example.macbookpro.MovieBook.Model.NetworkHelper;


import dagger.Component;


@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    MyMovieApiClient getMovieApiClient();




}
