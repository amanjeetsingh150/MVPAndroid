package com.developers.televize.data.model.VideoResultModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Amanjeet Singh on 21/8/17.
 */

public class VideoResults {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<VideoIdResult> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VideoIdResult> getResults() {
        return results;
    }

    public void setResults(List<VideoIdResult> results) {
        this.results = results;
    }

}
