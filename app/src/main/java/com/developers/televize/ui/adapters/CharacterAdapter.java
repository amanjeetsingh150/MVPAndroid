package com.developers.televize.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.R;
import com.developers.televize.data.model.CharacterModel.Cast;
import com.developers.televize.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amanjeet Singh on 22/8/17.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private Context context;
    private List<Cast> castList;

    public CharacterAdapter(Context context, List<Cast> castList) {
        this.context = context;
        this.castList = castList;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.char_list, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        Picasso.with(context).load(Constants.BASE_URL_IMAGES+castList.get(position).getProfilePath()).into(holder.tvShow);
        holder.tvTitle.setText(castList.get(position).getName()+" as "+castList.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview_tv_show)
        ImageView tvShow;
        @BindView(R.id.textview_tv_name)
        TextView tvTitle;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
