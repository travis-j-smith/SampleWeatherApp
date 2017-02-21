package com.iamtravisjsmith.sampleweatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Travis on 2/19/2017
 */

public class WindWeather {

    private double speed;
    @SerializedName("deg") private double directionDegrees;

    public WindWeather(double directionDegrees, double speed) {
        this.directionDegrees = directionDegrees;
        this.speed = speed;
    }

    public double getDirectionDegrees() {
        return directionDegrees;
    }

    public double getSpeed() {
        return speed;
    }
}
