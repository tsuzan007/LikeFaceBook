package com.example.macbookpro.MovieBook.ViewClass.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macbookpro.MovieBook.Presenter.MainPresenter;
import com.example.macbookpro.likefacebook.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends Fragment {

    @BindView(R.id.myViewPager)
    ViewPager viewPager;
    @BindView(R.id.myTablayout)
    TabLayout tabLayout;
    MainPresenter mainPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        createViewPager();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public void createViewPager() {
        tabLayout.setupWithViewPager(viewPager);
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getFragmentManager());
        viewPager.setAdapter(myFragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mainPresenter.onLoadUpcomingMovies();
                        break;
                    case 1:
                        mainPresenter.onLoadNowPlayingMovies();
                        break;
                    default:
                        mainPresenter.onLoadUpcomingMovies();

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public class MyFragmentAdapter extends FragmentPagerAdapter {
        String[] fragmentname = {"Upcoming Movies", "Now Playing"};

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentname[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    MyMoviefragment UpcomingFragment = new MyMoviefragment();
                    UpcomingFragment.setIdentifier(position);
                    return UpcomingFragment;
                case 1:
                    MyMoviefragment nowPlayingFragment = new MyMoviefragment();
                    nowPlayingFragment.setIdentifier(position);
                    return nowPlayingFragment;
                default:
                    UpcomingFragment = new MyMoviefragment();
                    UpcomingFragment.setIdentifier(position);
                    return UpcomingFragment;
            }

        }

        @Override
        public int getCount() {
            return fragmentname.length;
        }
    }
}
