package com.codepath.fittrack;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qrPzWdcwXBj2CFUBU65NDNKzTp7zfR3sNL828DpY")
                .clientKey("Oi4GtZJoLqyWuaumj2xshesRaQe98ShaAgEHRU2M")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
