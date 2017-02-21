package com.iamtravisjsmith.sampleweatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Travis on 2/14/2017
 */

public class MainWeather {

    @SerializedName("temp") private double temperature;
    @SerializedName("temp_min") private double minimumTemperature;
    @SerializedName("temp_max") private double maximumTemperature;
    private double pressure;
    private double humidity;

    public MainWeather(double humidity, double maximumTemperature, double minimumTemperature, double pressure, double temperature) {
        this.humidity = humidity;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }
}
