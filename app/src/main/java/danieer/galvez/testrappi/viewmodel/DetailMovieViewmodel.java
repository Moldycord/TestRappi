package danieer.galvez.testrappi.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import danieer.galvez.testrappi.model.MovieDetailResponse;
import danieer.galvez.testrappi.repository.DetailMovieRepository;

public class DetailMovieViewmodel extends ViewModel {

    DetailMovieRepository detailMovieRepository;

    public DetailMovieViewmodel() {
        detailMovieRepository = new DetailMovieRepository();
    }

    public MutableLiveData<MovieDetailResponse> getMovieDetails(int movieId) {
        return detailMovieRepository.getMovieDetails(movieId);
    }
}
