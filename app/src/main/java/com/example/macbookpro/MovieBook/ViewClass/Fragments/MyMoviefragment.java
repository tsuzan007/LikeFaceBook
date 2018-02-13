package com.example.macbookpro.MovieBook.ViewClass.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.macbookpro.MovieBook.Model.NetworkHelper.Movie;
import com.example.macbookpro.MovieBook.POJOClasses.MessageEvent;
import com.example.macbookpro.MovieBook.Presenter.MainPresenter;
import com.example.macbookpro.MovieBook.ViewClass.HelperClass.MyRecyclerViewAdapter;
import com.example.macbookpro.likefacebook.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class MyMoviefragment extends Fragment{
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private StaggeredGridLayoutManager stagManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Movie.ResultsBean> mylist = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private MainPresenter mainPresenter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mylist.clear();
        mainPresenter = new MainPresenter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friendlistfragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.myrecyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        sharedPreferences=getActivity().getSharedPreferences("userstatus",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putBoolean("userStatus",true);
        editor.commit();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                fetchTimelineAsync(0);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainPresenter.onLoadPopularMovies();
        EventBus.getDefault().register(this);
        adapter = new MyRecyclerViewAdapter(getActivity(), mylist);
        stagManager = new StaggeredGridLayoutManager(2, 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        if (recyclerView.getLayoutManager().equals(stagManager)) {
            recyclerView.setLayoutManager(linearLayoutManager);

        } else {
            recyclerView.setLayoutManager(stagManager);
        }
        adapter.notifyDataSetChanged();
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.

        mylist.clear();
        mainPresenter.onLoadPopularMovies();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void updateView(List<Movie.ResultsBean> upcomingMovies) {
        for(Movie.ResultsBean r: upcomingMovies){
            mylist.add(r);

        }
        adapter.notifyDataSetChanged();
    }



}
