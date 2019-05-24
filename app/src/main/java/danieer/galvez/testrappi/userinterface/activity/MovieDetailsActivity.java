package danieer.galvez.testrappi.userinterface.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.application.TestRappiApplication;
import danieer.galvez.testrappi.network.network.utils.NetworkUtils;
import danieer.galvez.testrappi.userinterface.presenter.adapter.VideoAdapter;
import danieer.galvez.testrappi.viewmodel.DetailMovieViewmodel;

public class MovieDetailsActivity extends AppCompatActivity {

    private int idMovie;
    private DetailMovieViewmodel detailMovieViewmodel;
    private VideoAdapter videoAdapter;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private TextView overView, releaseDate, voteAverage;
    private CollapsingToolbarLayout collapseToolbar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        idMovie = getIntent().getExtras().getInt(NetworkUtils.MOVIE_ID, 0);
        initializeViews();

    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.video_list);
        imageView = findViewById(R.id.movie_image);
        toolbar = findViewById(R.id.MyToolbar);
        overView = findViewById(R.id.movie_overview);
        releaseDate = findViewById(R.id.release_date);
        voteAverage = findViewById(R.id.vote_average);
        collapseToolbar = findViewById(R.id.collapse_toolbar);
        initializeElements();
    }

    private void initializeElements() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener((View v) -> onBackPressed());

        videoAdapter = new VideoAdapter();
        videoAdapter.setLifecycle(getLifecycle());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(videoAdapter);

        setupViewModel();
    }

    private void setupViewModel() {
        detailMovieViewmodel = ViewModelProviders.of(this).get(DetailMovieViewmodel.class);

        detailMovieViewmodel.getMovieDetails(idMovie).observe(this, movieDetailResponse -> {

            overView.setText(movieDetailResponse.getOverview());
            releaseDate.setText(movieDetailResponse.getReleaseDate());
            voteAverage.setText(movieDetailResponse.getVoteAverage().toString());
            collapseToolbar.setTitle(movieDetailResponse.getTitle());
            if (movieDetailResponse.getVideo() != null) {
                videoAdapter.setVideoResultList(movieDetailResponse.getVideos().getResults());
                videoAdapter.notifyDataSetChanged();
            }


            Glide.with(TestRappiApplication.getAppContext())
                    .load(NetworkUtils.IMAGE_BASE_URL + movieDetailResponse.getPosterPath())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            e.printStackTrace();

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            return false;
                        }
                    })
                    .apply(new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder))
                    .into(imageView);
        });
    }


}
