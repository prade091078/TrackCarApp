package com.prade.trackcarapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class ParseServerStart extends Application {


    @Override // Initialise parse server and build it
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.parseAppId))
                .clientKey(getString(R.string.parseAppClientId))
                .server(getString(R.string.parseAppServerId))
                .build());


        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}
