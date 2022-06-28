package lt.vcs.movieappbd.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lt.vcs.movieappbd.models.MovieModel;

public class MovieRepository {

    private static MovieRepository instance;

    private MutableLiveData<List<MovieModel>> movies = new MutableLiveData<>();

    public static MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;

    }

    private MovieRepository(){
        movies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){return movies;}
}
