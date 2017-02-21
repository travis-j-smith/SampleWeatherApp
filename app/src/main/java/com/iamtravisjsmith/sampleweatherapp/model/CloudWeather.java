package com.iamtravisjsmith.sampleweatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Travis on 2/19/2017
 */

public class CloudWeather {

    @SerializedName("all") private double percent;

    public CloudWeather(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }
}
