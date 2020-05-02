package com.prade.trackcarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); // hide app header

        if(ParseUser.getCurrentUser() == null)
        {
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e == null)
                    {
                        Log.i("Info", "Anonymous Login successful");
                    }else{
                        Log.i("Info", "Anonymous Login failed");
                    }
                }
            });
        }else{
         if(ParseUser.getCurrentUser().get("riderOrDriver")!=null){
             Log.i("Info", "Redirecting as" +ParseUser.getCurrentUser().get("riderOrDriver"));
             redirectActivity();
         }
        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }


    public void getStarted(View view) {

        Switch switchUserselection = (Switch) findViewById(R.id.switchRole);
        Log.i("Switch value", String.valueOf(switchUserselection.isChecked()));
        String userType = "driver";
        if (switchUserselection.isChecked()) {
            userType = "rider";
        }

        ParseUser.getCurrentUser().put("riderOrDriver", userType);
        Log.i("User type",userType);
        redirectActivity();
    }

    public void redirectActivity()
    {
        if(ParseUser.getCurrentUser().get("riderOrDriver")=="rider"){
            Intent intent=new Intent(getApplicationContext(),RiderActivity.class);
            startActivity(intent);
        }
    }
}
