package lt.vcs.movieappbd.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import lt.vcs.movieappbd.models.MovieModel;
import lt.vcs.movieappbd.repositories.MovieRepository;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){ return movieRepository.getMovies();}

    public void searchMovieApi(String query, int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }

    public void searchNextPage(){
        movieRepository.searchNextPage();
    }
}
