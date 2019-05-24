package danieer.galvez.testrappi.repository;

import androidx.lifecycle.MutableLiveData;

import danieer.galvez.testrappi.model.MovieDetailResponse;
import danieer.galvez.testrappi.network.client.ApiClient;
import danieer.galvez.testrappi.network.network.utils.NetworkUtils;
import danieer.galvez.testrappi.network.service.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieRepository {

    private ApiService apiService;
    private MutableLiveData<MovieDetailResponse> movieDetailResponseMutableLiveData;

    public DetailMovieRepository() {
        apiService = ApiClient.createService(ApiService.class);
        movieDetailResponseMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<MovieDetailResponse> getMovieDetails(int movieId) {

        apiService.getMovieDetails(movieId, NetworkUtils.API_KEY, "videos").enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                if (response.body() != null) {
                    movieDetailResponseMutableLiveData.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                    t.printStackTrace();
            }
        });
        return movieDetailResponseMutableLiveData;
    }


}
