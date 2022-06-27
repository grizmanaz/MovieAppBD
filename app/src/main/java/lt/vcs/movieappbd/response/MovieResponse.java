package lt.vcs.movieappbd.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lt.vcs.movieappbd.models.MovieModel;

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie() {
        return movie;
    }
}
