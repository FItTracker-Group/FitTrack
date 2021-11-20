package com.codepath.fittrack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;
import java.util.Observer;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    public static final String TAG = "VideoAdapter";
    private Context context;
    private List<Video> videos;

    public VideoAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View videoView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoAdapter.ViewHolder(videoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.bind(video);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void addAll(List<Video> list){
        videos.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private YouTubePlayerView youTubePlayerView;
        private TextView tvTitle;
        private TextView difficulty;
        private TextView muscleType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            difficulty = itemView.findViewById(R.id.tvDifficulty);
            muscleType = itemView.findViewById(R.id.tvMuscleType);
            youTubePlayerView = itemView.findViewById(R.id.ytVideo);

        }

        public void bind(Video video) {
            tvTitle.setText(video.getVideoTitle());
            difficulty.setText(video.getVideoDifficulty());
            muscleType.setText(video.getMuscleType());
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    //super.onReady(youTubePlayer);
                    String videoId = video.getVideoId();
                    youTubePlayer.loadVideo(videoId, 0);
                    youTubePlayer.pause();
                }
            });
        }


    }
}
