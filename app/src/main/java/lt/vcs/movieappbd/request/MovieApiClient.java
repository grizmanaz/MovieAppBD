package lt.vcs.movieappbd.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lt.vcs.movieappbd.models.MovieModel;

public class MovieApiClient {

    private MutableLiveData<List<MovieModel>> movies;

    private static MovieApiClient instance;


    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        movies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movies;
    }
}
