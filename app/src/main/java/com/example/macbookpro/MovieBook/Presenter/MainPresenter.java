package com.example.macbookpro.MovieBook.Presenter;

import com.example.macbookpro.MovieBook.Model.Model;
import com.example.macbookpro.MovieBook.Model.NetworkHelper.RetrofitCall;
import com.facebook.login.LoginResult;


public class MainPresenter implements
        MVPContracts.ViewPresenterContract {


    private MVPContracts.RequestModel requestModel;
    private RetrofitCall retrofitCall;


    public MainPresenter() {
        this.requestModel = new Model();
        retrofitCall = new RetrofitCall();
    }


    //when login is success, message onSuccess is called from the fragment


    @Override
    public void onSignupClicked() {


    }


    public void onSuccess(LoginResult loginResult) {
        requestModel.getUserInfo();


    }

    @Override
    public void onLoadPopularMovies() {
        retrofitCall.onLoadPopularMovies();


    }

    @Override
    public void onLoadUpcomingMovies() {
        retrofitCall.onLoadUpcomingMovies();


    }

    @Override
    public void onLoadNowPlayingMovies() {
        retrofitCall.onLoadNowPlayingMovies();

    }

}
