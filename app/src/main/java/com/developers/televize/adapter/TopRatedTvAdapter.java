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
import com.developers.televize.Util.Constants;
import com.developers.televize.Util.ViewCallBack;
import com.developers.televize.fragments.PopularFragment.PopularTvView;
import com.developers.televize.fragments.TopRatedTV.TopRatedView;
import com.developers.televize.model.TopRatedTvModel.TopRatedResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amanjeet Singh on 19/7/17.
 */

public class TopRatedTvAdapter extends RecyclerView.Adapter<TopRatedTvAdapter.TopRatedRecyclerViewHolder> implements ViewCallBack {

    private Context context;
    private List<TopRatedResult> topRatedResults;
    private TopRatedView topRatedView;

    public TopRatedTvAdapter(Context context, List<TopRatedResult> topRatedResults) {
        this.context = context;
        this.topRatedResults = topRatedResults;
    }

    @Override
    public TopRatedRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.top_list_row, parent, false);
        return new TopRatedRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TopRatedRecyclerViewHolder holder, final int position) {
        Picasso.with(context)
                .load(Constants.BASE_URL_IMAGES + topRatedResults.get(position).getBackdropPath())
                .into(holder.topRatedBanner);
        holder.topRatedTitle.setText(topRatedResults.get(position).getName());
        holder.topRatedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRatedView.launchDetailActivity(topRatedResults.get(position),holder.topRatedBanner,holder.topRatedTitle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topRatedResults.size();
    }

    @Override
    public void setPopularTvView(PopularTvView view) {

    }

    @Override
    public void setTopRatedTvView(TopRatedView topRatedTvView) {
        this.topRatedView = topRatedTvView;
    }

    public class TopRatedRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.top_rated_image_view)
        ImageView topRatedBanner;
        @BindView(R.id.top_rated_title_textview)
        TextView topRatedTitle;
        @BindView(R.id.top_rated_card_view)
        CardView topRatedCard;

        public TopRatedRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
