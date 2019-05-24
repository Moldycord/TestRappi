package danieer.galvez.testrappi.userinterface.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.model.Movie;
import danieer.galvez.testrappi.userinterface.presenter.viewholder.MovieItemViewHolder;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private danieer.galvez.testrappi.userinterface.presenter.interfaces.onItemClick onItemClick;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_card, null);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieItemViewHolder) holder).bind(movies.get(position));
        if (onItemClick != null)
            holder.itemView.setOnClickListener(view -> onItemClick.onItemClick(movies.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        if (movies != null)
            return movies.size();
        else
            return 0;
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
    }

    public void setOnItemClick(danieer.galvez.testrappi.userinterface.presenter.interfaces.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
