package lt.vcs.movieappbd.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lt.vcs.movieappbd.models.MovieModel;
import lt.vcs.movieappbd.request.MovieApiClient;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance() {
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;

    }

    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();}
}
