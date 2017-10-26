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
import com.developers.televize.ui.fragments.TopRatedTv.TopRatedTvView;
import com.developers.televize.util.Constants;
import com.developers.televize.util.ViewCallBack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public class PopularTvShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ViewCallBack {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private Context context;
    private List<Result> results;
    private PopularTvView popularTvView;

    private boolean isLoadingItemAdded = false;

    public PopularTvShowsAdapter(Context context) {
        this.context = context;
        this.results = new ArrayList<>();
    }

    public void addData(List<Result> results) {
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = new PopularViewHolder(inflater.inflate(R.layout.list_row, parent, false));
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
                Picasso
                    .with(context)
                    .load(Constants.BASE_URL_IMAGES + results.get(position).getBackdropPath())
                    .into(((PopularViewHolder)holder).tvPoster);
                ((PopularViewHolder)holder).tvTitle.setText(results.get(position).getName());
                ((PopularViewHolder)holder).cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popularTvView.launchDetailActivity(results.get(position), ((PopularViewHolder)holder).tvPoster, ((PopularViewHolder)holder).tvTitle);
                    }
                });
                ((PopularViewHolder)holder).shareButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popularTvView.launchShareActivity("LOOK at " + results.get(position).getName()
                                + " with " + results.get(position).getPopularity() + " popularity.");
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
        return results.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == results.size() - 1 && isLoadingItemAdded) ? LOADING : ITEM;
    }

    @Override
    public void setPopularTvView(PopularTvView popularTvView) {
        this.popularTvView = popularTvView;
    }

    @Override
    public void setTopRatedTvView(TopRatedTvView view) {

    }

    public void addLoadingFooter() {
        isLoadingItemAdded = true;
        Result result = new Result();
        addItem(result);
    }

    public void addItem(Result resultItem) {
        results.add(resultItem);
        notifyItemInserted(results.size() - 1);
    }

    public void removeLoadingFooter() {
        if (isLoadingItemAdded) {
            isLoadingItemAdded = false;

            int position = results.size() - 1;
            Result resultItem = results.get(position);

            if (resultItem != null) {
                results.remove(position);
                notifyItemRemoved(position);
            }
        }
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

    /**
     * ViewHolder item for loading spinner
     */
    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
