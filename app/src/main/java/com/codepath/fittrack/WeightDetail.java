package com.codepath.fittrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class WeightDetail extends AppCompatActivity {

    public static final String TAG = "WeightDetail";
    private RecyclerView rvWeightExercises;
    protected VideoAdapter adapter;
    protected List<Video> videos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_detail);
        getSupportActionBar().setTitle("                    Weight Training");

        rvWeightExercises = findViewById(R.id.rvWeightExercises);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.ytVideo);

        videos = new ArrayList<>();
        adapter = new VideoAdapter(this, videos);
        rvWeightExercises.setAdapter(adapter);
        rvWeightExercises.setLayoutManager(new LinearLayoutManager(this));
        queryVideos();
    }

    protected void queryVideos() {
        // Specify which class to query
        ParseQuery<Video> query = ParseQuery.getQuery(Video.class);
        query.addDescendingOrder(Video.VIDEO_DIFFICULTY);
        query.findInBackground(new FindCallback<Video>() {
            @Override
            public void done(List<Video> list, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting Video", e);
                }
                List<Video> easyList = new ArrayList<>();
                List<Video> mediumList = new ArrayList<>();
                List<Video> hardList = new ArrayList<>();
                for(Video video : list){
                    if(video.getVideoCategory().equals("weight") && video.getVideoDifficulty().equals("easy")){
                        easyList.add(video);
                    }
                    if(video.getVideoCategory().equals("weight") && video.getVideoDifficulty().equals("medium")){
                        mediumList.add(video);
                    }
                    if(video.getVideoCategory().equals("weight") && video.getVideoDifficulty().equals("hard")){
                        hardList.add(video);
                    }
                        Log.i(TAG, "Title: " + video.getVideoTitle() + ", difficulty: " + video.getVideoDifficulty() + ", muscleType: " + video.getMuscleType() + ", videoID: " + video.getVideoId());
                }
                videos.addAll(easyList);
                videos.addAll(mediumList);
                videos.addAll(hardList);
                adapter.notifyDataSetChanged();
            }
        });
    }
}