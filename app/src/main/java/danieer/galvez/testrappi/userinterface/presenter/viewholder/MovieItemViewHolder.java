package danieer.galvez.testrappi.userinterface.presenter.viewholder;

import android.graphics.drawable.Drawable;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.application.TestRappiApplication;
import danieer.galvez.testrappi.model.Movie;
import danieer.galvez.testrappi.network.network.utils.NetworkUtils;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView movieImage;
    private ProgressBar progressBar;
    private TextView movieTitle, releaseDate, movieRate;


    public MovieItemViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImage = itemView.findViewById(R.id.movie_image);
        progressBar = itemView.findViewById(R.id.progress_load_image);
        movieTitle = itemView.findViewById(R.id.movie_title);
        releaseDate = itemView.findViewById(R.id.release_date);
        movieRate = itemView.findViewById(R.id.movie_rate);
    }

    public void bind(final Movie movie) {

        movieTitle.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        movieRate.setText(movie.getVoteAverage().toString());
        Glide.with(TestRappiApplication.getAppContext())
                .load(NetworkUtils.IMAGE_BASE_URL + movie.getBackdropPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.place_holder).error(R.drawable.place_holder))
                .into(movieImage);

    }
}
