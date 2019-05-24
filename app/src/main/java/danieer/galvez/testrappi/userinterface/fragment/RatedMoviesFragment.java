package danieer.galvez.testrappi.userinterface.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.userinterface.presenter.adapter.MoviesAdapter;
import danieer.galvez.testrappi.viewmodel.MainViewModel;

public class RatedMoviesFragment extends Fragment {

    private MainViewModel mainViewModel;
    private RecyclerView movieList;
    private MoviesAdapter moviesAdapter;
    private int pageNo = 1;


    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public static RatedMoviesFragment newInstance() {
        return new RatedMoviesFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rated_movies, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieList = view.findViewById(R.id.movie_list);
        initializeElements();
    }

    private void initializeElements() {
        moviesAdapter = new MoviesAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        movieList.setLayoutManager(gridLayoutManager);
        movieList.setItemAnimator(new DefaultItemAnimator());
        movieList.setAdapter(moviesAdapter);
        movieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = movieList.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();

                // Handling the infinite scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    mainViewModel.noPageTopRated    .postValue(pageNo);
                    loading = true;
                }
            }
        });
        setupViewModel();
    }

    private void setupViewModel() {
        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);

        mainViewModel.noPageTopRated.postValue(pageNo);

        mainViewModel.noPageTopRated.observe(this, integer -> mainViewModel.getMoreRatedMovies(integer));

        mainViewModel.getTopRatedMovies().observe(this, movies -> {

            moviesAdapter.addMovies(movies);
            moviesAdapter.notifyDataSetChanged();
            pageNo++;
        });

    }

}
