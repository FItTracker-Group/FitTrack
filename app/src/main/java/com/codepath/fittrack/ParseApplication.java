package com.codepath.fittrack;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(UserInfo.class);
        // Register your parse models
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qrPzWdcwXBj2CFUBU65NDNKzTp7zfR3sNL828DpY")
                .clientKey("Oi4GtZJoLqyWuaumj2xshesRaQe98ShaAgEHRU2M")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
