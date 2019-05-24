package danieer.galvez.testrappi.network.service;

import danieer.galvez.testrappi.model.MovieDetailResponse;
import danieer.galvez.testrappi.model.MovieResponse;
import danieer.galvez.testrappi.network.network.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET(NetworkUtils.TOP_RATED_MOVIES_ENDPOINT)
    Call<MovieResponse> getTopRatedMovies(@Query(NetworkUtils.API_KEY_QUERY) String apiKey,
                                          @Query(NetworkUtils.PAGE_NO) int pageNo);

    @GET(NetworkUtils.UPCOMING_MOVIES_ENDPOINT)
    Call<MovieResponse> getUpcomingMovies(@Query(NetworkUtils.API_KEY_QUERY) String apiKey,
                                          @Query(NetworkUtils.PAGE_NO) int pageNo);

    @GET(NetworkUtils.POPULAR_MOVIES_ENDPOINT)
    Call<MovieResponse> getPopularMovies(@Query(NetworkUtils.API_KEY_QUERY) String apiKey,
                                         @Query(NetworkUtils.PAGE_NO) int pageNo);

    @GET(NetworkUtils.MOVIE_DETAILS_ENDPOINT)
    Call<MovieDetailResponse> getMovieDetails(@Path(NetworkUtils.MOVIE_ID) int movieId,
                                              @Query(NetworkUtils.API_KEY_QUERY) String apiKey,
                                              @Query(NetworkUtils.APPEND_TO_RESPONSE) String videos);
}
