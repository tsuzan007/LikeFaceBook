package com.example.macbookpro.MovieBook;

import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.MovieBook.Model.NetworkHelper.MovieDetail;
import com.example.macbookpro.likefacebook.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.imageView_movie)
    ImageView imageView;
    @BindView(R.id.textView_title)
    TextView title;
    @BindView(R.id.textView_imdbid)
    TextView imbid;
    @BindView(R.id.textView_status)
    TextView status;
    @BindView(R.id.textView_vote)
    TextView vote;
    @BindView(R.id.textView_company)
    TextView company;
    @BindView(R.id.textView_prodcountry)
    TextView prodcountry;
    @BindView(R.id.textView_budget)
    TextView budget;
    @BindView(R.id.textView_overview)
    TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void updateDetail(MovieDetail movieDetail){
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500"+movieDetail.getPoster_path()).into(imageView);
        title.setText(movieDetail.getTitle());
        imbid.setText("IMDB ID "+movieDetail.getImdb_id());
        status.setText("Status "+movieDetail.getStatus());
        vote.setText("Vote "+movieDetail.getVote_average()+"");
        company.setText("Company "+movieDetail.getProduction_companies().get(0).getName());
        prodcountry.setText("Country "+movieDetail.getProduction_countries().get(0).getName());
        budget.setText("Budget "+movieDetail.getBudget()+"");
        overview.setText(movieDetail.getOverview());
    }
}
