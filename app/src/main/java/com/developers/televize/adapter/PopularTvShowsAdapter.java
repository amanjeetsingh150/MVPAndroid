package com.developers.televize.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.R;
import com.developers.televize.model.PopularTvModel.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amanjeet Singh on 17/7/17.
 */

public class PopularTvShowsAdapter extends RecyclerView.Adapter<PopularTvShowsAdapter.PopularViewHolder> {

    private Context context;
    private List<Result> results;
    private static final String BASE_URL_IMAGES="http://image.tmdb.org/t/p/w185";


    public PopularTvShowsAdapter(Context context, List<Result> results){
        this.context=context;
        this.results=results;
    }

    @Override
    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_row,parent,false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularViewHolder holder, int position) {
        Picasso.with(context).load(BASE_URL_IMAGES+results.get(position).getBackdropPath()).into(holder.tvPoster);
        holder.tvTitle.setText(results.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_image_view)
        ImageView tvPoster;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.tv_title_textview)
        TextView tvTitle;

        public PopularViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
