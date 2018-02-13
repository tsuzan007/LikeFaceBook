package com.example.macbookpro.MovieBook.Presenter;

import com.example.macbookpro.MovieBook.Model.NetworkHelper.RetrofitCall;

/**
 * Created by macbookpro on 2/13/18.
 */

public class RecyclerViewPresenter implements RecyclerViewPresenterOps {
    RetrofitCall retrofitCall;

    public RecyclerViewPresenter() {
       retrofitCall=new RetrofitCall();
    }

    @Override
    public void onItemClicked(int movieID) {
        retrofitCall.showdetails(movieID);
    }
}
