package com.codepath.fittrack;

import androidx.annotation.NonNull;
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
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CardioDetail extends AppCompatActivity {

    public static final String TAG = "CardioDetail";
    private RecyclerView rvCardioExercises;
    protected VideoAdapter adapter;
    Vector<Video> videos = new Vector<Video>();
    private String selectedFilter = "all";


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


        //videos = new Vector<Video>();
        //adapter = new VideoAdapter(this, videos);
        //rvCardioExercises.setAdapter(adapter);
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
                        video.setVideoUrl(video.getVideoUrl());
                        easyList.add(video);
                    }
                    if(video.getVideoCategory().equals("cardio") && video.getVideoDifficulty().equals("medium")){
                        video.setVideoUrl(video.getVideoUrl());
                        mediumList.add(video);
                    }
                    if(video.getVideoCategory().equals("cardio") && video.getVideoDifficulty().equals("hard")){
                        video.setVideoUrl(video.getVideoUrl());
                        hardList.add(video);
                    }
                    Log.i(TAG, "Title: " + video.getVideoTitle() + ", difficulty: " + video.getVideoDifficulty() + ", muscleType: " + video.getMuscleType() + ", videoID: " + video.getVideoId());
                }
                videos.addAll(easyList);
                videos.addAll(mediumList);
                videos.addAll(hardList);

                adapter = new VideoAdapter(getApplicationContext(), videos);
                rvCardioExercises.setAdapter(adapter);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_easy:
                filterList("easy");
                return true;

            case R.id.action_medium:
                filterList("medium");
                return true;

            case R.id.action_hard:
                filterList("hard");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filterList(String status)
    {
        selectedFilter = status;
        ArrayList<Video> filteredVideos = new ArrayList<Video>();

        for(Video video : videos)
        {
            if(video.getVideoDifficulty().equals(status)){
                video.setVideoUrl(video.getVideoUrl());
                filteredVideos.add(video);
            }
        }
        adapter = new VideoAdapter(getApplicationContext(), filteredVideos);
        rvCardioExercises.setAdapter(adapter);
    }

    public void allFilterTapped(View view){
        selectedFilter ="all";
        adapter = new VideoAdapter(getApplicationContext(), videos);
        rvCardioExercises.setAdapter(adapter);
    }

    public void easyFilterTapped(View view){
        filterList("easy");
    }

    public void mediumFilterTapped(View view){
        filterList("medium");
    }

    public void hardFilterTapped(View view){
        filterList("hard");
    }
}