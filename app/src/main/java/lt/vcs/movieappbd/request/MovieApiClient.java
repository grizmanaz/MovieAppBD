package lt.vcs.movieappbd.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lt.vcs.movieappbd.AppExecutors;
import lt.vcs.movieappbd.models.MovieModel;
import lt.vcs.movieappbd.response.MovieSearchResponse;
import lt.vcs.movieappbd.utils.Constants;
import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private MutableLiveData<List<MovieModel>> movies;

    private static MovieApiClient instance;

    //global request
    private RetrieveMoviesRunnable retrieveMoviesRunnable;


    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        movies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movies;
    }

    public void searchMoviesApi(String query, int pageNumber) {

        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancel retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    //retrieve data from restAPI by runnable class
    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            // Getting the response objects
            try {
                Response response = getMovies(query, pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                    if (pageNumber == 1) {
                        movies.postValue(list);
                    } else {
                        List<MovieModel> currentMovies = movies.getValue();
                        currentMovies.addAll(list);
                        movies.postValue(currentMovies);
                    }
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error" + error);
                    movies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //search method query
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return Service.getMovieApi().searchMovie(
                    Constants.API_KEY,
                    query,
                    pageNumber
            );

        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }


}
