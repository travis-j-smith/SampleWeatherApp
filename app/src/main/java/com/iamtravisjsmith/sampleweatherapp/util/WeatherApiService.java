package com.iamtravisjsmith.sampleweatherapp.util;

import com.iamtravisjsmith.sampleweatherapp.model.WeatherReading;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Travis on 2/14/2017
 */

public interface WeatherApiService {

    // Example: api.openweathermap.org/data/2.5/weather?q=nashville
    @GET("weather")
    Call<WeatherReading> getByCityName(@Query("q") String cityName);

    // Example: api.openweathermap.org/data/2.5/weather?lat=35.1&lon=139.3
    @GET("weather")
    Call<WeatherReading> getByGeographicCoordinates(@Query("lat") double latitude,
                                                    @Query("lon") double longitude);
}
