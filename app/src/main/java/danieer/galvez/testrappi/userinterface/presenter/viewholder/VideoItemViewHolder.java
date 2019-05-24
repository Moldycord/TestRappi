package danieer.galvez.testrappi.userinterface.presenter.viewholder;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.model.VideoResult;

public class VideoItemViewHolder extends RecyclerView.ViewHolder {

    private Lifecycle lifecycle;
    private YouTubePlayerView youTubePlayerView;

    public VideoItemViewHolder(@NonNull View itemView, Lifecycle lifecycle) {
        super(itemView);
        youTubePlayerView = itemView.findViewById(R.id.youtube_player_view);
        this.lifecycle = lifecycle;
    }

    public void bind(final VideoResult videoResult) {

        lifecycle.addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = videoResult.getKey();
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

    }
}
