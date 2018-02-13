package com.example.macbookpro.MovieBook.ViewClass.HelperClass;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.MovieBook.Model.NetworkHelper.Movie;
import com.example.macbookpro.likefacebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    Context context;
   List<Movie.ResultsBean> list;

    public MyRecyclerViewAdapter(Context context, List<Movie.ResultsBean> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (list.size() != 0) {

                Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+list.get(position).getPoster_path()).into(holder.imageView);
                holder.textView.setText(list.get(position).getTitle());
                holder.textView1.setText(list.get(position).getOverview());
                holder.textView2.setText(list.get(position).getPopularity()+"");
                holder.textView3.setText(list.get(position).getRelease_date()+"");


        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        ImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            textView = (TextView) itemView.findViewById(R.id.title);
            textView1 = (TextView) itemView.findViewById(R.id.story);
            textView2 = (TextView) itemView.findViewById(R.id.popularityNo);
            textView3 = (TextView) itemView.findViewById(R.id.releasedateValue);
            imageView = (ImageView) itemView.findViewById(R.id.imageView3);
        }
    }
}
