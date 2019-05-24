package danieer.galvez.testrappi.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import danieer.galvez.testrappi.model.Movie;
import danieer.galvez.testrappi.model.MovieResponse;
import danieer.galvez.testrappi.network.client.ApiClient;
import danieer.galvez.testrappi.network.network.utils.NetworkUtils;
import danieer.galvez.testrappi.network.service.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private ApiService apiService;


    private MutableLiveData<List<Movie>> upcomingMoviesLiveData, topRatedMoviesLiveData, popularMoviesLiveData;


    public MoviesRepository() {
        apiService = ApiClient.createService(ApiService.class);
        upcomingMoviesLiveData = new MutableLiveData<>();
        topRatedMoviesLiveData = new MutableLiveData<>();
        popularMoviesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getUpcomingMovies() {
        return upcomingMoviesLiveData;
    }

    public MutableLiveData<List<Movie>> getTopRatedMoviesLiveData() {
        return topRatedMoviesLiveData;
    }

    public MutableLiveData<List<Movie>> getPopularMoviesLiveData() {
        return popularMoviesLiveData;
    }

    public void updateUpcoming(int pageNo) {
        apiService.getUpcomingMovies(NetworkUtils.API_KEY, pageNo).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null && response.body().getResults().size() > 0) {
                    upcomingMoviesLiveData.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void updatePopular(int pageNo) {
        apiService.getPopularMovies(NetworkUtils.API_KEY, pageNo).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null && response.body().getResults().size() > 0) {
                    popularMoviesLiveData.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void updateTopRated(int pageNo) {
        apiService.getTopRatedMovies(NetworkUtils.API_KEY, pageNo).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null && response.body().getResults().size() > 0) {
                    topRatedMoviesLiveData.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


}
