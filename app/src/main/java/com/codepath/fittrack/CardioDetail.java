package com.codepath.fittrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class CardioDetail extends AppCompatActivity {

    public static final String TAG = "CardioDetail";
    private RecyclerView rvCardioExercises;
    protected VideoAdapter adapter;
    protected List<Video> videos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_detail);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        rvCardioExercises = findViewById(R.id.rvCardioExercises);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.ytVideo);

        videos = new ArrayList<>();
        adapter = new VideoAdapter(this, videos, getLifecycle());
        rvCardioExercises.setAdapter(adapter);
        rvCardioExercises.setLayoutManager(new LinearLayoutManager(this));
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
                    if(video.getVideoCategory().equals("cardio") && video.getVideoDifficulty().equals("easy")){
                        easyList.add(video);
                    }
                    if(video.getVideoCategory().equals("cardio") && video.getVideoDifficulty().equals("medium")){
                        mediumList.add(video);
                    }
                    if(video.getVideoCategory().equals("cardio") && video.getVideoDifficulty().equals("hard")){
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