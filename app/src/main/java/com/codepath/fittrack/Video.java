package com.codepath.fittrack;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Video")
public class Video extends ParseObject {
    public static final String VIDEO_URL = "VideoUrl";
    public static final String VIDEO_TITLE = "Title";
    public static final String VIDEO_DIFFICULTY = "Difficulty";
    public static final String MUSCLE_TYPE = "MuscleType";
    public static final String VIDEO_ID = "VideoId";
    public static final String VIDEO_CATEGORY = "Category";

    public Video(){}
    public Video(String videoUrl){ put(VIDEO_URL, videoUrl); }

    //Getters
    public String getVideoUrl() { return getString(VIDEO_URL); }

    public String getVideoTitle() { return getString(VIDEO_TITLE); }

    public String getVideoDifficulty() { return getString(VIDEO_DIFFICULTY); }

    public String getMuscleType() { return getString(MUSCLE_TYPE); }

    public String getVideoId() { return getString(VIDEO_ID); }

    public String getVideoCategory() { return getString(VIDEO_CATEGORY); }

    //Setters

    public void setVideoUrl(String url) { put(VIDEO_URL, url); }

    public void setVideoTitle(String title) { put(VIDEO_TITLE, title); }

    public void setVideoDifficulty(String difficulty) { put(VIDEO_DIFFICULTY, difficulty); }

    public void setMuscleType(String muscle) { put(MUSCLE_TYPE, muscle); }

    public void setVideoId(String id) { put(VIDEO_ID, id); }

    public void setVideoCategory(String category) { put(VIDEO_CATEGORY, category); }

}
