package com.developers.televize.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.R;
import com.developers.televize.Util.Constants;
import com.developers.televize.Util.ViewCallBack;
import com.developers.televize.fragments.PopularFragment.PopularTvView;
import com.developers.televize.model.PopularTvModel.Result;
import com.developers.televize.ui.DetailActivity.DetailActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amanjeet Singh on 17/7/17.
 */

public class PopularTvShowsAdapter extends RecyclerView.Adapter<PopularTvShowsAdapter.PopularViewHolder> implements ViewCallBack {

    private Context context;
    private List<Result> results;
    private Intent intent;
    private Gson gson;
    private PopularTvView popularTvView;


    public PopularTvShowsAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PopularViewHolder holder, final int position) {
        Picasso.with(context).load(Constants.BASE_URL_IMAGES + results.get(position).getBackdropPath()).into(holder.tvPoster);
        holder.tvTitle.setText(results.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
                popularTvView.launchDetailActivity(results.get(position),holder.tvPoster,holder.tvTitle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public void setPopularTvView(PopularTvView popularTvView) {
        this.popularTvView = popularTvView;
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_image_view)
        ImageView tvPoster;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.tv_title_textview)
        TextView tvTitle;

        public PopularViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
