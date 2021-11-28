package com.codepath.fittrack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.fittrack.fragments.HomeFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observer;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> implements Filterable {
    public static final String TAG = "VideoAdapter";
    private Context context;
    protected List<Video> videos;
    protected List<Video> videosFull;
    private Lifecycle lifecycle;

    public VideoAdapter(Context context, List<Video> videos, Lifecycle lifecycle) {
        this.context = context;
        this.videos = videos;
        this.lifecycle = lifecycle;
        videosFull = new ArrayList<>(videos); //create an independent copy
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View videoView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        YouTubePlayerView youTubePlayerView = videoView.findViewById(R.id.ytVideo);
        lifecycle.addObserver(youTubePlayerView);
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

    /*public void addAll(List<Video> list){
        videos.addAll(list);
        notifyDataSetChanged();
    }*/

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Video> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
            {
                filteredList.addAll(videosFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim(); //removes empty spaces and case sensitive searches

                for(Video item : videosFull){
                    if(item.getVideoDifficulty().toLowerCase().contains(filterPattern) || item.getVideoTitle().toLowerCase().contains(filterPattern) || item.getMuscleType().toLowerCase().contains(filterPattern)
                    || item.getVideoId().toLowerCase().contains(filterPattern))
                        filteredList.add(item);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            videos.clear();
            videos.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();
        }
    };

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
            //lifecycle.addObserver(youTubePlayerView);
        }

        public void bind(Video video) {
            tvTitle.setText(video.getVideoTitle());
            difficulty.setText(video.getVideoDifficulty());
            String videoId = video.getVideoId();

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    //String videoId = video.getVideoId();
                    youTubePlayer.cueVideo(videoId, 0f);
                }
            });


            if(video.getVideoCategory().equals("weight") || video.getVideoCategory().equals("stretch"))
                muscleType.setText(video.getMuscleType());
        }


    }
}
