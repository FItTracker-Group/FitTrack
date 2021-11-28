package com.codepath.fittrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class YogaDetail extends AppCompatActivity {
    public static final String TAG = "YogaDetail";
    private RecyclerView rvYogaExercises;
    protected VideoAdapter adapter;
    protected List<Video> videos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_detail);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        rvYogaExercises = findViewById(R.id.rvYogaExercises);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.ytVideo);

        videos = new ArrayList<>();
        //adapter = new VideoAdapter(this, videos);
        //rvWeightExercises.setAdapter(adapter);
        rvYogaExercises.setLayoutManager(new LinearLayoutManager(this));
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
                    if(video.getVideoCategory().equals("yoga") && video.getVideoDifficulty().equals("easy")){
                        easyList.add(video);
                    }
                    if(video.getVideoCategory().equals("yoga") && video.getVideoDifficulty().equals("medium")){
                        mediumList.add(video);
                    }
                    if(video.getVideoCategory().equals("yoga") && video.getVideoDifficulty().equals("hard")){
                        hardList.add(video);
                    }
                    Log.i(TAG, "Title: " + video.getVideoTitle() + ", difficulty: " + video.getVideoDifficulty() + ", muscleType: " + video.getMuscleType() + ", videoID: " + video.getVideoId());
                }
                videos.addAll(easyList);
                videos.addAll(mediumList);
                videos.addAll(hardList);

                adapter = new VideoAdapter(getApplicationContext(), videos, getLifecycle());
                rvYogaExercises.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}