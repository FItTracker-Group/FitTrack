package com.codepath.fittrack;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.fittrack.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observer;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> implements Filterable {
    public static final String TAG = "VideoAdapter";
    private Context context;
    protected List<Video> videos;
    protected List<Video> videosFull;


    public VideoAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
        videosFull = new ArrayList<>(videos); //create an independent copy
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View videoView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        WebView webView = videoView.findViewById(R.id.ytVideo);
        webView.setBackgroundColor(Color.parseColor("#000000"));
        return new VideoAdapter.ViewHolder(videoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.videoWeb.loadData(videos.get(position).getVideoUrl(),"text/html","utf-8");
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
        WebView videoWeb;
        private TextView tvTitle;
        private TextView difficulty;
        private TextView muscleType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            difficulty = itemView.findViewById(R.id.tvDifficulty);
            muscleType = itemView.findViewById(R.id.tvMuscleType);
            videoWeb = (WebView) itemView.findViewById(R.id.ytVideo);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient());

        }

        public void bind(Video video) {
            tvTitle.setText(video.getVideoTitle());
            difficulty.setText(video.getVideoDifficulty());
            String videoId = video.getVideoId();



            if(video.getVideoCategory().equals("weight") || video.getVideoCategory().equals("stretch"))
                muscleType.setText(video.getMuscleType());
        }


    }
}
