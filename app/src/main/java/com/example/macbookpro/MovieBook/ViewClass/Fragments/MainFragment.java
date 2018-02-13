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

import com.example.macbookpro.likefacebook.R;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainFragment extends Fragment {

    @BindView(R.id.myViewPager)
    ViewPager viewPager;
    @BindView(R.id.myTablayout)
    TabLayout tabLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }



    public class MyFragmentAdapter extends FragmentPagerAdapter {
        String[] fragmentname = {"Upcoming Movies","Now Playing","Popular Movies",""};

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentname[position];
        }

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Fragment friendFragment = new MyMoviefragment();
                    return friendFragment;
                case 1:
                    Fragment nowPlayingFragment = new NowPlayingFragment();
                    return nowPlayingFragment;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return fragmentname.length;
        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}
