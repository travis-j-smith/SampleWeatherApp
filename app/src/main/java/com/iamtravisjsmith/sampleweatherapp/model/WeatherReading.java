package com.iamtravisjsmith.sampleweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Travis on 2/14/2017
 */

public class WeatherReading {

    private int id;
    private String name;
    @SerializedName("dt") private long readingEpoch;
    @SerializedName("main") private MainWeather mainWeather;
    @SerializedName("weather") private List<WeatherCondition> weatherConditions;
    @SerializedName("clouds") private CloudWeather cloudWeather;
    @SerializedName("rain") private PrecipitationWeather rainWeather;
    @SerializedName("snow") private PrecipitationWeather snowWeather;
    @SerializedName("wind") private WindWeather windWeather;

    public WeatherReading(CloudWeather cloudWeather, int id, MainWeather mainWeather, String name, PrecipitationWeather rainWeather, long readingEpoch, PrecipitationWeather snowWeather, List<WeatherCondition> weatherConditions, WindWeather windWeather) {
        this.cloudWeather = cloudWeather;
        this.id = id;
        this.mainWeather = mainWeather;
        this.name = name;
        this.rainWeather = rainWeather;
        this.readingEpoch = readingEpoch;
        this.snowWeather = snowWeather;
        this.weatherConditions = weatherConditions;
        this.windWeather = windWeather;
    }

    public CloudWeather getCloudWeather() {
        return cloudWeather;
    }

    public int getId() {
        return id;
    }

    public MainWeather getMainWeather() {
        return mainWeather;
    }

    public String getName() {
        return name;
    }

    public PrecipitationWeather getRainWeather() {
        return rainWeather;
    }

    public long getReadingEpoch() {
        return readingEpoch;
    }

    public PrecipitationWeather getSnowWeather() {
        return snowWeather;
    }

    public List<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }

    public WindWeather getWindWeather() {
        return windWeather;
    }
}
