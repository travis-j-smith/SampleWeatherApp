package com.iamtravisjsmith.sampleweatherapp.util;

import com.iamtravisjsmith.sampleweatherapp.model.WeatherReading;

/**
 * Created by Travis on 2/17/2017
 */

public interface WeatherCallback {

    void withWeather(WeatherReading weatherReading, Throwable error);
}
