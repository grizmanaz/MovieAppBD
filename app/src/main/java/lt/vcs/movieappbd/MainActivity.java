package lt.vcs.movieappbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lt.vcs.movieappbd.models.MovieModel;
import lt.vcs.movieappbd.request.Service;
import lt.vcs.movieappbd.response.MovieSearchResponse;
import lt.vcs.movieappbd.utils.Constants;
import lt.vcs.movieappbd.utils.MovieApi;
import lt.vcs.movieappbd.viewmodels.MovieListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn;

    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ObserveAnyChange();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMovieApi("Fast", 1);
            }
        });
    }

    private void ObserveAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null){
                    for(MovieModel movieModel: movieModels){
                        Log.v("Tag", "onChanged: " +movieModel.getTitle());
                    }
                }

            }
        });
    }

    private void searchMovieApi(String query, int pageNumber){
        movieListViewModel.searchMovieApi(query, pageNumber);
    }

//    private void GetRetrofitResponse() {
//        MovieApi movieApi = Service.getMovieApi();
//
//        Call<MovieSearchResponse> responseCall = movieApi
//                .searchMovie(
//                        Constants.API_KEY,
//                        "shrek",
//                        1);
//
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                if (response.code() == 200) {
//                    Log.v("Tag", "response " + response.body().toString());
//
//                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
//
//                    for (MovieModel movie : movies) {
//                        Log.v("Tag", "title " + movie.getTitle());
//                    }
//                } else {
//                    try {
//                        Log.v("Tag", "Error" + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    private void GetRetrofitResponseAccordingToId() {
//
//        MovieApi movieApi = Service.getMovieApi();
//        Call<MovieModel> responseCall = movieApi.
//                getMovie(550,
//                        Constants.API_KEY);
//        responseCall.enqueue((new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                if (response.code() == 200){
//                    MovieModel movie = response.body();
//                    Log.v("Tag", "The Response " + movie.getTitle());
//                }
//                else
//                {
//                    try {
//                        Log.v("Try", "Error" +response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        }));
//    }

}