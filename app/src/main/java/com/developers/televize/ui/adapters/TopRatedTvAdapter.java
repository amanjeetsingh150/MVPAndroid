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
import com.developers.televize.data.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.ui.fragments.PopularTv.PopularTvView;
import com.developers.televize.ui.fragments.TopRatedTv.TopRatedTvView;
import com.developers.televize.util.Constants;
import com.developers.televize.util.ViewCallBack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amanjeet Singh on 22/8/17.
 */

public class TopRatedTvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ViewCallBack {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private Context context;
    private List<TopRatedResult> topRatedResults;
    private TopRatedTvView topRatedView;

    private boolean isLoadingItemAdded =false;

    public TopRatedTvAdapter(Context context) {
        this.context = context;
        this.topRatedResults = new ArrayList<>();
    }

    public void addData(List<TopRatedResult> topRatedResults) {
        this.topRatedResults.addAll(topRatedResults);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = new TopRatedRecyclerViewHolder(inflater.inflate(R.layout.top_list_row, parent, false));
                break;
            case LOADING:
                viewHolder = new LoadingViewHolder(inflater.inflate(R.layout.item_loading, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case ITEM:
                Picasso.with(context)
                        .load(Constants.BASE_URL_IMAGES + topRatedResults.get(position).getBackdropPath())
                        .into(((TopRatedRecyclerViewHolder)holder).topRatedBanner);
                ((TopRatedRecyclerViewHolder)holder).topRatedTitle.setText(topRatedResults.get(position).getName());
                ((TopRatedRecyclerViewHolder)holder).topRatedCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        topRatedView.launchDetailActivity(topRatedResults.get(position),
                                ((TopRatedRecyclerViewHolder)holder).topRatedBanner,
                                ((TopRatedRecyclerViewHolder)holder).topRatedTitle);
                    }
                });
                ((TopRatedRecyclerViewHolder)holder).shareRatedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        topRatedView.launchShareActivity("LOOK at " +
                                topRatedResults.get(position).getName() + " with " +
                                topRatedResults.get(position).getPopularity() + " popularity.");
                    }
                });

                break;
            case LOADING:
                // do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {
        return topRatedResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == topRatedResults.size() - 1 && isLoadingItemAdded) ? LOADING : ITEM;
    }

    @Override
    public void setPopularTvView(PopularTvView view) {

    }

    @Override
    public void setTopRatedTvView(TopRatedTvView topRatedTvView) {
        this.topRatedView = topRatedTvView;
    }

    public void addLoadingFooter() {
        isLoadingItemAdded = true;
        TopRatedResult result = new TopRatedResult();
        addItem(result);
    }

    public void addItem(TopRatedResult resultItem) {
        topRatedResults.add(resultItem);
        notifyItemInserted(topRatedResults.size() - 1);
    }

    public void removeLoadingFooter() {
        if (isLoadingItemAdded) {
            isLoadingItemAdded = false;

            int position = topRatedResults.size() - 1;
            TopRatedResult resultItem = topRatedResults.get(position);

            if (resultItem != null) {
                topRatedResults.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public class TopRatedRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.top_rated_image_view)
        ImageView topRatedBanner;
        @BindView(R.id.top_rated_title_textview)
        TextView topRatedTitle;
        @BindView(R.id.top_rated_card_view)
        CardView topRatedCard;
        @BindView(R.id.share_rated_btn)
        ImageButton shareRatedButton;

        public TopRatedRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * ViewHolder item for loading spinner
     */
    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
