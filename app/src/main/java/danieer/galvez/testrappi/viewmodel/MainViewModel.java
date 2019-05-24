package danieer.galvez.testrappi.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import danieer.galvez.testrappi.model.Movie;
import danieer.galvez.testrappi.repository.MoviesRepository;

public class MainViewModel extends ViewModel {
    private MoviesRepository moviesRepository;

    public MutableLiveData<Integer> noPageUpcoming = new MutableLiveData<>();

    public MutableLiveData<Integer> noPagePopular = new MutableLiveData<>();

    public MutableLiveData<Integer> noPageTopRated = new MutableLiveData<>();


    public MainViewModel() {
        moviesRepository = new MoviesRepository();
    }

    public MutableLiveData<List<Movie>> getUpcomingMovies() {
        return moviesRepository.getUpcomingMovies();
    }

    public void getMoreData(int noPage) {
        moviesRepository.updateUpcoming(noPage);
    }

    public MutableLiveData<List<Movie>> getTopRatedMovies() {
        return moviesRepository.getTopRatedMoviesLiveData();
    }

    public void getMoreRatedMovies(int noPage) {
        moviesRepository.updateTopRated(noPage);
    }

    public MutableLiveData<List<Movie>> getPopularMovies() {
        return moviesRepository.getPopularMoviesLiveData();
    }

    public void getMorePupularMovies(int noPage) {
        moviesRepository.updatePopular(noPage);
    }


}
