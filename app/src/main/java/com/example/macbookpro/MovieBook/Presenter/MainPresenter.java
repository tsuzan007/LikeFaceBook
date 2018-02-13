package com.example.macbookpro.MovieBook.Presenter;

import com.example.macbookpro.MovieBook.Model.NetworkHelper.RetrofitCall;
import com.example.macbookpro.MovieBook.Model.Model;
import com.facebook.login.LoginResult;


public class MainPresenter implements
        MVPContracts.ViewPresenterContract,
        MVPContracts.ModelPresenterContract,
        MVPContracts.FeedsPresenterContract{






    private MVPContracts.RequestModel requestModel;
    private RetrofitCall retrofitCall;



    public MainPresenter() {

        this.requestModel=new Model(this);
        retrofitCall=new RetrofitCall();

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

}
