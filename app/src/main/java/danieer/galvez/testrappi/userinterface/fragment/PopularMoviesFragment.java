package danieer.galvez.testrappi.userinterface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.network.network.utils.NetworkUtils;
import danieer.galvez.testrappi.userinterface.activity.MovieDetailsActivity;
import danieer.galvez.testrappi.userinterface.presenter.adapter.MoviesAdapter;
import danieer.galvez.testrappi.userinterface.presenter.interfaces.OnQueryChangedListener;
import danieer.galvez.testrappi.userinterface.presenter.interfaces.onItemClick;
import danieer.galvez.testrappi.viewmodel.MainViewModel;

public class PopularMoviesFragment extends Fragment implements onItemClick {

    private MainViewModel mainViewModel;
    private RecyclerView movieList;
    private MoviesAdapter moviesAdapter;
    private int pageNo = 1;


    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;


    public static PopularMoviesFragment newInstance() {
        return new PopularMoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popular_movies, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieList = view.findViewById(R.id.movie_list);
        initializeElements();
    }

    private void initializeElements() {
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setOnItemClick(this);
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
                    mainViewModel.noPagePopular.postValue(pageNo);
                    loading = true;
                }
            }
        });
        movieList.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        movieList.getViewTreeObserver().removeOnPreDrawListener(this);

                        for (int i = 0; i < movieList.getChildCount(); i++) {
                            View v = movieList.getChildAt(i);
                            v.setAlpha(0.0f);
                            v.animate().alpha(1.0f)
                                    .setDuration(300)
                                    .setStartDelay(i * 50)
                                    .start();
                        }

                        return true;
                    }
                });

        setupViewModel();
    }

    private void setupViewModel() {
        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);

        mainViewModel.noPagePopular.postValue(pageNo);

        mainViewModel.noPagePopular.observe(this, integer -> mainViewModel.getMorePupularMovies(integer));

        mainViewModel.getPopularMovies().observe(this, movies -> {

            moviesAdapter.addMovies(movies);
            moviesAdapter.notifyDataSetChanged();
            pageNo++;
        });

    }

    @Override
    public void onItemClick(int movieId) {

        Intent detailsIntent = new Intent(getActivity(), MovieDetailsActivity.class);
        detailsIntent.putExtra(NetworkUtils.MOVIE_ID, movieId);
        getActivity().startActivity(detailsIntent);
    }

    public OnQueryChangedListener onQueryChangedListener = query -> Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();



}
