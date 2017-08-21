package com.developers.televize.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.R;
import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.ui.fragments.PopularTv.PopularTvView;
import com.developers.televize.util.Constants;
import com.developers.televize.util.ViewCallBack;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public class PopularTvShowsAdapter extends RecyclerView.Adapter<PopularTvShowsAdapter.PopularViewHolder> implements ViewCallBack {

    private Context context;
    private List<Result> results;
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
                popularTvView.launchDetailActivity(results.get(position), holder.tvPoster, holder.tvTitle);
            }
        });
        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popularTvView.launchShareActivity("LOOK at " + results.get(position).getName()
                        + " with " + results.get(position).getPopularity() + " popularity.");
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
        @BindView(R.id.share_button)
        ImageButton shareButton;

        public PopularViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
