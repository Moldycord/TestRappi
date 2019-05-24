package danieer.galvez.testrappi.userinterface.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.model.VideoResult;
import danieer.galvez.testrappi.userinterface.presenter.viewholder.VideoItemViewHolder;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<VideoResult> videoResultList;
    private Lifecycle lifecycle;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, null);
        return new VideoItemViewHolder(view, lifecycle);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((VideoItemViewHolder) holder).bind(videoResultList.get(position));

    }

    @Override
    public int getItemCount() {
        if (videoResultList != null) {
            return videoResultList.size();
        } else
            return 0;
    }

    public void setVideoResultList(List<VideoResult> videoResultList) {
        this.videoResultList = videoResultList;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }
}
