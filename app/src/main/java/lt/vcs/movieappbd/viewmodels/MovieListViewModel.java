package lt.vcs.movieappbd.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import lt.vcs.movieappbd.models.MovieModel;

public class MovieListViewModel extends ViewModel {
    private MutableLiveData<List<MovieModel>> movies = new MutableLiveData<>();

    public MovieListViewModel() {

    }

    public LiveData<List<MovieModel>> getMovies(){
        return movies;
    }
}
