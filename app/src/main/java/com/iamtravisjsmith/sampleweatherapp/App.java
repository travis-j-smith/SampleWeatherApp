package com.iamtravisjsmith.sampleweatherapp;

import android.app.Application;

import com.iamtravisjsmith.sampleweatherapp.util.LocationTracker;
import com.iamtravisjsmith.sampleweatherapp.util.WeatherApiManager;

/**
 * Created by Travis on 2/17/2017
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LocationTracker.init(this);
        WeatherApiManager.init(getString(R.string.weather_api_key));
    }
}
