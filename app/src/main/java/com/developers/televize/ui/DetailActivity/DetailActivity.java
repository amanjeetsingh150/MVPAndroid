package com.developers.televize.ui.DetailActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.televize.InitApplication;
import com.developers.televize.R;
import com.developers.televize.Util.Constants;
import com.developers.televize.adapter.CharacterAdapter;
import com.developers.televize.model.CharacterModel.Cast;
import com.developers.televize.model.PopularTvModel.Result;
import com.developers.televize.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.model.TvShowDetailModel.Genre;
import com.developers.televize.model.TvShowDetailModel.TvShowDetailResult;
import com.developers.televize.model.VideoResultModel.VideoIdResult;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private static final String TAG = DetailActivity.class.getSimpleName();
    @Inject
    DetailPresenter detailPresenter;
    @BindView(R.id.tv_image_view)
    ImageView popularBannerImage;
    @BindView(R.id.image_view_popular_poster)
    ImageView popularPoster;
    @BindView(R.id.tv_title_textview)
    TextView popularTitle;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.text_vote_average)
    TextView voteAverage;
    @BindView(R.id.genre_text)
    TextView genreNames;
    @BindView(R.id.overview)
    TextView overviewTextView;
    @BindView(R.id.crew_recycler_view)
    RecyclerView crewRecyclerView;
    @BindView(R.id.release_date_text)
    TextView releaseDate;
    @BindView(R.id.button_video)
    ImageView vidButton;
    private CharacterAdapter characterAdapter;
    private Gson gson;
    private String tvJsonObject;
    private Result resultObj;
    private TopRatedResult topRatedResultObj;
    private List<Genre> genreList;
    private String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ((InitApplication) getApplication()).getAppComponent().inject(this);
        detailPresenter.setDetailView(this);
        gson = new Gson();
        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("KEY");
        if (key.equals(Constants.POPULAR_KEY)) {
            tvJsonObject = getIntent().getStringExtra(Constants.POPULAR_TV_KEY);
            resultObj = gson.fromJson(tvJsonObject, Result.class);
            detailPresenter.fetchDetails(resultObj);
            vidButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detailPresenter.fetchVideos(resultObj.getId());
                }
            });
        }
        if (key.equals(Constants.RATED_KEY)) {
            tvJsonObject = getIntent().getStringExtra(Constants.RATED_TV_KEY);
            topRatedResultObj = gson.fromJson(tvJsonObject, TopRatedResult.class);
            detailPresenter.fetchTopRatedResult(topRatedResultObj);
            vidButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detailPresenter.fetchVideos(topRatedResultObj.getId());
                }
            });
        }


    }

    @Override
    public void showPopularDetails(Result result) {
        detailPresenter.getDetailForShow(result.getId());
        detailPresenter.getChar(result.getId());
        Picasso.with(DetailActivity.this)
                .load(Constants.BASE_URL_IMAGES + result.getBackdropPath())
                .into(popularBannerImage);
        Picasso.with(DetailActivity.this)
                .load(Constants.BASE_URL_IMAGES + result.getPosterPath())
                .into(popularPoster, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        popularTitle.setText(result.getName());
        voteAverage.setText("Rating: " + result.getVoteAverage() + "/10");
        overviewTextView.setText("Overview: " + result.getOverview());
        releaseDate.setText("Release Date: " + result.getFirstAirDate());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(DetailActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetailOfShows(TvShowDetailResult tvShowDetailResult) {
        genreList = tvShowDetailResult.getGenres();
        for (Genre genre : genreList) {
            if (genreList.indexOf(genre) < genreList.size()) {
                genreNames.append(" " + genre.getName() + " |");
            }
        }
    }

    @Override
    public void showCharacters(List<Cast> castList) {
        characterAdapter = new CharacterAdapter(DetailActivity.this, castList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        crewRecyclerView.setLayoutManager(layoutManager);
        crewRecyclerView.setAdapter(characterAdapter);
    }

    @Override
    public void showTopRatedDetails(TopRatedResult result) {
        detailPresenter.getDetailForShow(result.getId());
        detailPresenter.getChar(result.getId());
        Picasso.with(DetailActivity.this)
                .load(Constants.BASE_URL_IMAGES + result.getBackdropPath())
                .into(popularBannerImage);
        popularTitle.setText(result.getName());
        Picasso.with(DetailActivity.this)
                .load(Constants.BASE_URL_IMAGES + result.getPosterPath())
                .into(popularPoster, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        voteAverage.setText("Rating: " + result.getVoteAverage() + "/10");
        overviewTextView.setText("Overview: " + result.getOverview());
        releaseDate.setText("Release Date: " + result.getFirstAirDate());
    }

    @Override
    public void launchYoutubeActivity(List<VideoIdResult> videoIdResults) {
        String address=null;
        for(VideoIdResult v:videoIdResults){
            address=Constants.YOUTUBE_BASE_URL+v.getKey();
            break;
        }
        if(address!=null){
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(address));
            startActivity(intent);
        }
    }

}
